package ru.ohayo.moneypr.repository

interface CalculatorRepository {
    fun saveExpression(expression: String, result: String)

}