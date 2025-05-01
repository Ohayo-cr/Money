package ru.ohayo.moneypr.domain.category

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // Автоинкрементируемый ID
    val type: CategoryType, // Тип категории: "EXPENSE" или "INCOME"
    val name: String, // Название категории
    val color: Long, // Цвет категории
    val iconResId: Int, // Идентификатор ресурса иконки (например, R.drawable.icon_name)
    var order: Int = 1
)

enum class CategoryType {
    EXPENSE, INCOME
}