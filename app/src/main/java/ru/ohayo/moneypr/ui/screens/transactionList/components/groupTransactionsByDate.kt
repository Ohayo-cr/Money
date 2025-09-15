package ru.ohayo.moneypr.ui.screens.transactionList.components

import ru.ohayo.moneypr.data.room.transaction.TransactionDbo
import ru.ohayo.moneypr.ui.screens.transactionList.components.models.DayTransactions
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun groupTransactionsByDate(transactions: List<TransactionDbo>): List<DayTransactions> {
    val now = Calendar.getInstance()
    val currentYear = now.get(Calendar.YEAR)



    return transactions
        .groupBy {
            val transactionDate = Calendar.getInstance().apply {
                time = Date(it.timestamp)
            }

            if (
                transactionDate.get(Calendar.YEAR) == now.get(Calendar.YEAR) &&
                transactionDate.get(Calendar.MONTH) == now.get(Calendar.MONTH) &&
                transactionDate.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH)
            ) {
                "Сегодня"
            } else {
                val yesterday = Calendar.getInstance().apply {
                    time = now.time
                    add(Calendar.DAY_OF_MONTH, -1)
                }

                if (
                    transactionDate.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR) &&
                    transactionDate.get(Calendar.MONTH) == yesterday.get(Calendar.MONTH) &&
                    transactionDate.get(Calendar.DAY_OF_MONTH) == yesterday.get(Calendar.DAY_OF_MONTH)
                ) {
                    "Вчера"
                } else {
                    val sdf = SimpleDateFormat("d MMMM, EEEE", Locale.getDefault())
                    var formattedDate = sdf.format(transactionDate.time)

                    if (transactionDate.get(Calendar.YEAR) != currentYear) {
                        formattedDate += " ${transactionDate.get(Calendar.YEAR)} "
                    }

                    formattedDate
                }
            }
        }
        .map { (date, dayTransactions) ->
            DayTransactions.daySum(date, dayTransactions)
        }
        .sortedWith(compareByDescending { dayTransaction ->
            // Сортировка по дате транзакций (самые новые первыми)
            dayTransaction.transactions.firstOrNull()?.timestamp ?: 0L
        })
}