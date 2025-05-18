package ru.ohayo.moneypr.data.repository

import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.domain.allEntity.Account

interface AccountRepository {
    suspend fun insertAccount(account: Account)
    fun getAllAccounts(): Flow<List<Account>>
    suspend fun deleteAccountById(id: Long) // Используем Long
    suspend fun updateBalance(accountId: Long, amount: Double)
    suspend fun getAccountName(accountId: Long): String?
}