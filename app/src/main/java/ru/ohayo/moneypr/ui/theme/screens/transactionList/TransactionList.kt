package ru.ohayo.moneypr.ui.theme.screens.transactionList



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.ohayo.moneypr.domain.allEntity.TransactionEntity
import ru.ohayo.moneypr.domain.allEntity.Category
import ru.ohayo.moneypr.ui.theme.screens.components.TransactionDetailsDialog
import ru.ohayo.moneypr.utils.NumberFormatter
import ru.ohayo.moneypr.viewModel.CategoryViewModel
import ru.ohayo.moneypr.viewModel.TransactionListViewModel
import ru.ohayo.moneypr.viewModel.TransactionViewModel

import java.text.SimpleDateFormat
import java.util.*


@Composable
fun TransactionsList(
    transactionlistViewModel: TransactionListViewModel = hiltViewModel(),
    categoryViewModel: CategoryViewModel = hiltViewModel(),
    transactionViewModel: TransactionViewModel = hiltViewModel()
) {
    val transactions by transactionlistViewModel.transactions.collectAsState()
    val categories by categoryViewModel.categories.collectAsState()
    var selectedTransaction by remember { mutableStateOf<TransactionEntity?>(null) }
    val scrollState = transactionlistViewModel.scrollState

    Box(modifier = Modifier.fillMaxSize()) {

        Text(
            text = "Транзакции",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
        )

        // Основной контент
        when {
            transactions.isEmpty() -> EmptyTransactionsPlaceholder()
            else -> LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 48.dp), // Пробел под заголовок
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                state = scrollState
            ) {
                items(transactions) { transaction ->
                    TransactionItem(
                        transaction = transaction,
                        categories = categories,
                        onTransactionClick = { selectedTransaction = it }
                    )
                }
            }
        }

        // Диалог с деталями транзакции
        selectedTransaction?.let { transaction ->
            TransactionDetailsDialog(
                transaction = transaction,
                categories = categories,
                onDismiss = { selectedTransaction = null },
                transactionViewModel = transactionViewModel
            )
        }
    }
}

@Composable
private fun EmptyTransactionsPlaceholder() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp), // Отступ для заголовка
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Нет транзакций",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TransactionItem(
    transaction: TransactionEntity,
    categories: List<Category>,
    onTransactionClick: (TransactionEntity) -> Unit
) {
    Card(
        onClick = { onTransactionClick(transaction) },
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Сумма: ${NumberFormatter.format(transaction.amount)} ${transaction.currency}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Категория: ${getCategoryName(categories, transaction.category)}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Дата: ${formatTimestamp(transaction.timestamp)}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun getCategoryName(
    categories: List<Category>,
    categoryId: Long
): String {
    val category = categories.find { it.id == categoryId }
    return remember(category) { category?.name ?: "Неизвестная категория" }
}

fun formatTimestamp(timestamp: Long): String {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    return dateFormat.format(Date(timestamp))
}