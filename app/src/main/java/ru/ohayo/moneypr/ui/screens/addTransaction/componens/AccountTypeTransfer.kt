package ru.ohayo.moneypr.ui.screens.addTransaction.componens



sealed class AccountTypeTransfer {
    data object From : AccountTypeTransfer()
    data object To : AccountTypeTransfer()

    val title: String
        get() = when (this) {
            is From -> "Счет списания"
            is To -> "Счет пополнения"
        }
}
