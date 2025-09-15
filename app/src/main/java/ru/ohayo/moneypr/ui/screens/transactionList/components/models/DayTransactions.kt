package ru.ohayo.moneypr.ui.screens.transactionList.components.models

import ru.ohayo.moneypr.data.room.category.CategoryType
import ru.ohayo.moneypr.data.room.transaction.TransactionDbo

data class DayTransactions(
    val date: String,
    val transactions: List<TransactionDbo>,
    val total: Double
) {
    companion object {
        fun daySum (date: String, transactions: List<TransactionDbo>): DayTransactions {

            val financialTransactions = transactions.filter {
                it.type == CategoryType.Income || it.type == CategoryType.Expense
            }

            val income = financialTransactions.filter { it.type == CategoryType.Income }.sumOf { it.amount }
            val expense = financialTransactions.filter { it.type == CategoryType.Expense }.sumOf { it.amount }
            val total = income + expense

            return DayTransactions(
                date = date,
                transactions = transactions,
                total = total
            )
        }
    }
}
