package ru.ohayo.moneypr.data.repository

interface ExpressionRepository {
    fun saveExpression(expression: String, result: String)
    fun getHistory(): List<Pair<String, String>>
}