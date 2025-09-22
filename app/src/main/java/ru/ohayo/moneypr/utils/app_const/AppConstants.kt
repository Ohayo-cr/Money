package ru.ohayo.moneypr.utils.app_const

object AppConstants {
    const val BASE_CURRENCY = "RUB"
}
object CurrencySymbols {
    private val symbolMap = mapOf(
        "RUB" to "₽",
        "USD" to "$",
        "EUR" to "€",
        "GBP" to "£",
        "CNY" to "¥",
        "KZT" to "₸",
        "UAH" to "₴",
        "BYN" to "Br",
        // добавь другие по необходимости
    )

    fun getSymbol(currencyCode: String): String {
        return symbolMap[currencyCode] ?: currencyCode // если нет символа — вернём код
    }
}