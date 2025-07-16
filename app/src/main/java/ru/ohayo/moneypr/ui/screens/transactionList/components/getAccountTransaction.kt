package ru.ohayo.moneypr.ui.screens.transactionList.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import ru.ohayo.moneypr.data.room.account.AccountDbo

@Composable
fun getAccount(account: List<AccountDbo>, accountId: Long?): AccountDbo? {
    val result by remember(account, accountId) {
        mutableStateOf(accountId?.let { account.find { acc -> acc.id == it } })
    }
    return result
}