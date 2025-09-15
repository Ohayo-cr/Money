package ru.ohayo.moneypr.ui.screens.transactionList.components.models

data class TransactionListUiState(
    val isLoading: Boolean = false,
    val groupedTransactions: List<DailyTransactionGroupData> = emptyList(),
    val error: String? = null
) {
    companion object {
        val Initial = TransactionListUiState()
    }
}