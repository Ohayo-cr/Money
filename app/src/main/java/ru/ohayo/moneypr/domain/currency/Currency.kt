package ru.ohayo.moneypr.domain.currency

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Currency(
    @PrimaryKey(autoGenerate = true) val id: Long? = null, // Уникальный идентификатор валюты
    val name: String, // Название валюты
    val iconResId: Int // Идентификатор ресурса иконки (например, R.drawable.icon_name)
)
