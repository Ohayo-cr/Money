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
    ]
)
data class Account(
    val name: String,
    val type: AccountType,
    val balance: Double,
    val currency: String,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)

enum class AccountType {
    Cash,
    Card,
    Contribution,
    Other
}