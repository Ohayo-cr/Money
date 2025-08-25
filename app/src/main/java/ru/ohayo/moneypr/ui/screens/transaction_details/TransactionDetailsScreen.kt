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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.ohayo.moneypr.data.room.account.AccountDbo
import ru.ohayo.moneypr.ui.component.customeButton.BackButton
import ru.ohayo.moneypr.ui.component.spacers.StandartDivider
import ru.ohayo.moneypr.ui.screens.transactionList.components.formatTimestamp
import ru.ohayo.moneypr.ui.theme.TextDisabled
import ru.ohayo.moneypr.utils.formate.NumberFormatter
import ru.ohayo.moneypr.utils.formate.formatCustomDate
import ru.ohayo.moneypr.utils.formate.formatTime

@Composable
fun TransactionDetailsScreen(
    viewModel: TransactionViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    navController: NavController
) {
    val transactionWithAccount by viewModel.transactionWithAccount.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            BackButton(navController)

            transactionWithAccount?.transaction?.let { transaction ->
                Text(
                    text = "${transaction.type}",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(8.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }

        // Показываем контент в зависимости от состояния данных
        when (val data = transactionWithAccount) {
            null -> {
Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {

}
            }
            else -> {
                val transaction = data.transaction
                val account = data.account

                val payment = when {
                    transaction.paymentAccount != null -> Pair(
                        "Счет списания",
                        transaction.paymentAccount
                    )

                    transaction.recipientAccount != null -> Pair(
                        "Счет пополнения",
                        transaction.recipientAccount
                    )

                    else -> null
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
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
                        // Отображаем аккаунт с иконкой и балансом
                        transaction.account?.let {
                            AccountDetailsRow("Account", account = account)
                            StandartDivider()
                        }

                        payment?.let {
                            DetailsText(it.first, it.second)
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