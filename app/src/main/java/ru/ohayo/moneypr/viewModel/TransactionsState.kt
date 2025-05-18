package ru.ohayo.moneypr.viewModel

import ru.ohayo.moneypr.domain.allEntity.TransactionEntity

sealed class TransactionState {
    object Loading : TransactionState() // Данные загружаются
    data class Success(val transactions: List<TransactionEntity>) : TransactionState() // Данные успешно загружены
    data class Error(val message: String) : TransactionState() // Произошла ошибка
}
