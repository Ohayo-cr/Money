package ru.ohayo.moneypr.ui.screens.addAccount.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ru.ohayo.moneypr.ui.theme.TextDisabled

@Composable
fun AccountInfoSelector(
    text: String,
    onDismissRequest: () -> Unit,
    items: List<AccountItem>, // Список элементов для отображения
    selectedItem: AccountItem? = null, // Текущий выбранный элемент (опционально)
    onItemSelected: (AccountItem) -> Unit // Колбэк при выборе элемента
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                LazyColumn {
                    itemsIndexed(items) { index, item ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onItemSelected(item) }
                                .padding(vertical = 8.dp)
                        ) {
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
                        if (index < items.size - 1) {
                            Divider(modifier = Modifier.padding(horizontal = 8.dp))
                        }
                    }
                }
            }
        }
    }
}