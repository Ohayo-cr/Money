package ru.ohayo.moneypr.ui.component.customeKeyboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp



@Composable
fun NoteField(
    note: String,
    onFocusChanged: (Boolean) -> Unit,
    onTextChanged: (String) -> Unit
) {
    var textFieldValue by remember(note) { mutableStateOf(note) }
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxWidth().padding(start = 2.dp, end = 2.dp)
            .clickable {
                focusManager.clearFocus()
            }
    ) {

        TextField(
            value = textFieldValue,
            onValueChange = { newText ->
                if (newText.length <= 100) {
                    textFieldValue = newText
                    onTextChanged(newText)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    onFocusChanged(focusState.isFocused)
                },
            placeholder = {
                Text(
                    text = "Введите заметку",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorScheme.onPrimary,
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            colors = TextFieldDefaults.colors(
                focusedTextColor = colorScheme.onPrimary,
                unfocusedTextColor = colorScheme.onPrimary.copy(alpha = 0.55f),
                disabledTextColor = Color.White.copy(alpha = 0.5f),
                errorTextColor = Color.Red,
                focusedContainerColor = colorScheme.secondary,
                // Цвет фона текстового поля
                unfocusedContainerColor = colorScheme.secondary,
                disabledContainerColor = colorScheme.secondary,
                cursorColor = colorScheme.onPrimary, // Цвет курсора
                focusedIndicatorColor = Color.Transparent,       // Убираем подчеркивание при фокусе
                unfocusedIndicatorColor = Color.Transparent,     // Убираем подчеркивание без фокуса
                disabledIndicatorColor = Color.Transparent,      // Убираем подчеркивание для отключенного состояния
                focusedPlaceholderColor = colorScheme.onPrimary,             // Цвет плейсхолдера при фокусе
                unfocusedPlaceholderColor = colorScheme.onPrimary,          // Цвет плейсхолдера без фокуса
            ),
            shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
        )
    }
}
