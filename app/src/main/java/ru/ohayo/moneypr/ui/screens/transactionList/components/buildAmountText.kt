package ru.ohayo.moneypr.ui.screens.transactionList.components

import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import ru.ohayo.moneypr.data.room.category.CategoryType
import ru.ohayo.moneypr.data.room.transaction.TransactionDbo
import ru.ohayo.moneypr.ui.theme.AppleGreenColor
import ru.ohayo.moneypr.ui.theme.CornflowerBlueColor
import ru.ohayo.moneypr.utils.formate.NumberFormatter
import kotlin.math.abs

@Composable
fun buildAmountText(transaction: TransactionDbo): AnnotatedString {
    val formattedAmount = NumberFormatter.format(abs(transaction.amount))
    val currency = transaction.currency
    val prefix = when (transaction.type) {
        CategoryType.Expense -> "- "
        CategoryType.Income -> "+ "
        else -> ""
    }

    return buildAnnotatedString {
        pushStyle(
            SpanStyle(
                color = when (transaction.type) {
                    CategoryType.Income -> AppleGreenColor
                    CategoryType.AccountTransfer -> CornflowerBlueColor
                    else -> colorScheme.onPrimary
                }
            )
        )
        append(prefix)
        append(formattedAmount)
        append(" $currency")
        pop()
    }
}