package ru.ohayo.moneypr.ui.screens.transactionList.components.models

import ru.ohayo.moneypr.data.room.transaction.TransactionDbo

data class DailyTransactionGroupData(
    val date: String,
    val transactions: List<TransactionDbo>,
    val income: Double,
    val expense: Double,
    val total: Double
)