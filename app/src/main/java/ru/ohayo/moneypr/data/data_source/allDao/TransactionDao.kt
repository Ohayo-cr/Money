package ru.ohayo.moneypr.data.data_source.allDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import ru.ohayo.moneypr.domain.allEntity.TransactionEntity
import ru.ohayo.moneypr.ui.theme.screens.charts.components.CategorySummaryFromDb


@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions ORDER BY timestamp DESC")
    fun getAllTransactions(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE id = :id")
    suspend fun getTransactionById(id: Long): TransactionEntity? // Изменено на Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity)

    @Update
    suspend fun updateTransaction(transaction: TransactionEntity)

    @Query("DELETE FROM transactions WHERE id = :id")
    suspend fun deleteTransaction(id: Long) // Изменено на Long

    @Query("SELECT timestamp FROM transactions ORDER BY id DESC LIMIT 1")
    fun getLastAddedTransactionTimestampFlow(): Flow<Long?>

    @Query(
        """
        SELECT c.categoryName, 
               SUM(t.amount) AS totalAmount,
               (SUM(t.amount) * 100.0 / (SELECT SUM(amount) FROM transactions 
WHERE timestamp BETWEEN :startTimestamp AND :endTimestamp)) AS percentage,
               c.color, 
               c.iconResId
        FROM transactions t
        INNER JOIN Category c ON t.category = c.id
        WHERE t.timestamp BETWEEN :startTimestamp AND :endTimestamp
        GROUP BY c.id
        ORDER BY totalAmount 
    """
    )
    suspend fun getMonthlyCategorySummaries(
        startTimestamp: Long,
        endTimestamp: Long
    ): List<CategorySummaryFromDb>
}






