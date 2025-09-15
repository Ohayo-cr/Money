package ru.ohayo.moneypr.ui.screens.transactionList.components.models

import ru.ohayo.moneypr.data.room.category.CategoryType
import ru.ohayo.moneypr.data.room.transaction.TransactionDbo

data class DayTransactions(
    val date: String,
    val transactions: List<TransactionDbo>,
    val total: Map<String, Double>
) {
    companion object {
        fun daySum(date: String, transactions: List<TransactionDbo>): DayTransactions {
            val financialTransactions = transactions.filter {
                it.type == CategoryType.Income || it.type == CategoryType.Expense
            }

            val totalsByCurrency = financialTransactions
                .groupBy { it.currency }
                .mapValues { (_, transactionsForCurrency) ->
                    val income = transactionsForCurrency
                        .filter { it.type == CategoryType.Income }
                        .sumOf { it.amount }
                    val expense = transactionsForCurrency
                        .filter { it.type == CategoryType.Expense }
                        .sumOf { it.amount }
                    income + expense
                }

            return DayTransactions(
                date = date,
                transactions = transactions,
                total = totalsByCurrency
            )
        }
    }
}

