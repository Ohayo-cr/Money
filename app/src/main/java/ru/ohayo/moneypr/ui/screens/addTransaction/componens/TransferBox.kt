package ru.ohayo.moneypr.ui.screens.addTransaction.componens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.R
import ru.ohayo.moneypr.data.room.account.AccountDbo
import ru.ohayo.moneypr.ui.component.spacers.Spacers

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
            .fillMaxWidth()
            .background(
                color = m.tertiary,
                shape = RoundedCornerShape(16.dp)
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
                painter = painterResource(id = R.drawable.icc_transfer), // Замените на ваш ресурс
                contentDescription = "Перевод",
                tint = m.onPrimary,
                modifier = Modifier.size(60.dp)
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
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
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
                .clickable { onClick() }
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

        if (account != null) {
            Spacers.Small8()
            Text(
                text = account.name,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "${account.balance} ${account.currency}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}