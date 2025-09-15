package ru.ohayo.moneypr.ui.screens.transactionList

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.data.room.category.CategoryType
import ru.ohayo.moneypr.repository.TransactionRepository
import ru.ohayo.moneypr.data.room.transaction.TransactionDbo
import ru.ohayo.moneypr.ui.screens.transactionList.components.DailyTransactionGroupData
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class TransactionListViewModel @Inject constructor(
    private val repository: TransactionRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<TransactionState>(TransactionState.Loading)
    val state: StateFlow<TransactionState> = _state.asStateFlow()
    private val _transactions = MutableStateFlow<List<TransactionDbo>>(emptyList())
    val transactions: StateFlow<List<TransactionDbo>> = _transactions.asStateFlow()
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

    fun KcreateDailyTransactionGroups(transactions: List<TransactionDbo>): List<DailyTransactionGroupData> {
        return transactions
            .groupBy { formatTimestampToDate(it.timestamp) } // группируем по дате
            .map { (date, dayTransactions) ->
                val (income, expense, total) = calculateDayTotals(dayTransactions)
                DailyTransactionGroupData(
                    date = date,
                    transactions = dayTransactions,
                    income = income,
                    expense = expense,
                    total = total
                )
            }
            .sortedByDescending { it.date } // сортируем по убыванию даты
    }

    // Расчет сумм для одного дня
    private fun calculateDayTotals(transactions: List<TransactionDbo>): Triple<Double, Double, Double> {
        val nonTransferTransactions = transactions.filter { it.type != CategoryType.AccountTransfer }

        val income = nonTransferTransactions
            .filter { it.amount > 0 }
            .sumOf { it.amount }

        val expense = nonTransferTransactions
            .filter { it.amount < 0 }
            .sumOf { -it.amount }

        val total = income - expense

        return Triple(income, expense, total)
    }
    // Вспомогательный метод для форматирования timestamp в строку даты
    private fun formatTimestampToDate(timestamp: Long): String {
        val date = Date(timestamp)
        val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return formatter.format(date)
    }
}

