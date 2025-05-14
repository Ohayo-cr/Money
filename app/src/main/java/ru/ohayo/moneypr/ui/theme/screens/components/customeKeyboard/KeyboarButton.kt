package ru.ohayo.moneypr.ui.theme.screens.components.customeKeyboard

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KeyboardButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    backgroundColor: Color = MaterialTheme.colorScheme.tertiary,
    shape: Shape = RoundedCornerShape(2.dp)
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(2.dp)
            .height(46.dp), // Устанавливаем фиксированную высоту
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(2.dp), // Небольшое закругление углов
        enabled = enabled
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center,
            fontSize = when (text) {
                "OK", "Дата" -> 16.sp // Уменьшаем размер для "Дата"
                else -> 26.sp // Стандартный размер для остальных кнопок
            }
        )
    }
}