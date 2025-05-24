package ru.ohayo.moneypr.ui.theme.screens.components.customeKeyboard

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId


@RequiresApi(Build.VERSION_CODES.O)
fun millisToLocalDateTime(millis: Long): LocalDateTime {
    return LocalDateTime.ofInstant(
        Instant.ofEpochMilli(millis),
        ZoneId.systemDefault()
    )
}