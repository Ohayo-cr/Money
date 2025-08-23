package ru.ohayo.moneypr.ui.screens.transaction_details.componets

import ru.ohayo.moneypr.data.room.account.AccountDbo
import ru.ohayo.moneypr.data.room.transaction.TransactionDbo

data class TransactionWithAccount(
    val transaction: TransactionDbo,
    val account: AccountDbo?
)