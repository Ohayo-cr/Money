package ru.ohayo.moneypr.ui.theme.screens.transactionList

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
import ru.ohayo.moneypr.domain.allEntity.Account
import ru.ohayo.moneypr.domain.allEntity.Category
import ru.ohayo.moneypr.domain.allEntity.TransactionEntity
import ru.ohayo.moneypr.utils.NumberFormatter

@Composable
fun DayTransactionGroup(
    date: String,
    transactions: List<TransactionEntity>,
    categories: List<Category>,
    accounts: List<Account>,
    onTransactionClick: (TransactionEntity) -> Unit
) {
    val income = transactions.filter { it.amount > 0 }.sumOf { it.amount }
    val expense = transactions.filter { it.amount < 0 }.sumOf { -it.amount }
    val total = income - expense
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorScheme.onSurface.copy(alpha = 0.05f),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(2.dp)
    ) {
        Column(modifier = Modifier.padding(vertical = 4.dp,horizontal = 4.dp)) {
           Row(modifier = Modifier.fillMaxWidth().wrapContentHeight(),
               Arrangement.SpaceBetween) {

            Text(
                text = date,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(start = 8.dp),
                color = colorScheme.onSurface
            )
               Text(
                   text = "Итог: ${NumberFormatter.format(total)}",
                   style = MaterialTheme.typography.titleSmall,
                   modifier = Modifier.padding(end = 8.dp),
                   color = colorScheme.onSurface,
                   maxLines = 1,
                   overflow = TextOverflow.Ellipsis
               )
           }
            Spacer(modifier = Modifier.height(4.dp))
            Column(
                modifier = Modifier.padding(horizontal = 2.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                for (transaction in transactions) {
                    TransactionItem(
                        transaction = transaction,
                        categories = categories,
                        accounts = accounts,
                        onTransactionClick = onTransactionClick
                    )
                }
            }
        }
    }
}