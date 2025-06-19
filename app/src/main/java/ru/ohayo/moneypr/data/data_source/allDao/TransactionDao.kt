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
import ru.ohayo.moneypr.ui.theme.screens.charts.components.CategorySummary


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
    SELECT 
        t.category AS categoryId,
        c.categoryName AS name,
        SUM(t.amount) AS totalAmount,
        c.color AS color,              
        c.iconResId AS iconResId      
    FROM transactions t
    JOIN categories c ON t.category = c.id
    WHERE strftime('%Y-%m', datetime(t.timestamp, 'unixepoch')) = strftime('%Y-%m', 'now')
      AND t.amount > 0
    GROUP BY t.category
    ORDER BY totalAmount DESC
"""
    )
    fun getCategorySummariesByMonth(): Flow<List<CategorySummary>>
}



