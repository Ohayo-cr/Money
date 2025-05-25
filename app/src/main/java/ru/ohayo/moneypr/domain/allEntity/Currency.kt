package ru.ohayo.moneypr.domain.allEntity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Currency(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val iconResId: Int? = null,
    val symbol: String
)
