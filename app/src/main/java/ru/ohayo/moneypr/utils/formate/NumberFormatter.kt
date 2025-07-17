package ru.ohayo.moneypr.utils.formate

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

// Создаем объект для форматирования чисел
object NumberFormatter {
    private val formatter = DecimalFormat("#,##0.00", DecimalFormatSymbols().apply {
        groupingSeparator = ' '
        decimalSeparator = '.'
    })

    // Функция для форматирования числа
    fun format(number: Double): String {
        val formatted = formatter.format(number)
        // Если число отрицательное, добавляем пробел после минуса
        return if (formatted.startsWith("-")) {
            formatted.replaceFirst("-", "- ")
        } else {
            formatted
        }
    }
}