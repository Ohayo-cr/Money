package ru.ohayo.moneypr.data.initialData

import ru.ohayo.moneypr.R

import ru.ohayo.moneypr.data.room.currency.CurrencyDbo

object InitialCurrency {
    val INITIAL_CURRENCY = listOf(
        CurrencyDbo(code = "USD",fullName = "United States dollar", iconResId = R.drawable.currency_united_states,symbol ="$", rate = 89.5),
        CurrencyDbo(code = "RUB",fullName = "Russian ruble", iconResId = R.drawable.currency_russia_11699599, symbol ="₽",rate = 1.0),
        CurrencyDbo(code = "KZT",fullName = "Kazakhstani tenge", iconResId = R.drawable.currency_kazakhstan, symbol ="₸",rate = 0.1537),
        CurrencyDbo(code = "TEST",fullName = "TEST TEST TEST", iconResId = null, symbol ="T", rate = 10.0),

    )
}