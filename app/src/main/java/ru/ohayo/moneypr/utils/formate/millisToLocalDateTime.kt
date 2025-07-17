package ru.ohayo.moneypr.utils.formate


import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId



fun millisToLocalDateTime(millis: Long): LocalDateTime {
    return LocalDateTime.ofInstant(
        Instant.ofEpochMilli(millis),
        ZoneId.systemDefault()
    )
}