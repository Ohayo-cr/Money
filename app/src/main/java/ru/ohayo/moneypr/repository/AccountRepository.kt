package ru.ohayo.moneypr.repository

import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.data.room.account.AccountDbo

interface AccountRepository {
    suspend fun insertAccount(accountDbo: AccountDbo)
    fun getAllAccounts(): Flow<List<AccountDbo>>
    suspend fun deleteAccountById(id: Long) // Используем Long
    suspend fun updateBalance(accountId: Long, amount: Double)
    suspend fun getAccountName(accountId: Long): String?
    suspend fun getAccountById(accountId: Long): AccountDbo?
    suspend fun insertAllAccount(accountDbo: List<AccountDbo>)
    suspend fun isAccountsEmpty(): Boolean

}