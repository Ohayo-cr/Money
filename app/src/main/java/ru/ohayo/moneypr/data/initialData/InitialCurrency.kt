package ru.ohayo.moneypr.data.initialData

import ru.ohayo.moneypr.R

import ru.ohayo.moneypr.data.room.currency.CurrencyDbo

object InitialCurrency {
    val INITIAL_CURRENCY = listOf(
        CurrencyDbo(code = "USD",fullName = "United States dollar", iconResId = R.drawable.currency_united_states,symbol ="$"),
        CurrencyDbo(code = "RUB",fullName = "Russian ruble", iconResId = R.drawable.currency_russia_11699599, symbol ="₽"),
        CurrencyDbo(code = "TEST",fullName = "MOMOMO", iconResId = null, symbol ="X"),

    )
}