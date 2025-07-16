package ru.ohayo.moneypr.ui.screens.transactionList

import ru.ohayo.moneypr.data.room.transaction.TransactionDbo

sealed class TransactionState {
    object Loading : TransactionState()
    data class Success(val transactions: List<TransactionDbo>) : TransactionState()
    data class Error(val message: String) : TransactionState()
}
