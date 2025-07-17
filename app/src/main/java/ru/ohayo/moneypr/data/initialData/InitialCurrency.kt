package ru.ohayo.moneypr.data.initialData

import ru.ohayo.moneypr.R

import ru.ohayo.moneypr.data.room.currency.CurrencyDbo

object InitialCurrency {
    val INITIAL_CURRENCY = listOf(
        CurrencyDbo(name = "USD", iconResId = R.drawable.currency_united_states,symbol ="$"),
        CurrencyDbo(name = "RUB", iconResId = R.drawable.currency_russia_11699599, symbol ="₽"),

    )
}