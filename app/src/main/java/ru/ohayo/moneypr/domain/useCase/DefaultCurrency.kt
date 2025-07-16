package ru.ohayo.moneypr.domain.useCase

import ru.ohayo.moneypr.R

import ru.ohayo.moneypr.domain.allEntity.CurrencyDbo

object DefaultCurrency {
    val DEFAULT_CURRENCY = listOf(
        CurrencyDbo(name = "USD", iconResId = R.drawable.currency_united_states,symbol ="$"),
        CurrencyDbo(name = "RUB", iconResId = R.drawable.currency_russia_11699599, symbol ="₽"),

    )
}