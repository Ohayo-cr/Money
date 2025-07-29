package ru.ohayo.moneypr.ui.screens.transactionList



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.ohayo.moneypr.data.room.transaction.TransactionDbo
import ru.ohayo.moneypr.ui.screens.transactionList.components.DayTransactionGroup
import ru.ohayo.moneypr.ui.screens.transactionList.components.TransactionDetailsDialog
import ru.ohayo.moneypr.ui.screens.transactionList.components.groupTransactionsByDate
import ru.ohayo.moneypr.ui.screens.accountScreen.AccountViewModel
import ru.ohayo.moneypr.ui.screens.categoryList.CategoryViewModel
import ru.ohayo.moneypr.ui.screens.addTransaction.TransactionViewModel



@Composable
fun TransactionsList(
    transactionListVM: TransactionListViewModel = hiltViewModel(),
    categoryViewModel: CategoryViewModel = hiltViewModel(),
    transactionViewModel: TransactionViewModel = hiltViewModel(),
    accountViewModel: AccountViewModel = hiltViewModel(),

    ) {
    val transactions by transactionListVM.transactions.collectAsState()
    val categories by categoryViewModel.categories.collectAsState()
    var selectedTransaction by remember { mutableStateOf<TransactionDbo?>(null) }
    val scrollState = transactionListVM.scrollState
    val accounts by accountViewModel.accounts.collectAsState()
    val groupedTransactions = remember(transactions) {
        if (transactions.isNotEmpty()) groupTransactionsByDate(transactions) else emptyMap()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "List of transactions",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp),
                color = MaterialTheme.colorScheme.onPrimary

            )

            when {
                transactions.isEmpty() -> EmptyTransactionsPlaceholder()
                else -> LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    state = scrollState
                ) {
                    groupedTransactions.forEach { (date, dayTransactions) ->
                        item {
                            DayTransactionGroup(
                                date = date,
                                transactions = dayTransactions,
                                categories = categories,
                                account = accounts,
                                onTransactionClick = { selectedTransaction = it }
                            )

                        }

                    }
                    item {
                        Spacer(
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }


        selectedTransaction?.let { transaction ->
            TransactionDetailsDialog(
                transaction = transaction,
                categories = categories,
                onDismiss = { selectedTransaction = null },
                transactionViewModel = transactionViewModel
            )
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