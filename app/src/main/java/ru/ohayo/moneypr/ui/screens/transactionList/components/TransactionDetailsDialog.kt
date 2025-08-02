package ru.ohayo.moneypr.ui.screens.transactionList.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.data.room.transaction.TransactionDbo
import ru.ohayo.moneypr.data.room.category.CategoryDbo
import ru.ohayo.moneypr.utils.formate.NumberFormatter
import ru.ohayo.moneypr.ui.screens.addTransaction.TransactionViewModel

@Composable
fun TransactionDetailsDialog(
    transaction: TransactionDbo,
    categories: List<CategoryDbo>, // Принимаем список категорий
    onDismiss: () -> Unit,

) {

    val category = getCategoryByName(categories, transaction.category)
    val payment = when {
        transaction.paymentAccount != null -> "Счет списания: ${transaction.paymentAccount}"
        transaction.recipientAccount != null -> "Счет пополнения: ${transaction.recipientAccount}"
        else -> null
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Детали транзакции") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Сумма: ${NumberFormatter.format(transaction.amount)}")
                Text(text = "Счёт: ${transaction.account}")
                Text(text = "Категория: ${category?.categoryName ?: "Неизвестная категория"}")
                Text(text = "Дата: ${
                    formatTimestamp(
                        transaction.timestamp
                    )
                }")
                transaction.note?.let {
                    Text(text = "Описание: $it")
                }
                payment?.let { Text(text = it) }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Закрыть", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    )
}