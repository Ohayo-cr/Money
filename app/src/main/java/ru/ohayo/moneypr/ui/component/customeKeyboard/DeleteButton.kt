package ru.ohayo.moneypr.ui.component.customeKeyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DeleteButton(
    modifier: Modifier = Modifier,
    onDeleteLast: () -> Unit,
    shape: Shape = RoundedCornerShape(2.dp)
) {
    var isPressed by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .padding(2.dp)
            .height(46.dp)
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        // Ожидаем нажатие
                        val downEvent = awaitFirstDown(requireUnconsumed = false)
                        isPressed = true
                        // Вызываем одиночное удаление при первом нажатии
                        onDeleteLast()
                        // Запоминаем время начала нажатия
                        val pressTime = System.currentTimeMillis()

                        // Запускаем корутину для повторяющегося удаления
                        scope.launch {
                            // Ждем 500 мс перед началом повторяющегося удаления
                            delay(300)

                            // Пока кнопка удерживается, продолжаем удалять каждые 100 мс
                            while (isPressed) {
                                onDeleteLast()
                                delay(100)
                            }
                        }
                        // Ожидаем отпускание кнопки
                        waitForUpOrCancellation()
                        isPressed = false // Сбрасываем состояние при отпускании
                    }
                }
            }
            .background(
                color = MaterialTheme.colorScheme.tertiary,
                shape = RoundedCornerShape(2.dp)
            )
            .clickable {}
    ) {
        Text(
            text = "⌫",
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}