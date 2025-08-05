package ru.ohayo.moneypr.ui.screens.currencyScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.data.room.currency.CurrencyDbo
import ru.ohayo.moneypr.ui.screens.addAccount.components.AccountItem
import ru.ohayo.moneypr.ui.theme.TextDisabled

@Composable
fun CurrencyScreen(
    viewModel: CurrencyViewModel,
    onAddCurrencyClick: () -> Unit = {} // Коллбэк для добавления новой валюты
) {


    val currencies = viewModel.currencyList
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

            LazyColumn {
                itemsIndexed(currencies) { index, item ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                            .padding(vertical = 8.dp)
                    ) {
                        Row {


                        // Иконка
                        Image(
                            painter = painterResource(id = item.iconResId),
                            contentDescription = item.mainText,
                            modifier = Modifier
                                .size(44.dp)
                                .padding(end = 16.dp),
                            colorFilter = if (item.shouldTintIcon) ColorFilter.tint(MaterialTheme.colorScheme.onPrimary) else null
                        )

                        // Два текста друг над другом
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = item.mainText,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            Text(
                                text = item.secondaryText,
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextDisabled
                            )
                        }
                    }
                        Text(text = item.symbol,   color = MaterialTheme.colorScheme.onPrimary)

                    }
                    if (index < currencies.size - 1) {
                        Divider(modifier = Modifier.padding(horizontal = 8.dp))
                    }
                }
            }
        }
    }



