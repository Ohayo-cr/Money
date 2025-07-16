package ru.ohayo.moneypr.repository

interface ExpressionRepository {
    fun saveExpression(expression: String, result: String)

}