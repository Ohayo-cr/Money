package ru.ohayo.moneypr.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

// Создаем объект для форматирования чисел
object NumberFormatter {
    private val formatter = DecimalFormat("#,##0.00", DecimalFormatSymbols().apply {
        // Явно задаем пробел как разделитель групп разрядов
        groupingSeparator = ' '
        // Явно задаем точку как разделитель дробной части
        decimalSeparator = '.'
    })

    // Функция для форматирования числа
    fun format(number: Double): String {
        return formatter.format(number)
    }
}