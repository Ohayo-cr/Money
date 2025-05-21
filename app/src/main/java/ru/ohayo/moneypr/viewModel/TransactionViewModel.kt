package ru.ohayo.moneypr.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.data.repository.CategoryRepository
import ru.ohayo.moneypr.data.repository.CurrencyRepository
import ru.ohayo.moneypr.data.repository.TransactionRepository
import ru.ohayo.moneypr.data.repository.AccountRepository
import ru.ohayo.moneypr.domain.allEntity.TransactionEntity
import ru.ohayo.moneypr.domain.allEntity.Category
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

    // Состояние текущей даты (в миллисекундах)
    private val _currentDate = MutableStateFlow(System.currentTimeMillis())
    val currentDate: StateFlow<Long> = _currentDate

    // Последняя выбранная дата
    private val _lastSelectedDate = MutableStateFlow(System.currentTimeMillis())
    val lastSelectedDate: StateFlow<Long> = _lastSelectedDate

    fun setCurrentDate(date: Long) {
        _currentDate.value = date
    }

    fun setLastSelectedDate(date: Long) {
        _lastSelectedDate.value = date
    }

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
              setCurrentDate(System.currentTimeMillis())
            } catch (e: Exception) {
                e.printStackTrace()
                _transactionResult.value = Result.failure(e)
            }
        }
    }


    fun getCategoryById(categoryId: Long): Flow<Category?> {
        return categoryRepository.getCategoryById(categoryId.toLong())
    }

    suspend fun getAccountName(accountId: Long): String? {
        return accountRepository.getAccountName(accountId)
    }
}


