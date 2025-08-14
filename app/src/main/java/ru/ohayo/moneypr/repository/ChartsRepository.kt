package ru.ohayo.moneypr.repository

import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.ui.screens.charts.components.data.CategorySummaryFromDb

interface ChartsRepository {
    fun getCategoriesForPeriod(startTimestamp: Long, endTimestamp: Long): Flow<List<CategorySummaryFromDb>>



}