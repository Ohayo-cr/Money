package ru.ohayo.moneypr.ui.screens.transaction_details


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.ohayo.moneypr.R
import ru.ohayo.moneypr.data.room.account.AccountDbo
import ru.ohayo.moneypr.ui.component.spacers.Spacers
import ru.ohayo.moneypr.ui.component.spacers.StandartDivider
import ru.ohayo.moneypr.ui.component.top_app_panel.TopAppPanel
import ru.ohayo.moneypr.ui.theme.TextDisabled
import ru.ohayo.moneypr.utils.formate.NumberFormatter
import ru.ohayo.moneypr.utils.formate.formatCustomDate
import ru.ohayo.moneypr.utils.formate.formatTime
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DetailedTransaction(
    navController: NavController,
    onBackClick: () -> Unit,
    transactionVM: TransactionViewModel
) {
    val transactionWithAccount by transactionVM.transactionWithAccount.collectAsState()
    val DboTransaction = transactionWithAccount?.transaction
    var showDeleteDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        transactionWithAccount?.transaction?.let { transaction ->
            TopAppPanel(
                title = "${transaction.type}",
                showBackButton = true,
                navController = navController,
                leftIcon1 = painterResource(id = R.drawable.ic_edit_main),
                onIconClick1 = {},
                rightIcon2 = painterResource(id = R.drawable.ic_delete),
                onIconClick2 = {showDeleteDialog = true}
            )
        }

        // Показываем контент в зависимости от состояния данных
        when (val data = transactionWithAccount) {
            null -> {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)) {

                }
            }
            else -> {
                val transaction = data.transaction
                val account = data.account
                val paymentAccount = data.paymentAccount
                val recipientAccount = data.recipientAccount


                    Spacers.Micro2()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp, vertical = 8.dp)
                        .background(
                            color = MaterialTheme.colorScheme.secondary,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        DetailsText("Sum", "${NumberFormatter.format(transaction.amount)} ${transaction.currency}" )
                        StandartDivider()
                        transaction.account?.let {
                            AccountDetailsRow("Account", account = account)
                            StandartDivider()
                        }

                        transaction.paymentAccount?.let {
                            AccountDetailsRow("Счет списания", account = paymentAccount )
                            StandartDivider()
                        }
                        transaction.recipientAccount?.let {
                            AccountDetailsRow("Счет зачисления", account = recipientAccount)
                            StandartDivider()
                        }

                        DetailsText("Category", transaction.category)
                        StandartDivider()
                        DetailsText("Date", formatCustomDate(transaction.timestamp))
                        StandartDivider()
                        DetailsText("Time", formatTime(transaction.timestamp))
                        StandartDivider()
                        DetailsText(
                            "Note",
                            transaction.note ?: "Tap add note",
                            transaction.note == null
                        )

                    }
                }
                Text(
                    text = "Created on ${formatTimestamp(transaction.createdAt)}",
                    textAlign = TextAlign.Center,
                    color = TextDisabled,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun DetailsText(title: String, mainText: String, isPlaceholder: Boolean = false) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp)) {
        Column {
            Text(
                text = title,
                color = TextDisabled,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = mainText,
                color = if (isPlaceholder) TextDisabled
                else MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxWidth()
            )

        }
    }
}

@Composable
private fun AccountDetailsRow(title: String, account: AccountDbo?) {
    if (account == null) return
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Column(     modifier = Modifier
            .fillMaxWidth()) {


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = title,
                        color = TextDisabled
                    )
                    Text(
                        text = "${account.name} (${NumberFormatter.format(account.balance)} ${account.currency})",
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                }
                account.icon?.let { iconRes ->
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

        }
    }
}
@Composable
fun formatTimestamp(timestamp: Long): String {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    return dateFormat.format(Date(timestamp))
}