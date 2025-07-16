package ru.ohayo.moneypr.ui.screens.transactionList.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import ru.ohayo.moneypr.data.room.account.AccountDbo

@Composable
fun getAccount(accountDbos: List<AccountDbo>, accountId: Long?): AccountDbo? {
    val result by remember(accountDbos, accountId) {
        mutableStateOf(accountId?.let { accountDbos.find { acc -> acc.id == it } })
    }
    return result
}