package ru.ohayo.moneypr.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.ohayo.moneypr.data.repository.ExpressionRepository
import ru.ohayo.moneypr.ui.theme.screens.components.CalculationResult
import ru.ohayo.moneypr.ui.theme.screens.use_case.EvaluateExpressionUseCase
import javax.inject.Inject

@HiltViewModel
class KeyboardViewModel @Inject constructor(
    private val evaluateExpressionUseCase: EvaluateExpressionUseCase,
    private val repository: ExpressionRepository
) : ViewModel() {

    var currentInput by mutableStateOf("")
        private set

    var result by mutableStateOf("")
        private set

    var note by mutableStateOf("") // Новое состояние для примечания
        private set

    val isExpressionReadyForEvaluation: Boolean
        get() = currentInput.trim().isNotEmpty() && currentInput.any { it in "+-*/" }

    fun updateNote(newNote: String) {
        note = newNote // Обновление примечания
    }
    val isResultPositive: Boolean
        get() = result.toDoubleOrNull()?.let { it >= 0 } ?: true // Если результат пустой или не число, считаем его положительным
    // Флаг для отслеживания состояния зажатия кнопки "←"
    private var isDeletePressed = false

    fun appendToInput(value: String) {
        // Если результат равен "0", очищаем его перед началом нового ввода
        if (result == "0" && value.matches(Regex("[0-9.,]"))) {
            result = "" // Очищаем результат
        }

        // Если результат не пустой и пользователь начинает новый ввод
        if (result.isNotEmpty()) {
            currentInput = result
            result = ""
            if (value in "+-*/") {
                currentInput += value
                return
            }
        }

        // Обработка точки или запятой в начале ввода или после оператора
        if (value in ".," && (currentInput.isEmpty() || currentInput.last() in "+-*/")) {
            currentInput += "0."
            return
        }

        // Если вводится оператор
        if (value in "+-*/") {
            if (isExpressionReadyForEvaluation) {
                calculateResult() // Вычисляем текущее выражение
                currentInput = result + value // Добавляем оператор к результату
                result = "" // Очищаем результат
                return
            }
        }
        // Проверка на замену "0" на новое число
        if (value.matches(Regex("[0-9]")) && currentInput.endsWith("0")) {
            val lastNumber = getLastNumber(currentInput)
            if (lastNumber == "0") {
                currentInput = currentInput.dropLast(1) + value // Заменяем 0 на новое число
                return
            }
        }
        // Проверка на корректность ввода
        if (isValidInput(currentInput, value)) {
            currentInput += value
        }
    }

    fun calculateResult() {
        // Убираем последний символ, если это оператор
        val sanitizedInput = if (currentInput.isNotEmpty() && currentInput.last() in "+-*/") {
            currentInput.dropLast(1) // Удаляем последний оператор
        } else {
            currentInput
        }

        // Заменяем запятые на точки для корректного вычисления
        val expressionWithDots = sanitizedInput.replace(',', '.')

        when (val calculationResult = evaluateExpressionUseCase(expressionWithDots)) {
            is CalculationResult.Success -> {

                // Заменяем точку на запятую для отображения результата
                result = calculationResult.value.replace(',', '.')
                repository.saveExpression(currentInput, result)
                currentInput = ""
            }
            is CalculationResult.Error -> {
                result = calculationResult.message
            }
        }
    }

    // Удаление последнего символа
    fun deleteLast() {
        if (currentInput.isNotEmpty()) {
            currentInput = currentInput.dropLast(1)
        } else if (result.isNotEmpty()) {
            result = result.dropLast(1)
        }
    }

    private fun isValidInput(currentInput: String, newChar: String): Boolean {
        // Запрещаем ввод операторов в начале строки
        if (currentInput.isEmpty() && newChar in "+-*/") {
            return false
        }
// Разрешаем минус только как бинарный оператор (между числами)
        if (newChar == "-") {
            // Разрешаем минус только если перед ним есть число
            if (currentInput.isNotEmpty() && currentInput.last().isDigit()) {
                return true
            }
            return false
        }
        // Запрещаем вводить более одной запятой в одном числе
        if (newChar == ".") {
            if (currentInput.isEmpty() || currentInput.last() in "+-*/") {
                return false // Обрабатывается в appendToInput (добавляется "0.")
            }
            val lastNumber = getLastNumber(currentInput)
            return !lastNumber.contains(".")
        }

        // Если вводится цифра, проверяем количество знаков после запятой и ведущие нули
        if (newChar.matches(Regex("[0-9]"))) {
            val lastNumber = getLastNumber(currentInput)

            // Проверка на ведущие нули
            if (lastNumber.startsWith("0") && !lastNumber.contains(".")) {
                return false // Запрещаем добавление цифр после ведущего нуля без десятичной точки
            }

            // Проверка количества знаков после запятой
            if (lastNumber.contains(".")) {
                val afterDecimal = lastNumber.substringAfter(".")
                if (afterDecimal.length >= 2) {
                    return false // Запрещаем больше двух цифр после запятой
                }
            }
        }

        // Запрещаем вводить несколько операторов подряд
        if (newChar in "+*/-" && currentInput.isNotEmpty() && currentInput.last() in "+-*/") {
            return false
        }

        // Разрешаем только цифры, запятую и операторы
        return newChar.matches(Regex("[0-9,+-/*]"))
    }

    private fun getLastNumber(input: String): String {
        return input.split("+", "-", "*", "/").lastOrNull()?.trim() ?: ""
    }
}