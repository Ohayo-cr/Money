package ru.ohayo.moneypr.ui.theme.screens.components


import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


private val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())

fun formatTimestamp(timestamp: Long): String {
    return dateFormat.format(Date(timestamp))
}