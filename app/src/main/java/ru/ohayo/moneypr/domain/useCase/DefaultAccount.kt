package ru.ohayo.moneypr.domain.useCase

import ru.ohayo.moneypr.domain.allEntity.AccountDbo
import ru.ohayo.moneypr.domain.allEntity.AccountType

object DefaultAccounts {
    val DEFAULT_ACCOUNTDbos = listOf(
        AccountDbo(name = "Наличные", type = AccountType.Cash, balance = 1000.0, currency = "₽"),
        AccountDbo(name = "Райфайзен банк", type = AccountType.Card, balance = 1000.0, currency = "₽"),
        AccountDbo(name = "Точка банк", type = AccountType.Card, balance = 1000.0, currency = "₽"),
        AccountDbo(name = "Бачи", type = AccountType.Cash, balance = 1000.0, currency = "$")
    )
}