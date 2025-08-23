package ru.ohayo.moneypr.utils.formate

import java.util.Locale

fun formatCustomDate(timestamp: Long): String {
    val instant = java.time.Instant.ofEpochMilli(timestamp)
    val zoneId = java.time.ZoneId.systemDefault()
    val zonedDateTime = instant.atZone(zoneId)

    val formatter = java.time.format.DateTimeFormatter.ofPattern("dd MMM yyyy EEE", Locale.getDefault())
    return zonedDateTime.format(formatter)
}
 fun formatTime(timestamp: Long): String {
    val instant = java.time.Instant.ofEpochMilli(timestamp)
    val zoneId = java.time.ZoneId.systemDefault()
    val zonedDateTime = instant.atZone(zoneId)

    val formatter = java.time.format.DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())
    return zonedDateTime.format(formatter)
}