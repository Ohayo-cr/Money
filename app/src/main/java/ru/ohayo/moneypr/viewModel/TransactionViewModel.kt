package ru.ohayo.moneypr.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.data.repository.CategoryRepository
import ru.ohayo.moneypr.data.repository.CurrencyRepository
import ru.ohayo.moneypr.data.repository.TransactionRepository
import ru.ohayo.moneypr.domain.AccountRepository
import ru.ohayo.moneypr.domain.TransactionEntity
import ru.ohayo.moneypr.domain.category.Category
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repository: TransactionRepository,
    private val accountRepository: AccountRepository,
    private val currencyRepository: CurrencyRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _transactionResult = MutableStateFlow<Result<Unit>?>(null)
    val transactionResult: StateFlow<Result<Unit>?> = _transactionResult.asStateFlow()

    val accounts = accountRepository.getAllAccounts()
    val currencies = currencyRepository.getAllCurrencies()

    fun addTransaction(transaction: TransactionEntity) {
        viewModelScope.launch {
            try {
                repository.insertTransaction(transaction)

                if (transaction.fromAccount != null) {
                    accountRepository.updateBalance(
                        transaction.fromAccount,
                        -transaction.amount
                    )
                }
                if (transaction.toAccount != null) {
                    accountRepository.updateBalance(transaction.toAccount, transaction.amount)
                }

                _transactionResult.value = Result.success(Unit)
            } catch (e: Exception) {
                e.printStackTrace()
                _transactionResult.value = Result.failure(e)
            }
        }
    }


    fun updateTransaction(transaction: TransactionEntity) {
        viewModelScope.launch {
            try {
                repository.updateTransaction(transaction)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteTransaction(id: Long) {
        viewModelScope.launch {
            try {
                repository.deleteTransaction(id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun getTransactionById(id: Long): TransactionEntity? {
        return repository.getTransactionById(id)
    }

    fun addTransaction(
        amount: Double,
        content: String?,
        timestamp: Long,
        category: Int,
        fromAccount: Long?,
        toAccount: Long?,
        currency: Long?
    ) {
        viewModelScope.launch {
            val transaction = TransactionEntity(
                amount = amount,
                content = content,
                timestamp = timestamp,
                category = category.toLong(),
                fromAccount = fromAccount,
                toAccount = toAccount,
                currency = currency
            )
            repository.insertTransaction(transaction)
        }
    }

    suspend fun getCurrencyName(currencyId: Long): String? {
        return currencyRepository.getAllCurrencies()
            .first() // Получаем список валют из потока
            .firstOrNull { it.id == currencyId }
            ?.name
    }

    fun getCategoryById(categoryId: Long): Flow<Category?> {
        return categoryRepository.getCategoryById(categoryId.toLong())
    }

    suspend fun getAccountName(accountId: Long): String? {
        return accountRepository.getAccountName(accountId)
    }
}


