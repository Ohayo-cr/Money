package ru.ohayo.moneypr.ui.screens.addAccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.data.room.account.AccountDbo
import ru.ohayo.moneypr.data.room.account.AccountType
import ru.ohayo.moneypr.repository.AccountRepository
import ru.ohayo.moneypr.repository.CurrencyRepository
import ru.ohayo.moneypr.ui.screens.addAccount.components.AccountItem

import javax.inject.Inject
@HiltViewModel
class AddAccountViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val currencyRepository: CurrencyRepository
) : ViewModel() {


    private val _currencyList = mutableListOf<AccountItem>()
    val currencyList: List<AccountItem> get() = _currencyList


    private val _state = MutableStateFlow<AccountState>(AccountState.Idle)
    val state: StateFlow<AccountState> = _state
    private val _dialogStates = MutableStateFlow(mapOf<String, Boolean>())
    val dialogStates: StateFlow<Map<String, Boolean>> get() = _dialogStates

    private val _fieldValues = MutableStateFlow(mapOf<String, String>())
    val fieldValues: StateFlow<Map<String, String>> get() = _fieldValues

    private val _tempFieldValues = MutableStateFlow(mapOf<String, String>())
    val tempFieldValues: StateFlow<Map<String, String>> get() = _tempFieldValues

    init {
        // Инициализация начальных значений
        viewModelScope.launch {
            _fieldValues.emit(mapOf(
                "name" to "",
                "note" to "",
                "type" to AccountType.Cash.toString()))
            _tempFieldValues.emit(mapOf("name" to "", "note" to ""))

            loadCurrencyList()
        }
    }
    private fun loadCurrencyList() {
        viewModelScope.launch {
            val currencies = currencyRepository.getAllAccountItems()
            _currencyList.clear()
            _currencyList.addAll(currencies)
        }
    }

    fun setShowDialog(dialogKey: String, show: Boolean) {
        viewModelScope.launch {
            _dialogStates.update { it + (dialogKey to show) }
        }
    }

     fun setFieldValue(fieldKey: String, value: String) {
        viewModelScope.launch {
            _fieldValues.update { it + (fieldKey to value) }
        }
    }

    fun setTempFieldValue(fieldKey: String, value: String) {
        viewModelScope.launch {
            _tempFieldValues.update { it + (fieldKey to value) }
        }
    }

    fun confirmField(fieldKey: String) {
        viewModelScope.launch {
            val tempValue = _tempFieldValues.value[fieldKey] ?: ""
            setFieldValue(fieldKey, tempValue)
            setShowDialog(fieldKey, false)
        }
    }


    fun setAccountType(selectedType: AccountType) {
        viewModelScope.launch {
            _fieldValues.update { it + ("type" to selectedType.name) }
        }
    }

    fun setCurrency(selectedCurrency: AccountItem) {
        viewModelScope.launch {
            _fieldValues.update { it + ("currency" to selectedCurrency.mainText) }
        }
    }


    fun addAccount(name: String, type: String, balance: Double, currency: String, note: String) {
        viewModelScope.launch {
            _state.value = AccountState.Loading
            try {
                // Проверяем уникальность имени счета
                val existingAccount = accountRepository.getAllAccounts()
                    .first() // Получаем список счетов из потока
                    .firstOrNull { it.name == name }

                if (existingAccount != null) {
                    throw IllegalArgumentException("Счет с именем '$name' уже существует.")
                }

                // Безопасное преобразование строки в AccountType
                val accountType = enumValueOrNull<AccountType>(type)
                    ?: throw IllegalArgumentException("Неверный тип счета: $type")

                val accountDbo = AccountDbo(
                    name = name,
                    type = accountType,
                    balance = balance,
                    currency = currency,
                    note = note
                    )

                accountRepository.insertAccount(accountDbo)
                _state.value = AccountState.Success
            } catch (e: Exception) {
                _state.value = AccountState.Error(e.message ?: "Ошибка")
            }
        }
    }
    private inline fun <reified T : Enum<T>> enumValueOrNull(value: String): T? {
        return try {
            enumValueOf<T>(value)
        } catch (e: IllegalArgumentException) {
            null
        }
    }
    sealed class AccountState {
        data object Idle : AccountState()
        data object Loading : AccountState()
        data object Success : AccountState()
        data class Error(val message: String) : AccountState()
    }
}

