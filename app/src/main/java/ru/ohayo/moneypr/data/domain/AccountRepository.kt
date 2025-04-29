package ru.ohayo.moneypr.domain

import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun insertAccount(account: Account)
    fun getAllAccounts(): Flow<List<Account>>
    suspend fun deleteAccountById(id: Long) // Используем Long
    suspend fun updateBalance(accountId: Long, amount: Double)
    suspend fun getAccountName(accountId: Long): String?
}