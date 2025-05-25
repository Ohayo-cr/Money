package ru.ohayo.moneypr.ui.theme.screens.transactionList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import ru.ohayo.moneypr.domain.allEntity.Account

@Composable
fun getAccount(accounts: List<Account>, accountId: Long?): Account? {
    val result by remember(accounts, accountId) {
        mutableStateOf(accountId?.let { accounts.find { acc -> acc.id == it } })
    }
    return result
}