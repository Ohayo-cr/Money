package ru.ohayo.moneypr.utils.formate

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

// Создаем объект для форматирования чисел
object NumberFormatter {
    private val formatter = DecimalFormat("#,##0.00", DecimalFormatSymbols().apply {
        groupingSeparator = ' '
        decimalSeparator = '.'
    })

    // Функция для форматирования числа с опцией отображения знака "+"
    fun format(number: Double, showPlus: Boolean = false): String {
        val formatted = formatter.format(number)
        val result = if (formatted.startsWith("-")) {
            formatted.replaceFirst("-", "- ")
        } else {
            if (showPlus) "+ $formatted" else formatted
        }
        return result
    }
}
