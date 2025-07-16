package ru.ohayo.moneypr.repository

class ExpressionRepositoryImpl : ExpressionRepository {
    private val history = mutableListOf<Pair<String, String>>()

    override fun saveExpression(expression: String, result: String) {
        history.add(Pair(expression, result))
    }

}