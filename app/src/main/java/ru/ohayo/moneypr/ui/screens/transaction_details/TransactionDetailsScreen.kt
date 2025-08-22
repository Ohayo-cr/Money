package ru.ohayo.moneypr.ui.screens.transaction_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.ohayo.moneypr.data.room.transaction.TransactionDbo
import ru.ohayo.moneypr.ui.component.customeButton.BackButton
import ru.ohayo.moneypr.ui.screens.transactionList.components.formatTimestamp
import ru.ohayo.moneypr.utils.formate.NumberFormatter

@Composable
fun TransactionDetailsScreen(
    transaction: TransactionDbo,
    onBackClick: () -> Unit,
    navController: NavController
) {
    val payment = when {
        transaction.paymentAccount != null -> "Счет списания: ${transaction.paymentAccount}"
        transaction.recipientAccount != null -> "Счет пополнения: ${transaction.recipientAccount}"
        else -> null
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BackButton(navController)

        Text(
            text = "Детали транзакции",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 8.dp)
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(top = 64.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Сумма: ${NumberFormatter.format(transaction.amount)}")
            Text(text = "Счёт: ${transaction.account}")
            Text(text = "Категория: ${transaction.category}") // Прямо используем
            Text(text = "Дата: ${formatTimestamp(transaction.timestamp)}")
            Text(text = "Создано: ${formatTimestamp(transaction.createdAt)}")
            transaction.note?.let {
                Text(text = "Описание: $it")
            }
            payment?.let { Text(text = it) }
        }

        TextButton(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            Text("Закрыть", color = MaterialTheme.colorScheme.primary)
        }
    }
}