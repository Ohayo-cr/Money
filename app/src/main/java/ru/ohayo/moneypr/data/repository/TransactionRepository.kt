
package ru.ohayo.moneypr.data.repository


import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.data.data_source.allDao.TransactionDao
import ru.ohayo.moneypr.domain.allEntity.TransactionDbo
import ru.ohayo.moneypr.ui.screens.charts.components.CategorySummaryFromDb
import ru.ohayo.moneypr.ui.screens.charts.components.getCurrentMonthRange

import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val transactionDao: TransactionDao
) {

    fun getAllTransactions(): Flow<List<TransactionDbo>> {
        return transactionDao.getAllTransactions()
    }

    suspend fun getTransactionById(id: Long): TransactionDbo? { // Изменено на Long
        return transactionDao.getTransactionById(id)
    }

    suspend fun insertTransaction(transaction: TransactionDbo) {
        transactionDao.insertTransaction(transaction)
    }

    suspend fun updateTransaction(transaction: TransactionDbo) {
        transactionDao.updateTransaction(transaction)
    }

    suspend fun deleteTransaction(id: Long) { // Изменено на Long
        transactionDao.deleteTransaction(id)
    }
    fun getLastAddedTransactionTimestampFlow(): Flow<Long?> =
        transactionDao.getLastAddedTransactionTimestampFlow()
    suspend fun getTopCategoriesForCurrentMonth(): List<CategorySummaryFromDb> {
        val (start, end) = getCurrentMonthRange()
        return transactionDao.getMonthlyCategorySummaries(start, end)
    }



}