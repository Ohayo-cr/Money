package ru.ohayo.moneypr.ui.screens.transactionList.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.ohayo.moneypr.data.room.category.CategoryDbo
import ru.ohayo.moneypr.data.room.transaction.TransactionDbo
import ru.ohayo.moneypr.ui.component.categoryIcon.CategoryIcon
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TransactionItem(
    transaction: TransactionDbo,
    categories: List<CategoryDbo>,
    onTransactionClick: (TransactionDbo) -> Unit,
) {
    val accountName = when {
        transaction.paymentAccount != null -> transaction.paymentAccount
        transaction.recipientAccount != null -> transaction.recipientAccount
        else -> "non account"
    }
    val category = getCategoryByName(categories, transaction.category)
    Box(modifier = Modifier.fillMaxWidth()
        .clickable { onTransactionClick(transaction) }
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            category?.let { cat ->
                CategoryIcon(
                    iconResId = cat.iconResId,
                    backgroundColor = Color(cat.color),
                    modifier = Modifier.size(54.dp),
                    padding = 6.dp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .padding(2.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    val categoryName = transaction.category
                    val formattedCategory = if (categoryName.length > 16) {
                        categoryName.substring(0, 16) + "-\n" + categoryName.substring(16)
                    } else {
                        categoryName
                    }

                    Text(
                        text = transaction.category,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2
                    )

                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    val amountText = buildAmountText(transaction)
                    Text(
                        text = amountText,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(text = accountName, fontSize = 12.sp,   color = MaterialTheme.colorScheme.onPrimary)


                }
            }
        }
    }
}

@Composable
fun getCategoryByName(categories: List<CategoryDbo>, categoryName: String): CategoryDbo? {
    val result by remember(categories, categoryName) {
        mutableStateOf(categories.find { it.categoryName == categoryName })
    }
    return result
}
fun formatTimestamp(timestamp: Long): String {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    return dateFormat.format(Date(timestamp))
}