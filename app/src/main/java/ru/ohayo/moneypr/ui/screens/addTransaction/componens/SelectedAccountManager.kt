package ru.ohayo.moneypr.ui.screens.addTransaction.componens

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.ohayo.moneypr.domain.allEntity.AccountDbo

object SelectedAccountManager {
    private val _selectedAccountDbo = MutableStateFlow<AccountDbo?>(null)
    val selectedAccountDbo: StateFlow<AccountDbo?> = _selectedAccountDbo

    fun setSelectedAccount(accountDbo: AccountDbo?) {
        _selectedAccountDbo.value = accountDbo
    }

    fun clearSelectedAccount() {
        _selectedAccountDbo.value = null
    }
}
