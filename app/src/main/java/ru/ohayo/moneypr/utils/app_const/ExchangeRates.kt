package ru.ohayo.moneypr.utils.app_const

import ru.ohayo.moneypr.data.room.currency.CurrencyDbo

object ExchangeRates {
    // Фиксированные курсы к базовой валюте (RUB)
    // Ключ — код валюты, значение — курс *к RUB*
    private val fixedRates = mapOf(
        "RUB" to 1.0,
        "USD" to 89.5,
        "TEST" to 10.0,
        "KZT" to 0.1537,
    )

    // Получить курс для валюты (по умолчанию 1.0)
   fun getRate(currencyCode: String): Double {
        return fixedRates[currencyCode.uppercase()] ?: 1.0
    }

    // Удобный способ получить курс по CurrencyDbo
    fun getRateBD(currency: CurrencyDbo): Double {
        return getRate(currency.code)
    }

    // Получить все курсы как список (для отладки или настроек)
    fun getAllRates(): Map<String, Double> = fixedRates.toMap()

    // Проверка: является ли валюта базовой?
    fun isBaseCurrency(currencyCode: String): Boolean {
        return currencyCode.uppercase() == AppConstants.BASE_CURRENCY
    }
}