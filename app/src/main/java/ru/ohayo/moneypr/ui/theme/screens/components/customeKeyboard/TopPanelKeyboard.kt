package ru.ohayo.moneypr.ui.theme.screens.components.customeKeyboard

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.utils.NumberFormatterKeyboard
import ru.ohayo.moneypr.viewModel.AccountViewModel
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
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            modifier = Modifier.wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Кнопка "Выбрать счет"
            Text(
                text = selectedAccountName.take(10),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable { onAccountButtonClicked() }
            )
            if (currencyText != null) {
                Text(
                    text = currencyText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorScheme.onPrimary
                )
            }
        }

        // Правая часть: текст с текущим вводом или результатом
        val displayText = if (viewModel.result.isNotEmpty()) {
            NumberFormatterKeyboard.formatWithSpaces(viewModel.result)
        } else {
            NumberFormatterKeyboard.formatWithSpaces(viewModel.currentInput.ifEmpty { "0" })
        }

        // Заменяем пробелы на неразрывные внутри чисел, а после + / - вставляем мягкую точку переноса
        val formattedText = displayText
            .replace(" ", "\u00A0")                     // 1. Все пробелы → неразрывные
            .replace(Regex("([+\\-])")) { "${it.value}\u200B" }  // 2. После + / - → мягкий перенос + обычный пробел

        Text(
            text = formattedText,
            style = MaterialTheme.typography.headlineMedium,
            color = colorScheme.onPrimary,
            modifier = Modifier
                .wrapContentWidth() // чтобы не обрезало по краям
                .padding(end = 4.dp),
            softWrap = true,         // Разрешаем перенос
            overflow = TextOverflow.Clip // Отключаем обрезание, чтобы работал перенос
        )
    }
}
