package ru.ohayo.moneypr.data.room.account

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.data.room.account.AccountDbo

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(accountDbo: AccountDbo)

    @Query("SELECT * FROM AccountDbo")
    fun getAllAccounts(): Flow<List<AccountDbo>>

    @Query("DELETE FROM AccountDbo WHERE id = :id")
    suspend fun deleteAccountById(id: Long) // Используем Long

    @Query("UPDATE AccountDbo SET balance = ROUND(balance + :amount, 2) WHERE id = :accountId")
    suspend fun updateBalance(accountId: Long, amount: Double)

    @Query("SELECT name FROM AccountDbo WHERE id = :accountId")
    suspend fun getAccountNameById(accountId: Long): String?
    @Query("SELECT * FROM AccountDbo WHERE id = :accountId")
    suspend fun getAccountById(accountId: Long): AccountDbo?
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllAccount(accountDbo: List<AccountDbo>)
    @Query("SELECT COUNT(*) FROM AccountDbo")
    suspend fun getAccountsCount(): Int
}

