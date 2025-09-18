package ru.ohayo.moneypr.ui.screens.addTransaction.componens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.R
import ru.ohayo.moneypr.data.room.account.AccountDbo
import ru.ohayo.moneypr.ui.component.spacers.Spacers
import ru.ohayo.moneypr.utils.formate.NumberFormatter

@Composable
fun TransferBox(
    fromAccount: AccountDbo? = null,
    toAccount: AccountDbo? = null,
    onFromAccountClick: () -> Unit = {},
    onToAccountClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val m = MaterialTheme.colorScheme


    Box(
        modifier = modifier
            .fillMaxWidth().padding(top = 16.dp)
            .background(
                color = m.tertiary,
                shape = RoundedCornerShape(16.dp),
            )
            .padding(vertical = 28.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            // Левая часть - счет списания
            AccountItem(
                title = "Счет списания",
                account = fromAccount,
                onClick = onFromAccountClick,
                modifier = Modifier.weight(1f)
            )

            // Центральная иконка перевода
            Icon(
                painter = painterResource(id = R.drawable.ic_2arrow), // Замените на ваш ресурс
                contentDescription = "Перевод",
                tint = m.onPrimary,
                modifier = Modifier.size(60.dp).align(Alignment.CenterVertically)
            )

            // Правая часть - счет получения
            AccountItem(
                title = "Счет пополнения",
                account = toAccount,
                onClick = onToAccountClick,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun AccountItem(
    account: AccountDbo?,
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(percent = 20)
    Column(
        modifier = modifier        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) { onClick() }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacers.Small8()

        Box(
            modifier = Modifier
                .size(60.dp)
                .background(Color.White, shape = shape)
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            if (account == null) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Добавить счет",
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp)
                )
            } else {
                // Отображаем иконку аккаунта, если задана
                account.icon?.let { iconRes ->
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(40.dp)
                    )
                } ?: Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(40.dp)
                )
            }
        }

        Spacers.Small8()
        Text(
            text = account?.name ?: "не выбран",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = if (account != null) "${NumberFormatter.format(account.balance)} ${account.currencySymbol}" else "не известно",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}