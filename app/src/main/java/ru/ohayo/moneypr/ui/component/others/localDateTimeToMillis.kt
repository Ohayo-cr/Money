package ru.ohayo.moneypr.ui.component.others

import android.icu.util.Calendar


fun localDateTimeToMillis(dateTime: java.time.LocalDateTime): Long {
    val calendar = Calendar.getInstance()
    calendar.clear()

    calendar.set(
        dateTime.year,
        dateTime.monthValue - 1, // Calendar.MONTH — с 0 (январь = 0)
        dateTime.dayOfMonth,
        dateTime.hour,
        dateTime.minute,
        dateTime.second
    )

    return calendar.timeInMillis
}
