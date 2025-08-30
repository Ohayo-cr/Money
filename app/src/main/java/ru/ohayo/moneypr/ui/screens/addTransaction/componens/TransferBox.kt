package ru.ohayo.moneypr.ui.screens.addTransaction.componens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import ru.ohayo.moneypr.ui.component.spacers.Spacers

@Composable
fun TransferBox(
    fromAccount: String = "Счет списания",
    toAccount: String = "Счет получения",
    onFromAccountClick: () -> Unit = {},
    onToAccountClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val m = MaterialTheme.colorScheme
    val shape = RoundedCornerShape(percent = 20)
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
            verticalAlignment = Alignment.CenterVertically
        ) {
        // Левая часть - счет списания
        Column(
            modifier = Modifier
                .weight(1f)

        ) {
            Text(
                text = fromAccount,
                style = MaterialTheme.typography.bodyMedium,
                color = m.onPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacers.Small8()
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.White, shape = shape)
                    .clickable { onFromAccountClick()  }
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Добавить счет списания",
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp)
                )
            }
        }

        // Центральная иконка перевода
        Icon(
            painter = painterResource(id = R.drawable.icc_transfer), // Замените на ваш ресурс
            contentDescription = "Перевод",
            tint = m.onPrimary,
            modifier = Modifier
                .size(60.dp)
        )

        // Правая часть - счет получения
        Column(
            modifier = Modifier
                .weight(1f)

        ) {
            Text(
                text = toAccount,
                style = MaterialTheme.typography.bodyMedium,
                color = m.onPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacers.Small8()
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.White, shape = shape)
                    .clickable { onToAccountClick() }
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Добавить счет получения",
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        }
    }
}