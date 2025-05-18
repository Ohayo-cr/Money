package ru.ohayo.moneypr.viewModel

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.data.repository.TransactionRepository
import ru.ohayo.moneypr.domain.allEntity.TransactionEntity
import javax.inject.Inject

@HiltViewModel
class TransactionListViewModel @Inject constructor(
    private val repository: TransactionRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<TransactionState>(TransactionState.Loading)
    val state: StateFlow<TransactionState> = _state.asStateFlow()
    private val _transactions = MutableStateFlow<List<TransactionEntity>>(emptyList())
    val transactions: StateFlow<List<TransactionEntity>> = _transactions.asStateFlow()
    val scrollState = LazyListState(0, 0)
    init {
        loadTransactions()
    }

    fun loadTransactions() {
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
}

