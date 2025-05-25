package ru.ohayo.moneypr.domain.allEntity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Сущность для представления счета пользователя.
 */
@Entity(
    indices = [
        Index(value = ["name"], unique = true), // Уникальное имя счета
        Index(value = ["currency"]) // Индекс для currencyId
    ],
    foreignKeys = [
        ForeignKey(
            entity = Currency::class,
            parentColumns = ["id"],
            childColumns = ["currency"],
            onDelete = ForeignKey.SET_NULL // Установка NULL при удалении валюты
        )
    ]
)
data class Account(
    val name: String,
    val type: AccountType,
    val balance: Double,
    val currency: Long,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)

enum class AccountType {
    Cash,
    Card,
    Contribution,
    Other
}