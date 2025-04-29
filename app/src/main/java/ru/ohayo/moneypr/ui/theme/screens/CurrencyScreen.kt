package ru.ohayo.moneypr.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.domain.currency.Currency


import ru.ohayo.moneypr.viewModel.CurrencyViewModel

@Composable
fun CurrencyScreen(
    viewModel: CurrencyViewModel,
    onAddCurrencyClick: () -> Unit = {} // Коллбэк для добавления новой валюты
) {
    // Собираем состояние валют из ViewModel
    val currencies by viewModel.currencies.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Заголовок экрана
        Text(
            text = "Список валют",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Если список валют пуст, показываем сообщение
        if (currencies.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Нет доступных валют",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = onAddCurrencyClick) {
                        Text("Добавить валюту")
                    }
                }
            }
        } else {
            // Отображаем список валют
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(currencies) { currency ->
                    CurrencyItem(currency = currency)
                }
            }
        }
    }
}

@Composable
fun CurrencyItem(currency: Currency) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(color = Color.LightGray.copy(alpha = 0.2f))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Иконка валюты
        Box(
            modifier = Modifier.size(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = currency.iconResId),
                contentDescription = "Currency icon",
                tint = Color.Unspecified
            )
        }

        // Название валюты
        Text(
            text = currency.name,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        // Дополнительные действия (например, удаление валюты)
        IconButton(onClick = { /* Обработчик удаления валюты */ }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete currency",
                tint = Color.Red
            )
        }
    }
}