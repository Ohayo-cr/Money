package ru.ohayo.moneypr.data.repository

import android.database.sqlite.SQLiteConstraintException
import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.data.data_source.allDao.AccountDao
import ru.ohayo.moneypr.domain.allEntity.AccountDbo
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountDao: AccountDao
) : AccountRepository {

    override suspend fun insertAccount(accountDbo: AccountDbo) {
        try {
            accountDao.insertAccount(accountDbo)
        } catch (e: SQLiteConstraintException) {
            throw IllegalArgumentException("AccountDbo with name '${accountDbo.name}' already exists.")
        }
    }

    override fun getAllAccounts(): Flow<List<AccountDbo>> {
        return accountDao.getAllAccounts()
    }

    override suspend fun deleteAccountById(id: Long) { // Используем Long
        accountDao.deleteAccountById(id)
    }

    override suspend fun updateBalance(accountId: Long, amount: Double) {
        accountDao.updateBalance(accountId, amount)
    }

    override suspend fun getAccountName(accountId: Long): String? {
        return accountDao.getAccountNameById(accountId)
    }
    override suspend fun getAccountById(accountId: Long): AccountDbo? {
        return accountDao.getAccountById(accountId)
    }
    override suspend fun insertAllAccount(accountDbo: List<AccountDbo>) {
        accountDao.insertAllAccount(accountDbo)
    }

    // Проверяем, есть ли уже аккаунты в БД
    override suspend fun isAccountsEmpty(): Boolean {
        return accountDao.getAccountsCount() == 0
    }

}
