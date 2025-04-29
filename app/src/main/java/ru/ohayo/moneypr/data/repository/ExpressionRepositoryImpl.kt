package ru.ohayo.moneypr.data.repository

class ExpressionRepositoryImpl : ExpressionRepository {
    private val history = mutableListOf<Pair<String, String>>()

    override fun saveExpression(expression: String, result: String) {
        history.add(Pair(expression, result))
    }

    override fun getHistory(): List<Pair<String, String>> {
        return history.toList()
    }
}