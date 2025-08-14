package ru.ohayo.moneypr.ui.screens.charts.components.data

data class CategorySummaryFromDb(
    val categoryName: String,
    val totalAmount: Double,
    val percentage: Double,
    val color: Long,
    val iconResId: Int,
)