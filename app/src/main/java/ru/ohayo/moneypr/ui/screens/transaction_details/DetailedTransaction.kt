package ru.ohayo.moneypr.ui.screens.transaction_details


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.ohayo.moneypr.R
import ru.ohayo.moneypr.data.room.account.AccountDbo
import ru.ohayo.moneypr.data.room.category.CategoryDbo
import ru.ohayo.moneypr.data.room.category.CategoryType
import ru.ohayo.moneypr.ui.component.categoryIcon.CategoryIcon
import ru.ohayo.moneypr.ui.component.spacers.Spacers
import ru.ohayo.moneypr.ui.component.spacers.StandardDivider
import ru.ohayo.moneypr.ui.component.top_app_panel.TopAppPanel
import ru.ohayo.moneypr.ui.theme.TextDisabled
import ru.ohayo.moneypr.utils.app_const.AppConstants
import ru.ohayo.moneypr.utils.formate.NumberFormatter
import ru.ohayo.moneypr.utils.formate.formatCustomDate
import ru.ohayo.moneypr.utils.formate.formatTime
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DetailedTransaction(
    navController: NavController,
    transactionVM: TransactionViewModel
) {
    val transactionWithAccount by transactionVM.transactionWithAccount.collectAsState()
    var showDeleteDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
            TopAppPanel(
                title = "Details transaction",
                showBackButton = true,
                navController = navController,
                leftIcon1 = painterResource(id = R.drawable.ic_edit_main),
                onIconClick1 = {},
                rightIcon2 = painterResource(id = R.drawable.ic_delete),
                onIconClick2 = {showDeleteDialog = true}
            )

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
                val category = data.category


                    Spacers.Micro2()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp, vertical = 8.dp)
                        .background(
                            color = MaterialTheme.colorScheme.secondary,
                            shape = RoundedCornerShape(10.dp)
                        )

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        DetailsText(
                            "Sum",
                            "${NumberFormatter.format(transaction.amount, transaction.type == CategoryType.Income)} ${transaction.currency}"
                        )
                        StandardDivider()
                        if (transaction.exchangeRate != 1.0) {
                            DetailsText("ExchangeRate",transaction.exchangeRate.toString())
                            StandardDivider()
                            DetailsText("Sum in base currency",
                                "≈ ${NumberFormatter.format(transaction.amount * transaction.exchangeRate, true)} ${AppConstants.BASE_CURRENCY}")
                            StandardDivider()
                        }
                        DetailsText(
                            "Type transaction",transaction.type.toString()
                        )
                        StandardDivider()
                        transaction.account?.let {
                            AccountDetailsRow("Account", account = account)
                            StandardDivider()
                        }

                        transaction.paymentAccount?.let {
                            AccountDetailsRow("Счет списания", account = paymentAccount )
                            StandardDivider()
                        }
                        transaction.recipientAccount?.let {
                            AccountDetailsRow("Счет зачисления", account = recipientAccount)
                            StandardDivider()
                        }

                        category?.let { DetailsCategory( it ) }
                        StandardDivider()
                        DetailsText("Date", formatCustomDate(transaction.timestamp))
                        StandardDivider()
                        DetailsText("Time", formatTime(transaction.timestamp))
                        StandardDivider()
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
        .height(56.dp)
        .padding(horizontal = 8.dp)) {
        Column(modifier = Modifier
            .fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Text(
                text = "$title:",
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
    val size = 56.dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(size)
            .padding(horizontal = 8.dp)
    ) {
        Column(     modifier = Modifier
            .fillMaxWidth()) {


            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = title,
                        color = TextDisabled
                    )
                    Text(
                        text = "${account.name} ${NumberFormatter.format(account.balance)} ${account.currencySymbol}",
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                }
                account.icon?.let { iconRes ->
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = null,
                        modifier = Modifier.size(size-8.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

        }
    }
}
@Composable
private fun DetailsCategory(category: CategoryDbo) {
    val size = 56.dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(size)
            .padding(horizontal = 8.dp)
    ) {
        Column(     modifier = Modifier
            .fillMaxWidth()) {


            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Category",
                        color = TextDisabled
                    )
                    Text(
                        text = category.categoryName,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                }
                CategoryIcon(iconResId =category.iconResId ,
                    backgroundColor = Color(category.color),
                    size =(size-8.dp),
                )


            }

        }
    }
}
@Composable
fun formatTimestamp(timestamp: Long): String {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    return dateFormat.format(Date(timestamp))
}