package ru.ohayo.moneypr.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
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

    // Другие стейты
    private val _note = mutableStateOf("")
    val note: State<String> get() = _note

    fun updateNote(newNote: String) {
        _note.value = newNote
    }

    private val _transactionResult = MutableStateFlow<Result<Unit>?>(null)
    val transactionResult: StateFlow<Result<Unit>?> = _transactionResult.asStateFlow()

    val accounts = accountRepository.getAllAccounts()
    val currencies = currencyRepository.getAllCurrencies()

    // Состояние текущей даты (в миллисекундах)
    private val _currentDate = MutableStateFlow(System.currentTimeMillis())
    val currentDate: StateFlow<Long> = _currentDate

    // Последняя выбранная дата (из БД)
    val lastSelectedDate: Flow<Long> = repository.getLastAddedTransactionTimestampFlow()
        .map { it ?: System.currentTimeMillis() }

    // Выбранная пользователем дата (начальное значение — текущая дата)
    private val _selectedDate = MutableStateFlow(_currentDate.value)
    val selectedDate: StateFlow<Long> = _selectedDate

    // Функция для установки пользовательской даты
    fun setSelectedDate(date: Long) {
        _selectedDate.value = date
    }



    fun addTransaction(transaction: TransactionEntity) {
        viewModelScope.launch {
            try {
                // Установка даты транзакции в выбранную
                val updatedTransaction = transaction.copy(timestamp = selectedDate.value)
                repository.insertTransaction(updatedTransaction)

                if (updatedTransaction.fromAccount != null) {
                    accountRepository.updateBalance(
                        updatedTransaction.fromAccount,
                        -updatedTransaction.amount
                    )
                }
                if (updatedTransaction.toAccount != null) {
                    accountRepository.updateBalance(
                        updatedTransaction.toAccount,
                        updatedTransaction.amount
                    )
                }

                _transactionResult.value = Result.success(Unit)

                // После успешного добавления — сбрасываем дату

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

    suspend fun getCurrencySymbol(currencyId: Long): String? {
        return currencyRepository.getCurrencyById(currencyId)?.symbol ?: ""
    }

}


