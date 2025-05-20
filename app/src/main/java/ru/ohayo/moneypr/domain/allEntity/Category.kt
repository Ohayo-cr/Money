package ru.ohayo.moneypr.domain.allEntity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val type: CategoryType,
    val name: String,
    val color: Long,
    val iconResId: Int,
    var order: Int = 1,
)

enum class CategoryType {
    EXPENSE, INCOME
}