package ru.ohayo.moneypr.repository


import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.data.room.transaction.TransactionDao
import ru.ohayo.moneypr.ui.screens.charts.components.data.CategorySummaryFromDb
import javax.inject.Inject

class ChartsRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao
) : ChartsRepository {

    override fun getCategoriesForPeriod(startTimestamp: Long, endTimestamp: Long): Flow<List<CategorySummaryFromDb>> {
        return transactionDao.getMonthlyCategorySummaries(startTimestamp, endTimestamp)
    }

}