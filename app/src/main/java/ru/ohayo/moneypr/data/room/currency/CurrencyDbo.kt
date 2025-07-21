package ru.ohayo.moneypr.data.room.currency

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencyDbo(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val code: String,
    val fullName: String,
    val iconResId: Int? = null,
    val symbol: String
)
