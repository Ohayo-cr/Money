package ru.ohayo.moneypr.ui.theme.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.viewModel.KeyboardViewModel

@Composable
fun CalculatorKeyboard(
    viewModel: KeyboardViewModel,
    onDateButtonClicked: () -> Unit,
    onHideKeyboardClicked: () -> Unit,
    onOkClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max) // Занимает всю доступную высоту
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 4.dp), // Добавляем небольшой отступ сверху
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Первая строка
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            KeyboardButton("7", Modifier.weight(1f), onClick = { viewModel.appendToInput("7") })
            KeyboardButton("8", Modifier.weight(1f), onClick = { viewModel.appendToInput("8") })
            KeyboardButton("9", Modifier.weight(1f), onClick = { viewModel.appendToInput("9") })
            KeyboardButton("Дата", Modifier.weight(1f), onClick = { onDateButtonClicked() })
        }

        // Вторая строка
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            KeyboardButton("4", Modifier.weight(1f), onClick = { viewModel.appendToInput("4") })
            KeyboardButton("5", Modifier.weight(1f), onClick = { viewModel.appendToInput("5") })
            KeyboardButton("6", Modifier.weight(1f), onClick = { viewModel.appendToInput("6") })
            KeyboardButton("+", Modifier.weight(1f), onClick = { viewModel.appendToInput("+") })
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            KeyboardButton("1", Modifier.weight(1f), onClick = { viewModel.appendToInput("1") })
            KeyboardButton("2", Modifier.weight(1f), onClick = { viewModel.appendToInput("2") })
            KeyboardButton("3", Modifier.weight(1f), onClick = { viewModel.appendToInput("3") })
            KeyboardButton("-", Modifier.weight(1f), onClick = { viewModel.appendToInput("-") })
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            KeyboardButton(".", Modifier.weight(1f), onClick = { viewModel.appendToInput(".") },
                shape = RoundedCornerShape(bottomStart = 8.dp))
            KeyboardButton("0", Modifier.weight(1f), onClick = { viewModel.appendToInput("0") })
            // Кнопка "стереть"
            DeleteButton(modifier = Modifier.weight(1f), onDeleteLast = { viewModel.deleteLast() })
            // Кнопка OK или =
            val buttonText = if (viewModel.isExpressionReadyForEvaluation) "=" else "OK"
            val isOnlyMinus = viewModel.currentInput == "-"
            val buttonEnabled = if (viewModel.result.isNotEmpty()) {
                viewModel.isResultPositive && viewModel.result.toDoubleOrNull() != 0.0
            } else {
                !isOnlyMinus && (viewModel.isExpressionReadyForEvaluation || isNumber(viewModel.currentInput))
            }
            val buttonColor = if (buttonEnabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)

            KeyboardButton(
                text = buttonText,
                modifier = Modifier.weight(1f),
                onClick = {
                    if (viewModel.isExpressionReadyForEvaluation) {
                        viewModel.calculateResult()
                    } else {
                        onOkClicked()
                    }
                },
                enabled = buttonEnabled,
                backgroundColor = buttonColor,
                shape = RoundedCornerShape(bottomEnd = 8.dp)
            )
        }
    }
}
fun isNumber(input: String): Boolean {
    return when {
        input == "0" -> false // Одиночный 0 не является числом
        input == "." || input == "," -> false // Одиночная точка или запятая не являются числами
        input == "0." || input == "0," -> false // Незавершенные числа вида "0." или "0," не являются числами
        input == "0.0" || input == "0.00" -> false // Числа 0.0 и 0.00 считаются некорректными
        input.toDoubleOrNull() != null -> true // Корректное число
        else -> false
    }
}

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
                else -> 20.sp // Стандартный размер для остальных кнопок
            }
        )
    }
}
@Composable
fun DeleteButton(
    modifier: Modifier = Modifier,
    onDeleteLast: () -> Unit
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
    ) {
        Button(
            onClick = { /* Обработка клика уже включена в pointerInput */ },
            modifier = Modifier.fillMaxSize(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
            shape = RoundedCornerShape(2.dp)
        ) {
            Text(
                text = "⌫",
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
    }
}
