package ru.ohayo.moneypr.data.room.transaction

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.ui.screens.charts.components.data.CategorySummaryFromDb


@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions ORDER BY timestamp DESC")
    fun getAllTransactions(): Flow<List<TransactionDbo>>

    @Query("SELECT * FROM transactions WHERE id = :id")
    suspend fun getTransactionById(id: Long): TransactionDbo? // Изменено на Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionDbo)

    @Update
    suspend fun updateTransaction(transaction: TransactionDbo)

    @Query("DELETE FROM transactions WHERE id = :id")
    suspend fun deleteTransaction(id: Long) // Изменено на Long

    @Query("SELECT timestamp FROM transactions ORDER BY id DESC LIMIT 1")
    fun getLastAddedTransactionTimestampFlow(): Flow<Long?>

    @Query(
        """
    SELECT c.categoryName, 
           SUM(t.amount) AS totalAmount,
           (SUM(t.amount) * 100.0 / (
               SELECT SUM(amount) 
               FROM transactions 
               WHERE timestamp BETWEEN :startTimestamp AND :endTimestamp 
                 AND type = 'Expense'
           )) AS percentage,
           c.color, 
           c.iconResId
    FROM transactions t
    INNER JOIN CategoryDbo c ON t.category = c.categoryName
    WHERE t.timestamp BETWEEN :startTimestamp AND :endTimestamp
      AND t.type = 'Expense'
    GROUP BY c.id
    ORDER BY totalAmount ASC
    """
    )
     fun getMonthlyCategorySummaries(
        startTimestamp: Long,
        endTimestamp: Long
    ): Flow<List<CategorySummaryFromDb>>
}






