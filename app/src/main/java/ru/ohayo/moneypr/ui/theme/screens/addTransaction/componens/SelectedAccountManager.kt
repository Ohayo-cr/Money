package ru.ohayo.moneypr.ui.theme.screens.addTransaction.componens

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.ohayo.moneypr.domain.allEntity.Account

object SelectedAccountManager {
    private val _selectedAccount = MutableStateFlow<Account?>(null)
    val selectedAccount: StateFlow<Account?> = _selectedAccount

    fun setSelectedAccount(account: Account?) {
        _selectedAccount.value = account
    }

    fun clearSelectedAccount() {
        _selectedAccount.value = null
    }
}