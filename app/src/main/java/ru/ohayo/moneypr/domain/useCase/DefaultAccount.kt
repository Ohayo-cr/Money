package ru.ohayo.moneypr.domain.useCase

import ru.ohayo.moneypr.domain.allEntity.Account
import ru.ohayo.moneypr.domain.allEntity.AccountType

object DefaultAccounts {
    val DEFAULT_ACCOUNTS = listOf(
        Account(name = "Наличные", type = AccountType.Cash, balance = 1000.0, currency = "₽"),
        Account(name = "Райфайзен банк", type = AccountType.Card, balance = 1000.0, currency = "₽"),
        Account(name = "Точка банк", type = AccountType.Card, balance = 1000.0, currency = "₽"),
        Account(name = "Бачи", type = AccountType.Cash, balance = 1000.0, currency = "$")
    )
}