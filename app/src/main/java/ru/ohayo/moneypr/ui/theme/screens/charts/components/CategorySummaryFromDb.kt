package ru.ohayo.moneypr.ui.theme.screens.charts.components

data class CategorySummaryFromDb(
    val categoryName: String,
    val totalAmount: Double,
    val percentage: Double,
    val color: Long,
    val iconResId: Int,
)