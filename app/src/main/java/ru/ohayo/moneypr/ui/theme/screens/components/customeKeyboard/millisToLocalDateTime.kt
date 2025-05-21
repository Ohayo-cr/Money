package ru.ohayo.moneypr.ui.theme.screens.components.customeKeyboard

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
fun millisToLocalDateTime(millis: Long): java.time.LocalDateTime {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = millis
    return java.time.LocalDateTime.of(
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH) + 1, // Calendar.MONTH — с 0
        calendar.get(Calendar.DAY_OF_MONTH),
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        calendar.get(Calendar.SECOND)
    )
}