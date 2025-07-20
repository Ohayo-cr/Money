package ru.ohayo.moneypr.ui.screens.accountScreen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.data.room.account.AccountDbo
import ru.ohayo.moneypr.repository.AccountRepository
import ru.ohayo.moneypr.data.room.account.AccountType
import ru.ohayo.moneypr.ui.screens.addTransaction.componens.SelectedAccountManager
import javax.inject.Inject


@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    private val _accounts = MutableStateFlow<List<AccountDbo>>(emptyList())
    val accounts: StateFlow<List<AccountDbo>> = _accounts
    // Состояние для UI
    private val _state = MutableStateFlow<AccountState>(AccountState.Idle)
    val state: StateFlow<AccountState> = _state

    private val _selectedAccount = MutableStateFlow(SelectedAccountManager.selectedAccountDbo.value)
    val selectedAccountDbo: StateFlow<AccountDbo?> = _selectedAccount

    fun setSelectedAccount(accountDbo: AccountDbo?) {
        _selectedAccount.value = accountDbo
    }

    init {
        viewModelScope.launch {
            accountRepository.getAllAccounts()
                .distinctUntilChanged()
                .collect { accountList ->
                    _accounts.value = accountList

                    if (SelectedAccountManager.selectedAccountDbo.value == null && accountList.isNotEmpty()) {
                        SelectedAccountManager.setSelectedAccount(accountList.first())
                    }
                }
        }
    }

    // Метод получения аккаунта по ID
    fun getAccountById(accountId: Long) {
        viewModelScope.launch {
            _state.value = AccountState.Loading
            try {
                val account = accountRepository.getAccountById(accountId)
                if (account != null) {
                    _selectedAccount.value = account
                    _state.value = AccountState.Success
                } else {
                    _state.value = AccountState.Error("Аккаунт не найден")
                }
            } catch (e: Exception) {
                _state.value = AccountState.Error(e.message ?: "Неизвестная ошибка")
            }
        }
    }









    sealed class AccountState {
        object Idle : AccountState()
        object Loading : AccountState()
        object Success : AccountState()
        data class Error(val message: String) : AccountState()
    }
}