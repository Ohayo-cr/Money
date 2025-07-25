package ru.ohayo.moneypr.ui.component.customeKeyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.ohayo.moneypr.ui.screens.addAccount.NoDateKeyboardViewModel

@Composable
fun UndatedKeyboard(
    viewModel: NoDateKeyboardViewModel = hiltViewModel(),
    onDateButtonClicked: () -> Unit,
    onHideKeyboardClicked: () -> Unit,
    onOkClicked: () -> Unit,
    dateText: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max) // Занимает всю доступную высоту
            .background(MaterialTheme.colorScheme.primary)
            .padding(top = 4.dp), // Добавляем небольшой отступ сверху
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Первая строка
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            KeyboardButton("7", Modifier.weight(1f), onClick = { viewModel.appendToInput("7") },
                shape = RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 2.dp,
                    bottomEnd = 2.dp,
                    bottomStart = 2.dp
                ))
            KeyboardButton("8", Modifier.weight(1f), onClick = { viewModel.appendToInput("8") })
            KeyboardButton("9", Modifier.weight(1f), onClick = { viewModel.appendToInput("9") },)
            DateButton(
                text = dateText,
                onClick = onDateButtonClicked,
                modifier = Modifier.weight(1f),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                shape = RoundedCornerShape(
                    topStart = 2.dp,
                    topEnd = 8.dp,
                    bottomEnd = 2.dp,
                    bottomStart = 2.dp
                )
            )
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
            KeyboardButton(
                text = ".",
                modifier = Modifier.weight(1f),
                onClick = { viewModel.appendToInput(".") },
                shape = RoundedCornerShape(
                    topStart = 2.dp,
                    topEnd = 2.dp,
                    bottomEnd = 2.dp,
                    bottomStart = 8.dp // нижний левый угол закруглён на 10.dp
                )
            )
            KeyboardButton("0", Modifier.weight(1f), onClick = { viewModel.appendToInput("0") })
            // Кнопка "стереть"
            DeleteButton(modifier = Modifier.weight(1f), onDeleteLast = { viewModel.deleteLast() })
            // Кнопка OK или =
            val buttonText = if (viewModel.isExpressionReadyForEvaluation) "=" else "OK"
            val isOnlyMinus = viewModel.currentInput == "-"
            val buttonEnabled = if (viewModel.result.isNotEmpty()) {
                 viewModel.result.toDoubleOrNull() != 0.0
            } else {
                !isOnlyMinus && (viewModel.isExpressionReadyForEvaluation || isNumber(viewModel.currentInput))
            }
            val buttonColor = if (buttonEnabled) MaterialTheme.colorScheme.inversePrimary
            else MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)

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
                shape = RoundedCornerShape(
                    topStart = 2.dp,
                    topEnd = 2.dp,
                    bottomStart = 2.dp,
                    bottomEnd = 8.dp
                )
            )
        }
    }
}
private fun isNumber(input: String): Boolean {
    return when {

        input == "." || input == "," -> false // Одиночная точка или запятая не являются числами
        input.toDoubleOrNull() != null -> true // Корректное число
        else -> false
    }
}