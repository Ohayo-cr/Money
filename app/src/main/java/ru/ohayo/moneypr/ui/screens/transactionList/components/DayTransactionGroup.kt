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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.data.room.transaction.TransactionDbo
import ru.ohayo.moneypr.ui.screens.transactionList.components.models.DayTransactions
import ru.ohayo.moneypr.ui.theme.TextDisabled


@Composable
fun DayTransactionGroup(
    dayTransactions: DayTransactions,
    onTransactionClick: (TransactionDbo) -> Unit
) {


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


                Text(
                    text = dayTransactions.totalFormatted,
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
                            onTransactionClick = onTransactionClick,
                            isFirst = index == 0,
                            isLast = index == transactions.size - 1
                        )

                    }
                }
            }
        }
    }
