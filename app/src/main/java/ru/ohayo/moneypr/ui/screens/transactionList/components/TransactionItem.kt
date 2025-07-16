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
import ru.ohayo.moneypr.domain.allEntity.AccountDbo
import ru.ohayo.moneypr.domain.allEntity.CategoryDbo
import ru.ohayo.moneypr.domain.allEntity.TransactionDbo
import ru.ohayo.moneypr.ui.component.categoryIcon.CategoryIcon
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TransactionItem(
    transaction: TransactionDbo,
    categories: List<CategoryDbo>,
    accountDbos: List<AccountDbo>,
    onTransactionClick: (TransactionDbo) -> Unit,
) {
    val category = getCategory(categories, transaction.categoryDbo)
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
                    val categoryName = category?.categoryName ?: "Неизвестная категория"
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
                        getAccount(accountDbos, transaction.toAccount)?.name ?: "Неизвестный"
                    } else {
                        getAccount(accountDbos, transaction.fromAccount)?.name ?: "Неизвестный"
                    }
                    Text(text = accountTransaction, fontSize = 12.sp,   color = MaterialTheme.colorScheme.onPrimary)

                }
            }
        }
    }
}

@Composable
fun getCategory(categories: List<CategoryDbo>, categoryId: Long): CategoryDbo? {
    val result by remember(categories, categoryId) {
        mutableStateOf(categories.find { it.id == categoryId })
    }
    return result
}
fun formatTimestamp(timestamp: Long): String {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    return dateFormat.format(Date(timestamp))
}