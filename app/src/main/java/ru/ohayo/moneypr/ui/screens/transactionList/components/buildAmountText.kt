package ru.ohayo.moneypr.ui.screens.transactionList.components

import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import ru.ohayo.moneypr.data.room.transaction.TransactionDbo
import ru.ohayo.moneypr.ui.theme.AppleGreenColor
import ru.ohayo.moneypr.utils.formate.NumberFormatter

@Composable
fun buildAmountText(transaction: TransactionDbo): AnnotatedString {
    val formattedAmount = NumberFormatter.format(Math.abs(transaction.amount))
    val currency = transaction.currency
    val prefix = if (transaction.recipientAccount == null) "- "
    else if (transaction.paymentAccount == null) "+ "
    else ""

    return buildAnnotatedString {
        pushStyle(
            SpanStyle(
                color = when {
                    transaction.recipientAccount == null -> colorScheme.onPrimary
                    transaction.paymentAccount == null -> AppleGreenColor
                    else -> Color.Blue
                }
            )
        )
        append(prefix)
        append(formattedAmount)
        append(" $currency")
        pop()
    }
}