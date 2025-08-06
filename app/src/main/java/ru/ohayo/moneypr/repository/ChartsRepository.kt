package ru.ohayo.moneypr.repository

import ru.ohayo.moneypr.data.room.category.CategoryDbo
import ru.ohayo.moneypr.ui.screens.charts.components.CategorySummaryFromDb

interface ChartsRepository {
    suspend fun getCategoriesForPeriod(startTimestamp: Long, endTimestamp: Long): List<CategorySummaryFromDb>
}