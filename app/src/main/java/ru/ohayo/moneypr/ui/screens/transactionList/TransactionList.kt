package ru.ohayo.moneypr.ui.screens.transactionList


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.ohayo.moneypr.ui.component.spacers.Spacers
import ru.ohayo.moneypr.ui.component.top_app_panel.TopAppPanel
import ru.ohayo.moneypr.ui.navController.Screen
import ru.ohayo.moneypr.ui.screens.transactionList.components.DayTransactionGroup
import ru.ohayo.moneypr.ui.screens.transactionList.components.models.LocalCategoryMap
import ru.ohayo.moneypr.ui.screens.transaction_details.TransactionViewModel


@Composable
fun TransactionsList(navController: NavController,
                     transactionListVM: TransactionListViewModel = hiltViewModel(),
                     transactionVM: TransactionViewModel
    ) {

    val groupedTransactions by transactionListVM.groupedTransactions.collectAsState()
    val categoryMap by transactionListVM.categoryMap.collectAsState()
    val scrollState = transactionListVM.scrollState

    CompositionLocalProvider(LocalCategoryMap provides categoryMap) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TopAppPanel(
                    title = "Список транзакций"
                )




                when {
                    groupedTransactions.isEmpty() -> EmptyTransactionsPlaceholder()
                    else -> LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        state = scrollState
                    ) {
                        item {
                            Spacers.Micro2()
                        }
                        groupedTransactions.forEach { dayTransactions ->
                            item {
                                DayTransactionGroup(
                                    dayTransactions = dayTransactions, // передаем весь объект
                                    onTransactionClick = { transaction ->
                                        transactionVM.setTransactionIdAndNavigate(transaction.id) {
                                            navController.navigate(Screen.DetailedTransaction.route)
                                        }
                                    }
                                )
                            }
                        }
                        item {
                            Spacers.Large200()
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun EmptyTransactionsPlaceholder() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Нет транзакций",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}