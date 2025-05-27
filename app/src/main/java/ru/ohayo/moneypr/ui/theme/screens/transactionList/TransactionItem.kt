package ru.ohayo.moneypr.ui.theme.screens.transactionList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat.getCategory
import ru.ohayo.moneypr.domain.allEntity.Account
import ru.ohayo.moneypr.domain.allEntity.Category
import ru.ohayo.moneypr.domain.allEntity.TransactionEntity
import ru.ohayo.moneypr.ui.theme.screens.components.componentsCategory.CategoryIcon
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionItem(
    transaction: TransactionEntity,
    categories: List<Category>,
    accounts: List<Account>,
    onTransactionClick: (TransactionEntity) -> Unit,
) {
    val category = getCategory(categories, transaction.category)
    Box(modifier = Modifier.fillMaxWidth()
        .clickable { onTransactionClick(transaction) }
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Иконка категории слева
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
                    val categoryName = category?.name ?: "Неизвестная категория"
                    val formattedCategory = if (categoryName.length > 16) {
                        categoryName.substring(0, 16) + "-\n" + categoryName.substring(16)
                    } else {
                        categoryName
                    }

                    Text(
                        text = formattedCategory,
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
                    // Отображение названия счета
                    val accountTransaction = if (transaction.toAccount != null) {
                        getAccount(accounts, transaction.toAccount)?.name ?: "Неизвестный"
                    } else {
                        getAccount(accounts, transaction.fromAccount)?.name ?: "Неизвестный"
                    }
                    Text(text = "$accountTransaction", fontSize = 12.sp)

                }
            }
        }
    }
}

@Composable
fun getCategory(categories: List<Category>, categoryId: Long): Category? {
    val result by remember(categories, categoryId) {
        mutableStateOf(categories.find { it.id == categoryId })
    }
    return result
}
fun formatTimestamp(timestamp: Long): String {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    return dateFormat.format(Date(timestamp))
}