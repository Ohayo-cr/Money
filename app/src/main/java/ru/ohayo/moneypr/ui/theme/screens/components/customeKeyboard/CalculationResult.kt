package ru.ohayo.moneypr.ui.theme.screens.components.customeKeyboard

// Domain Layer: Модель результата
sealed class CalculationResult {
    data class Success(val value: String) : CalculationResult()
    data class Error(val message: String) : CalculationResult()
}