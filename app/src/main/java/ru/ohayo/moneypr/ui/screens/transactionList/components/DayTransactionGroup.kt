package ru.ohayo.moneypr.ui.screens.transactionList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.ohayo.moneypr.data.room.category.CategoryDbo
import ru.ohayo.moneypr.data.room.transaction.TransactionDbo
import ru.ohayo.moneypr.ui.screens.transactionList.components.models.DayTransactions
import ru.ohayo.moneypr.ui.theme.TextDisabled
import ru.ohayo.moneypr.utils.formate.NumberFormatter

@Composable
fun DayTransactionGroup(
    dayTransactions: DayTransactions,
    categoryMap: Map<String, CategoryDbo>,
    onTransactionClick: (TransactionDbo) -> Unit
) {
    val currencyList = remember(dayTransactions.total) {
        dayTransactions.total.toList()
    }

    var currentIndex by remember { mutableIntStateOf(0) }

    // Переключение валют каждые 3 секунды
    LaunchedEffect(currencyList.size) {
        if (currencyList.size > 1) {
            while (true) {
                delay(3000)
                currentIndex = (currentIndex + 1) % currencyList.size
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorScheme.secondary,
                shape = RoundedCornerShape(10.dp)
            )

    ) {
        Column(modifier = Modifier) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 4.dp)
                    .wrapContentHeight(),
                Arrangement.SpaceBetween
            ) {

                Text(
                    text = dayTransactions.date,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = 8.dp),
                    color = TextDisabled
                )
                // Отображение валюты
                val currencyText = if (currencyList.isEmpty()) {
                    ""
                } else {
                    val (currency, amount) = currencyList[currentIndex]
                    "${NumberFormatter.format(amount, true)} $currency"
                }

                Text(
                    text = currencyText,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(end = 8.dp),
                    color = TextDisabled,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
                Divider(modifier = Modifier.padding(horizontal = 6.dp), color = TextDisabled)

                Column(
                    modifier = Modifier,

                ) {
                    val transactions = dayTransactions.transactions
                    for ((index, transaction) in transactions.withIndex()) {
                        TransactionItem(
                            transaction = transaction,
                            categoryMap = categoryMap,
                            onTransactionClick = onTransactionClick,
                            isFirst = index == 0,
                            isLast = index == transactions.size - 1
                        )

                    }
                }
            }
        }
    }
