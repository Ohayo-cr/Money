
package ru.ohayo.moneypr.data.repository


import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.data.data_source.allDao.TransactionDao
import ru.ohayo.moneypr.domain.allEntity.TransactionEntity
import ru.ohayo.moneypr.ui.theme.screens.charts.components.CategorySummary
import ru.ohayo.moneypr.ui.theme.screens.charts.components.getCurrentMonthRange

import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val transactionDao: TransactionDao
) {

    fun getAllTransactions(): Flow<List<TransactionEntity>> {
        return transactionDao.getAllTransactions()
    }

    suspend fun getTransactionById(id: Long): TransactionEntity? { // Изменено на Long
        return transactionDao.getTransactionById(id)
    }

    suspend fun insertTransaction(transaction: TransactionEntity) {
        transactionDao.insertTransaction(transaction)
    }

    suspend fun updateTransaction(transaction: TransactionEntity) {
        transactionDao.updateTransaction(transaction)
    }

    suspend fun deleteTransaction(id: Long) { // Изменено на Long
        transactionDao.deleteTransaction(id)
    }
    fun getLastAddedTransactionTimestampFlow(): Flow<Long?> =
        transactionDao.getLastAddedTransactionTimestampFlow()
    suspend fun getTopCategoriesForCurrentMonth(): List<CategorySummary> {
        val (start, end) = getCurrentMonthRange()
        return transactionDao.getMonthlyCategorySummaries(start, end)
    }



}