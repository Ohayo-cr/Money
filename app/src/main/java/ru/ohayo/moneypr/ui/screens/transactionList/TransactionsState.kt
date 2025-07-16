package ru.ohayo.moneypr.ui.screens.transactionList

import ru.ohayo.moneypr.domain.allEntity.TransactionDbo

sealed class TransactionState {
    object Loading : TransactionState()
    data class Success(val transactions: List<TransactionDbo>) : TransactionState()
    data class Error(val message: String) : TransactionState()
}
