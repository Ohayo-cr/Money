package ru.ohayo.moneypr.viewModel

import ru.ohayo.moneypr.domain.allEntity.TransactionEntity

sealed class TransactionState {
    object Loading : TransactionState()
    data class Success(val transactions: List<TransactionEntity>) : TransactionState()
    data class Error(val message: String) : TransactionState()
}
