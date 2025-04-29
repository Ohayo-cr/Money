package ru.ohayo.moneypr.domain.useCase

import ru.ohayo.moneypr.R

import ru.ohayo.moneypr.domain.category.CategoryType
import ru.ohayo.moneypr.domain.currency.Currency

object DefaultCurrency {
    val DEFAULT_CURRENCY = listOf(
        Currency(name = "USD", iconResId = R.drawable.currency_united_states),
        Currency(name = "RUB", iconResId = R.drawable.currency_russia_11699599)
    )
}