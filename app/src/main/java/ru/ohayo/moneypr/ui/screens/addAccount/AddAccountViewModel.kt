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
import ru.ohayo.moneypr.ui.screens.addAccount.components.AccountIconMapper
import ru.ohayo.moneypr.ui.screens.addAccount.components.AccountItem
import ru.ohayo.moneypr.utils.app_const.ExchangeRates

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

    fun resetState() {
        viewModelScope.launch {
            _state.value = AccountState.Idle
        }
    }
    private val _selectedIcon = MutableStateFlow<Int?>(null)
    val selectedIcon: StateFlow<Int?> get() = _selectedIcon

    fun setSelectedIcon(iconResId: Int?) {
        viewModelScope.launch {
            _selectedIcon.value = iconResId
        }
    }


    init {
        // Инициализация начальных значений
        viewModelScope.launch {
            _fieldValues.emit(mapOf(
                "name" to "",
                "note" to ""))
            _tempFieldValues.emit(mapOf("name" to "", "note" to ""))
            setShowDialog ("accountType", true)

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
            setSelectedIcon(AccountIconMapper.getIconResId(selectedType))
        }
    }


    fun setCurrency(selectedCurrency: AccountItem) {
        viewModelScope.launch {
            // Store the symbol in fieldValues for database
            _fieldValues.update { it + ("currency" to selectedCurrency.mainText) }
            // Store the display name separately for UI
            _fieldValues.update { it + ("currency_display" to selectedCurrency.mainText) }
            _fieldValues.update { it + ("currency_symbol" to selectedCurrency.symbol) }
        }
    }


    fun addAccount(name: String, type: String, balance: Double, note: String) {
        viewModelScope.launch {
            _state.value = AccountState.Loading
            try {
                // Проверяем уникальность имени счета
                val existingAccount = accountRepository.getAllAccounts()
                    .first() // Получаем список счетов из потока
                    .firstOrNull { it.name == name }

                if (existingAccount != null) {
                    throw IllegalArgumentException("Счет с именем $name уже существует.")
                }

                // Безопасное преобразование строки в AccountType
                val accountType = enumValueOrNull<AccountType>(type)
                    ?: throw IllegalArgumentException("Неверный тип счета: $type")

                val currencyCode = _fieldValues.value["currency"] ?: throw IllegalArgumentException("Нужно выбрать валюту")
                val currencySymbol = _fieldValues.value["currency_symbol"] ?: throw IllegalArgumentException("Символ валюты не установлен")
                val exchangeRate = ExchangeRates.getRate(currencyCode)

                val accountDbo = AccountDbo(
                    name = name,
                    type = accountType,
                    initialBalance = balance,
                    balance = balance,
                    currencyCode = currencyCode,
                    icon = _selectedIcon.value,
                    note = note,
                    exchangeRate = exchangeRate,
                    currencySymbol = currencySymbol,
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

