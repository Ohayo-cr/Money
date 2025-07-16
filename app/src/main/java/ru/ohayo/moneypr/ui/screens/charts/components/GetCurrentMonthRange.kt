package ru.ohayo.moneypr.ui.screens.charts.components

import java.util.Calendar

fun getCurrentMonthRange(): Pair<Long, Long> {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.DAY_OF_MONTH, 1)
    val firstDayOfMonth = calendar.timeInMillis  // в секундах (если timestamp хранится в секундах)

    calendar.add(Calendar.MONTH, 1)
    calendar.add(Calendar.DAY_OF_MONTH, -1)
    val lastDayOfMonth = calendar.timeInMillis

    return Pair(firstDayOfMonth, lastDayOfMonth)
}