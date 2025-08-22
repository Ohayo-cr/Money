package ru.ohayo.moneypr.ui.screens.transaction_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
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
    transaction: TransactionDbo?,
    onBackClick: () -> Unit,
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            BackButton(navController)

            if (transaction != null) {
                Text(
                    text = "${transaction.type}",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(8.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }

        // Показываем контент в зависимости от состояния данных
        when (transaction) {
            null -> {
                // Показываем загрузку или пустой контейнер
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {}
            }
            else -> {
                // Показываем данные транзакции
                val payment = when {
                    transaction.paymentAccount != null -> "Счет списания: ${transaction.paymentAccount}"
                    transaction.recipientAccount != null -> "Счет пополнения: ${transaction.recipientAccount}"
                    else -> null
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 64.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    DetailsText("Сумма: ${NumberFormatter.format(transaction.amount)}")
                    DetailsText("Счёт: ${transaction.account}")
                    DetailsText("Категория: ${transaction.category}")
                    DetailsText("Дата: ${formatTimestamp(transaction.timestamp)}")
                    DetailsText("Создано: ${formatTimestamp(transaction.createdAt)}")
                    transaction.note?.let {
                        DetailsText("Описание: $it")
                    }
                    payment?.let {
                        DetailsText(it)
                    }
                }
            }
        }

        TextButton(
            onClick = onBackClick,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Закрыть", color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}

@Composable
private fun DetailsText(s: String) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column {
            Text(
                text = s, // Исправлено: было String(), стало s
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxWidth()
            )
            Divider(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
            )
        }
    }
}