package ru.ohayo.moneypr.ui.component.customeKeyboard


import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId



fun millisToLocalDateTime(millis: Long): LocalDateTime {
    return LocalDateTime.ofInstant(
        Instant.ofEpochMilli(millis),
        ZoneId.systemDefault()
    )
}