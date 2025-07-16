package ru.ohayo.moneypr.ui.screens.transactionList.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.data.room.transaction.TransactionDbo
import ru.ohayo.moneypr.data.room.category.CategoryDbo
import ru.ohayo.moneypr.models.formate.NumberFormatter
import ru.ohayo.moneypr.ui.screens.addTransaction.TransactionViewModel

@Composable
fun TransactionDetailsDialog(
    transaction: TransactionDbo,
    categories: List<CategoryDbo>, // Принимаем список категорий
    onDismiss: () -> Unit,
    transactionViewModel: TransactionViewModel // Принимаем ViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val category = getCategory(categories, transaction.categoryDbo)
    // Состояния для хранения названий счетов
    var fromAccountName by remember { mutableStateOf<String?>(null) }
    var toAccountName by remember { mutableStateOf<String?>(null) }

    // Загрузка названий счетов при создании диалога
    LaunchedEffect(transaction.fromAccount, transaction.toAccount) {
        transaction.fromAccount?.let { accountId ->
            coroutineScope.launch {
                fromAccountName = transactionViewModel.getAccountName(accountId)
            }
        }
        transaction.toAccount?.let { accountId ->
            coroutineScope.launch {
                toAccountName = transactionViewModel.getAccountName(accountId)
            }
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Детали транзакции") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Сумма: ${NumberFormatter.format(transaction.amount)}")
                Text(text = "Категория:${category?.categoryName ?: "Неизвестная категория"}")
                Text(text = "Дата: ${
                    formatTimestamp(
                        transaction.timestamp
                    )
                }")
                transaction.content?.let {
                    Text(text = "Описание: $it")
                }
                fromAccountName?.let {
                    Text(text = "Счет списания: $it")
                } ?: transaction.fromAccount?.let {
                    Text(text = "Счет списания: $it (название не найдено)")
                }
                toAccountName?.let {
                    Text(text = "Счет зачисления: $it")
                } ?: transaction.toAccount?.let {
                    Text(text = "Счет зачисления: $it (название не найдено)")
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Закрыть")
            }
        }
    )
}