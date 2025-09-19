package ru.ohayo.moneypr.ui.screens.transactionList

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.data.room.category.CategoryDbo
import ru.ohayo.moneypr.repository.TransactionRepository
import ru.ohayo.moneypr.data.room.transaction.TransactionDbo
import ru.ohayo.moneypr.repository.CategoryRepository
import ru.ohayo.moneypr.ui.screens.transactionList.components.groupTransactionsByDate
import ru.ohayo.moneypr.ui.screens.transactionList.components.models.DayTransactions
import javax.inject.Inject

@HiltViewModel
class TransactionListViewModel @Inject constructor(
    private val repository: TransactionRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _transactions = MutableStateFlow<List<TransactionDbo>>(emptyList())
    val transactions: StateFlow<List<TransactionDbo>> = _transactions.asStateFlow()

    private val _categories = MutableStateFlow<List<CategoryDbo>>(emptyList())
    val categories: StateFlow<List<CategoryDbo>> = _categories

    private val _state = MutableStateFlow<TransactionState>(TransactionState.Loading)
    val state: StateFlow<TransactionState> = _state.asStateFlow()

    val scrollState = LazyListState(0, 0)
    init {
        loadTransactions()
        loadAllCategories()
    }
    // Группировка транзакций по дате
    val groupedTransactions: StateFlow<List<DayTransactions>> =
        _transactions.map { transactions ->
            if (transactions.isNotEmpty()) {
                groupTransactionsByDate(transactions)
            } else {
                emptyList()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Маппинг категорий по имени
    val categoryMap: StateFlow<Map<String, CategoryDbo>> =
        _categories.map { categories ->
            categories.associateBy { it.categoryName }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyMap())

    private fun loadTransactions() {
        viewModelScope.launch {
            try {
                _state.value = TransactionState.Loading
                repository.getAllTransactions().collect { dbTransactions ->
                    if (dbTransactions.isEmpty()) {
                        _state.value = TransactionState.Error("Нет транзакций")
                    } else {
                        _state.value = TransactionState.Success(dbTransactions)
                    }
                    _transactions.value = dbTransactions
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _state.value = TransactionState.Error(e.message ?: "Неизвестная ошибка")
            }
        }
    }

    private fun loadAllCategories() {
        viewModelScope.launch {
            categoryRepository.getAllCategories().collect { categories ->
                _categories.value = categories

            }
        }
    }
}

