package ru.ohayo.moneypr.ui.theme.screens.components

import android.icu.util.Calendar

fun localDateTimeToMillis(dateTime: java.time.LocalDateTime): Long {
    val calendar = Calendar.getInstance()
    calendar.set(
        dateTime.year,
        dateTime.monthValue - 1, // Calendar.MONTH начинается с 0
        dateTime.dayOfMonth,
        dateTime.hour,
        dateTime.minute,
        dateTime.second
    )
    return calendar.timeInMillis
}