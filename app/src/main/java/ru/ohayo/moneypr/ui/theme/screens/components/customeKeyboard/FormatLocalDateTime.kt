package ru.ohayo.moneypr.ui.theme.screens.components.customeKeyboard

import java.text.SimpleDateFormat
import java.util.*

fun formatLocalDateTime(dateTimeMillis: Long): String {
    val sdf = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
    return sdf.format(Date(dateTimeMillis))
}