package ru.ohayo.moneypr.ui.screens.categoryList.components


data class CategoryTransactionStats(
    val category: String,
    val transactionCount: Int,
    val totalAmount: Double
)