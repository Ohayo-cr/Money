package ru.ohayo.moneypr.domain.use_case


import ru.ohayo.moneypr.ui.screens.currencyScreen.components.CurrencyModel
import java.util.*
import javax.inject.Inject

class GetCurrencyUseCase @Inject constructor() {

    operator fun invoke(): List<CurrencyModel> {
        val currencyModels = mutableSetOf<CurrencyModel>()
        val allLocales = Locale.getAvailableLocales()

        allLocales.forEach { locale ->
            try {
                val currency = Currency.getInstance(locale)
                val country = locale.country

                if (country.isNotEmpty() && currency.currencyCode.isNotEmpty()) {
                    val currencyModel = CurrencyModel(
                        countryName = locale.displayCountry,
                        currencyCode = currency.currencyCode,
                        currencySymbol = currency.symbol,
                        flagEmoji = getFlagEmoji(country)
                    )
                    currencyModels.add(currencyModel)
                }
            } catch (e: Exception) {
                // Некоторые локали могут не иметь валюты или некорректный код страны
            }
        }

        return currencyModels.sortedBy { it.countryName }
    }

    private fun getFlagEmoji(countryCode: String): String {
        // Проверяем, что код страны состоит из 2 букв
        if (countryCode.length != 2) return ""

        return try {
            val firstLetter = Character.codePointAt(countryCode, 0) - 0x41 + 0x1F1E6
            val secondLetter = Character.codePointAt(countryCode, 1) - 0x41 + 0x1F1E6
            String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
        } catch (e: Exception) {
            "" // Возвращаем пустую строку если не удалось создать эмодзи
        }
    }
}