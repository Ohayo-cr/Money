package ru.ohayo.moneypr.data.room.account

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    indices = [
        Index(value = ["name"], unique = true),
    ]
)
data class AccountDbo(
    val name: String,
    val type: AccountType,
    val initialBalance : Double = 0.0,
    val balance: Double,
    val currencySymbol: String,
    val currencyCode: String,
    val icon: Int? = null,
    val note: String? = "",
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val exchangeRate: Double = 1.0,
    val inBudget: Boolean = true,
)

enum class AccountType {
    Cash,
    Card,
    Contribution,
    Other,
}