package ru.ohayo.moneypr.data.room.category

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryDbo(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val type: CategoryType,
    val categoryName: String,
    val color: Long,
    val iconResId: Int,
    var order: Int = 1,
)

enum class CategoryType {
    EXPENSE, INCOME
}