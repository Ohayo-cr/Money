package ru.ohayo.moneypr.data.initialData

import ru.ohayo.moneypr.data.room.account.AccountDbo
import ru.ohayo.moneypr.data.room.account.AccountType

object InitialAccounts {
    val INITIAL_ACCOUNT = listOf(
        AccountDbo(name = "Наличные", type = AccountType.Cash, balance = 1000.0, currency = "₽"),
        AccountDbo(name = "Райфайзен банк", type = AccountType.Card, balance = 1000.0, currency = "₽"),
        AccountDbo(name = "Точка банк", type = AccountType.Card, balance = 1000.0, currency = "₽"),
        AccountDbo(name = "Бачи", type = AccountType.Cash, balance = 1000.0, currency = "$")
    )
}