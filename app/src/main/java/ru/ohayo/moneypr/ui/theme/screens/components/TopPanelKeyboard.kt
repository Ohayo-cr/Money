package ru.ohayo.moneypr.ui.theme.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.utils.NumberFormatterKeyboard
import ru.ohayo.moneypr.viewModel.KeyboardViewModel

@Composable
fun TopPanelKeyboard(
    viewModel: KeyboardViewModel,
    onAccountButtonClicked: () -> Unit,
    selectedAccountName: String,
    currencyText: String? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween // Распределяем элементы по краям
    ) {
        // Левая часть: кнопка выбора счета и текст с валютой
        Row(
            modifier = Modifier.wrapContentWidth(), // Занимает только необходимое пространство
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Кнопка "Выбрать счет"
            Button(
                onClick = onAccountButtonClicked,
                modifier = Modifier.padding(end = 8.dp),
                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 2.dp) // Минимальный паддинг
            ) {
                Text(
                    text = selectedAccountName.take(6), // Ограничиваем до 6 символов
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // Текст с валютой (если доступен)
            if (currencyText != null) {
                Text(
                    text = currencyText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorScheme.onPrimary
                )
            }
        }

        // Правая часть: текст с текущим вводом или результатом
        Text(
            text = if (viewModel.result.isNotEmpty()) {
                NumberFormatterKeyboard.formatWithSpaces(viewModel.result) // Форматируем результат
            } else {
                NumberFormatterKeyboard.formatWithSpaces(viewModel.currentInput.ifEmpty { "0" }) // Форматируем текущий ввод
            },
            style = MaterialTheme.typography.headlineMedium,
            color = colorScheme.onPrimary,
            modifier = Modifier
        )
    }
}
