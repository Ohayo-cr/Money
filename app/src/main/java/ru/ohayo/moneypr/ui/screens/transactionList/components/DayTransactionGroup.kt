package ru.ohayo.moneypr.ui.screens.transactionList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import ru.ohayo.moneypr.data.room.category.CategoryDbo
import ru.ohayo.moneypr.data.room.transaction.TransactionDbo
import ru.ohayo.moneypr.ui.theme.TextDisabled
import ru.ohayo.moneypr.utils.formate.NumberFormatter

@Composable
fun DayTransactionGroup(
    date: String,
    transactions: List<TransactionDbo>,
    categoryMap: Map<String, CategoryDbo>,
    onTransactionClick: (TransactionDbo) -> Unit
) {
    val income = transactions.filter { it.amount > 0 }.sumOf { it.amount }
    val expense = transactions.filter { it.amount < 0 }.sumOf { -it.amount }
    val total = income - expense
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorScheme.secondary,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(2.dp)
    ) {
        Column(modifier = Modifier.padding(vertical = 4.dp,horizontal = 4.dp)) {
           Row(modifier = Modifier
               .fillMaxWidth()
               .padding(bottom = 2.dp)
               .wrapContentHeight(),
               Arrangement.SpaceBetween) {

            Text(
                text = date,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(start = 8.dp),
                color = TextDisabled
            )
               Text(
                   text = NumberFormatter.format(total),
                   style = MaterialTheme.typography.titleSmall,
                   modifier = Modifier.padding(end = 8.dp),
                   color = TextDisabled,
                   maxLines = 1,
                   overflow = TextOverflow.Ellipsis
               )
           }
            Divider(modifier = Modifier.padding(vertical = 2.dp), color = TextDisabled)
            Spacer(modifier = Modifier.height(4.dp))
            Column(
                modifier = Modifier.padding(horizontal = 2.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                for (transaction in transactions) {
                    TransactionItem(
                        transaction = transaction,
                        categoryMap = categoryMap,
                        onTransactionClick = onTransactionClick
                    )

                }
            }
        }
    }
}