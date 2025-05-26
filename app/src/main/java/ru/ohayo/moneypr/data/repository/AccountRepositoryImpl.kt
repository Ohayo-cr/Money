package ru.ohayo.moneypr.data.repository

import android.database.sqlite.SQLiteConstraintException
import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.data.data_source.allDao.AccountDao
import ru.ohayo.moneypr.domain.allEntity.Account
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountDao: AccountDao
) : AccountRepository {

    override suspend fun insertAccount(account: Account) {
        try {
            accountDao.insertAccount(account)
        } catch (e: SQLiteConstraintException) {
            throw IllegalArgumentException("Account with name '${account.name}' already exists.")
        }
    }

    override fun getAllAccounts(): Flow<List<Account>> {
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
    override suspend fun getAccountById(accountId: Long): Account? {
        return accountDao.getAccountById(accountId)
    }
    override suspend fun insertAllAccount(accounts: List<Account>) {
        accountDao.insertAllAccount(accounts)
    }

    // Проверяем, есть ли уже аккаунты в БД
    override suspend fun isAccountsEmpty(): Boolean {
        return accountDao.getAccountsCount() == 0
    }

}
