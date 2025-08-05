package ru.ohayo.moneypr.ui.screens.currencyScreen.components

data class CurrencyModel(
    val countryName: String,
    val currencyCode: String,
    val currencySymbol: String,
    val flagEmoji: String = ""
)