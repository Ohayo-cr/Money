package ru.ohayo.moneypr.ui.screens.addAccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.data.room.account.AccountDbo
import ru.ohayo.moneypr.data.room.account.AccountType
import ru.ohayo.moneypr.repository.AccountRepository

import javax.inject.Inject
@HiltViewModel
class AddAccountViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {
    private val _state = MutableStateFlow<AccountState>(AccountState.Idle)
    val state: StateFlow<AccountState> = _state
    fun addAccount(name: String, type: String, balance: Double, currency: String) {
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
                    currency = currency
                )

                accountRepository.insertAccount(accountDbo)
                _state.value = AccountState.Success
            } catch (e: Exception) {
                _state.value = AccountState.Error(e.message ?: "Неизвестная ошибка")
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
        object Idle : AccountState()
        object Loading : AccountState()
        object Success : AccountState()
        data class Error(val message: String) : AccountState()
    }
}

