package ru.ohayo.moneypr.ui.component.customeKeyboard


sealed class CalculationResult {
    data class Success(val value: String) : CalculationResult()
    data class Error(val message: String) : CalculationResult()
}