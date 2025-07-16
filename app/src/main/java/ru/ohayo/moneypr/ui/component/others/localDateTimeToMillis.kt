package ru.ohayo.moneypr.ui.component.others

import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi


@RequiresApi(Build.VERSION_CODES.O)
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
