package ru.ohayo.moneypr.ui.screens.transactionList.components.models

import ru.ohayo.moneypr.data.room.category.CategoryDbo
import ru.ohayo.moneypr.data.room.transaction.TransactionDbo

data class TransactionUiModel(
    val transaction: TransactionDbo,
    val category: CategoryDbo?,
    val formattedCategoryName: String,
    val displayTime: String,
    val displayAmount: String,
    val displayAccountInfo: String
)
