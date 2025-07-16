package ru.ohayo.moneypr.ui.component.customeKeyboard

// Domain Layer: Модель результата
sealed class CalculationResult {
    data class Success(val value: String) : CalculationResult()
    data class Error(val message: String) : CalculationResult()
}