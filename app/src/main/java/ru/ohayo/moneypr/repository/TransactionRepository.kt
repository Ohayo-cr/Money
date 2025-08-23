
package ru.ohayo.moneypr.repository


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.ohayo.moneypr.data.room.account.AccountDao
import ru.ohayo.moneypr.data.room.transaction.TransactionDao
import ru.ohayo.moneypr.data.room.transaction.TransactionDbo
import ru.ohayo.moneypr.ui.screens.transaction_details.componets.TransactionWithAccount


import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val transactionDao: TransactionDao,
    private val accountDao: AccountDao
) {

    fun getAllTransactions(): Flow<List<TransactionDbo>> {
        return transactionDao.getAllTransactions()
    }

    suspend fun getTransactionById(id: Long): TransactionDbo? { // Изменено на Long
        return transactionDao.getTransactionById(id)
    }

    suspend fun insertTransaction(transaction: TransactionDbo) {
        transactionDao.insertTransaction(transaction)
    }

    suspend fun updateTransaction(transaction: TransactionDbo) {
        transactionDao.updateTransaction(transaction)
    }

    suspend fun deleteTransaction(id: Long) { // Изменено на Long
        transactionDao.deleteTransaction(id)
    }
    fun getLastAddedTransactionTimestampFlow(): Flow<Long?> =
        transactionDao.getLastAddedTransactionTimestampFlow()

    fun getTransactionWithAccount(id: Long): Flow<TransactionWithAccount?> = flow {
        val transaction = transactionDao.getTransactionById(id) ?: return@flow emit(null)
        val account = transaction.account?.let { accountDao.getAccountByName(it) }
        emit(TransactionWithAccount(transaction, account))
    }




}