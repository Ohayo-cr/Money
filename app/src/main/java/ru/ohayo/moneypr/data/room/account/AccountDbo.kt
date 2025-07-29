package ru.ohayo.moneypr.data.room.account

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    indices = [
        Index(value = ["name"], unique = true), // Уникальное имя счета
    ]
)
data class AccountDbo(
    val name: String,
    val type: AccountType,
    val initialBalance : Double = 0.0,
    val balance: Double,
    val currency: String,
    val icon: Int? = null,
    val note: String? = "",
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)

enum class AccountType {
    Cash,
    Card,
    Contribution,
    Other,
}