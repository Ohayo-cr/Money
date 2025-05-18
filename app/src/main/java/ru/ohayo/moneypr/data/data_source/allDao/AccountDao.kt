package ru.ohayo.moneypr.data.data_source.allDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.domain.allEntity.Account

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: Account)

    @Query("SELECT * FROM account")
    fun getAllAccounts(): Flow<List<Account>>

    @Query("DELETE FROM account WHERE id = :id")
    suspend fun deleteAccountById(id: Long) // Используем Long

    @Query("UPDATE account SET balance = ROUND(balance + :amount, 2) WHERE id = :accountId")
    suspend fun updateBalance(accountId: Long, amount: Double)

    @Query("SELECT name FROM account WHERE id = :accountId")
    suspend fun getAccountNameById(accountId: Long): String?
}

