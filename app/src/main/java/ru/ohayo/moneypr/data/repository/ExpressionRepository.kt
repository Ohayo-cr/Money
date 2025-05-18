package ru.ohayo.moneypr.data.repository

interface ExpressionRepository {
    fun saveExpression(expression: String, result: String)

}