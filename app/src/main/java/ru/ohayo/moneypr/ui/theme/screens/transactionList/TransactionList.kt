package ru.ohayo.moneypr.ui.theme.screens.transactionList



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.ohayo.moneypr.domain.allEntity.TransactionEntity
import ru.ohayo.moneypr.ui.theme.screens.components.TransactionDetailsDialog
import ru.ohayo.moneypr.viewModel.AccountViewModel
import ru.ohayo.moneypr.viewModel.CategoryViewModel
import ru.ohayo.moneypr.viewModel.TransactionListViewModel
import ru.ohayo.moneypr.viewModel.TransactionViewModel



@Composable
fun TransactionsList(
    transactionlistViewModel: TransactionListViewModel = hiltViewModel(),
    categoryViewModel: CategoryViewModel = hiltViewModel(),
    transactionViewModel: TransactionViewModel = hiltViewModel(),
    accountViewModel: AccountViewModel = hiltViewModel(),

) {
    val transactions by transactionlistViewModel.transactions.collectAsState()
    val categories by categoryViewModel.categories.collectAsState()
    var selectedTransaction by remember { mutableStateOf<TransactionEntity?>(null) }
    val scrollState = transactionlistViewModel.scrollState
    val accounts by accountViewModel.accounts.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        Text(
            text = "Транзакции",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopCenter)
        )

        // Основной контент
        when {
            transactions.isEmpty() -> EmptyTransactionsPlaceholder()
            else -> LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 48.dp), // Пробел под заголовок
                contentPadding = PaddingValues(horizontal = 4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                state = scrollState
            ) {
                items(transactions) { transaction ->
                    TransactionItem(
                        transaction = transaction,
                        categories = categories,
                        accounts = accounts,
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