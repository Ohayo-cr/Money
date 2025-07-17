package ru.ohayo.moneypr.domain.calculator

import net.objecthunter.exp4j.ExpressionBuilder
import ru.ohayo.moneypr.ui.component.customeKeyboard.CalculationResult

class ExpressionCalculator {
    operator fun invoke(expression: String): CalculationResult {
        return try {
            // Вычисляем результат выражения
            val result = ExpressionBuilder(expression).build().evaluate()

            // Определяем, является ли результат целым числом
            if (result == result.toLong().toDouble()) {
                CalculationResult.Success(result.toLong().toString())
            } else {
                // Ограничиваем количество знаков после запятой до двух
                val roundedResult = String.format("%.2f", result)
                CalculationResult.Success(roundedResult)
            }
        } catch (e: ArithmeticException) {
            // Обработка деления на ноль
            CalculationResult.Error("Ошибка: деление на ноль")
        } catch (e: IllegalArgumentException) {
            // Обработка недопустимых символов или выражений
            CalculationResult.Error("Ошибка: недопустимое выражение")
        } catch (e: Exception) {
            // Обработка всех остальных ошибок
            CalculationResult.Error("Ошибка вычисления")
        }
    }
}