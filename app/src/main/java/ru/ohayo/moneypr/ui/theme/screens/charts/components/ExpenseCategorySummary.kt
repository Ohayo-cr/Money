package ru.ohayo.moneypr.ui.theme.screens.charts.components

data class CategorySummary(
    val categoryId: Long,
    val name: String,
    val totalAmount: Double,
    val color: Long,
    val iconResId: Int
)