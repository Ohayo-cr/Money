package ru.ohayo.moneypr.domain

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import ru.ohayo.moneypr.domain.currency.Currency

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
    val name: String, // Название счета
    val type: AccountType, // Тип счета (например, "cash", "bank", "card")
    val balance: Double, // Текущий баланс счета
    val currency: Long, // ID валюты (внешний ключ, используется Long для согласованности)
    @PrimaryKey(autoGenerate = true) val id: Long = 0 // Уникальный идентификатор счета
)

/**
 * Перечисление для типов счетов.
 */
enum class AccountType {
    Cash, // Наличные
    Card, // Банковская карта
    Contribution, // Вклад
    Other // Другое
}