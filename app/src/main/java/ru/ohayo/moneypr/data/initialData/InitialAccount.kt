package ru.ohayo.moneypr.data.initialData

import ru.ohayo.moneypr.R
import ru.ohayo.moneypr.data.room.account.AccountDbo
import ru.ohayo.moneypr.data.room.account.AccountType

object InitialAccounts {
    val INITIAL_ACCOUNT = listOf(
        AccountDbo(name = "Наличные", type = AccountType.Cash, balance = 1000.0, currencySymbol = "₽", currencyCode ="RUB" , icon = R.drawable.acc_d_cash),
        AccountDbo(name = "Райфайзен банк", type = AccountType.Card, balance = 1000.0, currencySymbol = "₽", currencyCode ="RUB" ,icon = R.drawable.acc_d_card),
        AccountDbo(name = "Точка банк", type = AccountType.Card, balance = 1000.0, currencySymbol = "₽", currencyCode ="RUB" ,icon = R.drawable.acc_d_contribution),
        AccountDbo(name = "Бачи", type = AccountType.Cash, balance = 1000.0, currencySymbol = "$", currencyCode ="USD" ,icon = R.drawable.acc_d_card, exchangeRate = 89.5),
        AccountDbo(name = "Рубесы не в бюджете", type = AccountType.Card, balance = 1000.0, currencySymbol = "₽", currencyCode ="RUB" ,icon = R.drawable.acc_d_card, inBudget = false),
    )
}