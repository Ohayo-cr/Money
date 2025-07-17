package ru.ohayo.moneypr.repository

class CalculatorRepositoryImpl : CalculatorRepository {
    private val history = mutableListOf<Pair<String, String>>()

    override fun saveExpression(expression: String, result: String) {
        history.add(Pair(expression, result))
    }

}