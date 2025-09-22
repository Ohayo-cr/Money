package ru.ohayo.moneypr.ui.screens.transactionList.components.models

import ru.ohayo.moneypr.data.room.category.CategoryType
import ru.ohayo.moneypr.data.room.transaction.TransactionDbo
import ru.ohayo.moneypr.utils.app_const.AppConstants
import ru.ohayo.moneypr.utils.app_const.CurrencySymbols
import ru.ohayo.moneypr.utils.formate.NumberFormatter

data class DayTransactions(
    val date: String,
    val transactions: List<TransactionDbo>,
    val totalFormatted: String // ✅ Готовая строка для отображения
) {
    companion object {
        fun daySum(date: String, transactions: List<TransactionDbo>): DayTransactions {
            val totalSum = transactions
                .filter { it.type == CategoryType.Income || it.type == CategoryType.Expense }
                .sumOf { it.amount * it.exchangeRate }

            val baseCurrency = AppConstants.BASE_CURRENCY
            val formatted = NumberFormatter.format(totalSum, true) +
                    " " + CurrencySymbols.getSymbol(baseCurrency)

            return DayTransactions(
                date = date,
                transactions = transactions,
                totalFormatted = formatted
            )
        }
    }
}

