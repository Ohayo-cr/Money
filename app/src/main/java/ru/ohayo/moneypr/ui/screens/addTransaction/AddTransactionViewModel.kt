package ru.ohayo.moneypr.ui.screens.addTransaction

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
import ru.ohayo.moneypr.data.room.account.AccountDbo
import ru.ohayo.moneypr.repository.CategoryRepository
import ru.ohayo.moneypr.repository.CurrencyRepository
import ru.ohayo.moneypr.repository.TransactionRepository
import ru.ohayo.moneypr.repository.AccountRepository
import ru.ohayo.moneypr.data.room.transaction.TransactionDbo
import ru.ohayo.moneypr.data.room.category.CategoryDbo
import ru.ohayo.moneypr.data.room.category.CategoryType
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val repository: TransactionRepository,
    private val accountRepository: AccountRepository,
    private val currencyRepository: CurrencyRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _note = mutableStateOf("")
    val note: State<String> get() = _note

    fun updateNote(newNote: String) {
        _note.value = newNote
    }


    private val _selectedCategory = mutableStateOf<CategoryDbo?>(null)
    val selectedCategory: State<CategoryDbo?> get() = _selectedCategory

    fun selectCategoryName(newCategoryName: String) {
        viewModelScope.launch {
            val category = categoryRepository.getCategoryByName(newCategoryName)
            _selectedCategory.value = category
        }
    }
    fun getSelectedCategory(): CategoryDbo? = _selectedCategory.value

    private val _transactionResult = MutableStateFlow<Result<Unit>?>(null)
    val transactionResult: StateFlow<Result<Unit>?> = _transactionResult.asStateFlow()

    val accounts = accountRepository.getAllAccounts()
    val currencies = currencyRepository.getAllCurrenciesFlow()

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



    fun addTransaction(transaction: TransactionDbo) {
        viewModelScope.launch {
            try {

                val updatedTransaction = transaction.copy(timestamp = selectedDate.value)

                repository.insertTransaction(updatedTransaction)
                if (updatedTransaction.account != null) {
                    accountRepository.updateBalance(
                        updatedTransaction.account,
                        updatedTransaction.amount
                    )
                }

                _transactionResult.value = Result.success(Unit)

            } catch (e: Exception) {
                e.printStackTrace()
                _transactionResult.value = Result.failure(e)
            }
        }
    }

    // ПЕРЕВОДЫ
    private val _fromAccount = mutableStateOf<AccountDbo?>(null)
    val fromAccount: State<AccountDbo?> get() = _fromAccount

    private val _toAccount = mutableStateOf<AccountDbo?>(null)
    val toAccount: State<AccountDbo?> get() = _toAccount

    // Функции для выбора аккаунтов
    fun selectFromAccount(account: AccountDbo) {
        _fromAccount.value = account
    }

    fun selectToAccount(account: AccountDbo) {
        _toAccount.value = account
    }

    fun clearFromAccount() {
        _fromAccount.value = null
    }

    fun clearToAccount() {
        _toAccount.value = null
    }
    fun addTransferTransaction(amount: Double, note: String?) {
        viewModelScope.launch {
            try {
                val fromAcc = _fromAccount.value ?: return@launch
                val toAcc = _toAccount.value ?: return@launch

                val transaction = TransactionDbo(
                    type = CategoryType.AccountTransfer,
                    currency = fromAcc.currency, // или toAcc.currency — проверь совпадение
                    amount = amount,
                    paymentAccount = fromAcc.name,
                    recipientAccount = toAcc.name,
                    note = note,
                    timestamp = selectedDate.value,
                    category = "Transfer" // или любая служебная категория
                )

                repository.insertTransaction(transaction)

                // Обновляем балансы
                accountRepository.updateBalancesTransfer(
                    fromAccount = fromAcc.name,
                    toAccount = toAcc.name,
                    amount = amount
                )

                _transactionResult.value = Result.success(Unit)
            } catch (e: Exception) {
                e.printStackTrace()
                _transactionResult.value = Result.failure(e)
            }
        }
    }
    fun getFromAccount(): AccountDbo? = _fromAccount.value
    fun getToAccount(): AccountDbo? = _toAccount.value

}


