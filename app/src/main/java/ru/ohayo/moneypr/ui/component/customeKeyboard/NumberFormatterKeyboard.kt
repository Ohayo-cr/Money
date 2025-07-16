package ru.ohayo.moneypr.ui.component.customeKeyboard

import java.util.Locale

object NumberFormatterKeyboard {

    fun formatWithSpaces(input: String): String {
        // Регулярное выражение для поиска чисел (целых, дробных, с точкой в начале)
        val numberPattern = Regex("""\d+(\.\d*)?|\.\d+""")
        // Регулярное выражение для поиска операторов
        val operatorPattern = Regex("""[+\-*/]""")

        // Разбиваем строку на токены (числа, операторы, другие символы)
        val tokens = mutableListOf<String>()
        var currentIndex = 0

        while (currentIndex < input.length) {
            val numberMatch = numberPattern.find(input, currentIndex)
            val operatorMatch = operatorPattern.find(input, currentIndex)

            if (numberMatch != null && (operatorMatch == null || numberMatch.range.first < operatorMatch.range.first)) {
                // Если найдено число
                tokens.add(numberMatch.value)
                currentIndex = numberMatch.range.last + 1
            } else if (operatorMatch != null) {
                // Если найден оператор
                tokens.add(operatorMatch.value)
                currentIndex = operatorMatch.range.last + 1
            } else {
                // Пропускаем неизвестные символы
                currentIndex++
            }
        }

        // Форматируем числа и добавляем пробелы вокруг операторов
        val result = tokens.mapIndexed { index, token ->
            when {
                numberPattern.matches(token) -> formatSingleNumber(token)
                operatorPattern.matches(token) -> "\u00A0$token\u00A0"
                else -> token
            }
        }.joinToString("")

        // Удаляем лишние пробелы и корректируем форматирование
        return result.trim()
            .replace(Regex("\\s+"), " ") // Убираем лишние пробелы
            .replace(Regex("(\\d)\\s+(\\.)\\s+(\\d)"), "$1.$3") // Убираем пробелы вокруг точки в десятичных числах
            .replace(Regex("(\\d)\\s+(\\.)"), "$1.") // Убираем пробелы между числом и точкой, если точка в конце
    }

    private fun formatSingleNumber(number: String): String {
        return try {
            // Если число начинается с точки (например, ".1"), добавляем "0" перед точкой
            val normalizedNumber = if (number.startsWith(".")) "0$number" else number


            // Преобразуем строку в число
            val numericValue = normalizedNumber.toDouble()

            // 1. Форматируем с разделителями-запятыми
            val withCommas = "%,d".format(Locale.getDefault(), numericValue.toLong())

            // 2. Заменяем запятые на НЕРАЗРЫВНЫЕ пробелы
            val integerPart = withCommas.replace(",", "\u00A0")

            // Форматируем дробную часть, если она есть
            val decimalPart = when {
                normalizedNumber.contains(".") && numericValue % 1 != 0.0 -> {
                    // Берем только те цифры, которые находятся после точки
                    val rawDecimal = normalizedNumber.substringAfter(".")
                    // Ограничиваем длину до двух знаков после запятой
                    val formattedDecimal = rawDecimal.take(2)
                    ".$formattedDecimal"
                }
                normalizedNumber.endsWith(".") -> "." // Если число заканчивается на точку, добавляем её
                else -> ""
            }

            // Проверяем, была ли у исходного числа нулевая дробная часть (например, "0.00")
            val shouldKeepZero = normalizedNumber.endsWith(".00") || normalizedNumber.endsWith(".")

            if (shouldKeepZero && normalizedNumber.endsWith(".00")) {
                "$integerPart.00" // **Сохраняем ".00"**
            } else if (normalizedNumber.endsWith(".0")) {
                "$integerPart.0" // **Сохраняем ".0"**
            } else {
                "$integerPart$decimalPart"
            }
        } catch (e: NumberFormatException) {
            number // Возвращаем исходное значение, если преобразование не удалось
        }
    }
}