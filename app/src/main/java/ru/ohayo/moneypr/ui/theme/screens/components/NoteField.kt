package ru.ohayo.moneypr.ui.theme.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.viewModel.KeyboardViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteField(
    viewModel: KeyboardViewModel,
    onFocusChanged: (Boolean) -> Unit,
    onTextChanged: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current // Получаем менеджер фокуса

    Box(
        modifier = Modifier
            .fillMaxWidth().padding(start = 2.dp, end = 2.dp)
            .clickable {
                focusManager.clearFocus() // Убираем фокус при клике вне TextField
            }
    ) {
        TextField(
            value = viewModel.note, // Берем значение заметки из ViewModel
            onValueChange = { newText ->
                viewModel.updateNote(newText) // Обновляем значение в ViewModel
                onTextChanged(newText) // Передаем новое значение через колбэк
            },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    onFocusChanged(focusState.isFocused) // Уведомляем о состоянии фокуса
                },
            placeholder = {
                Text(
                    text = "Введите примечание",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.primary
                , // Цвет фона текстового поля
                cursorColor = MaterialTheme.colorScheme.primary, // Цвет курсора
                focusedIndicatorColor = Color.Transparent,       // Убираем подчеркивание при фокусе
                unfocusedIndicatorColor = Color.Transparent,     // Убираем подчеркивание без фокуса
                disabledIndicatorColor = Color.Transparent,      // Убираем подчеркивание для отключенного состояния
                focusedPlaceholderColor = Color.Red,             // Цвет плейсхолдера при фокусе
                unfocusedPlaceholderColor = Color.Gray           // Цвет плейсхолдера без фокуса
            ),
            shape = RoundedCornerShape(0.dp) // Убираем закругление углов
        )
    }
}
