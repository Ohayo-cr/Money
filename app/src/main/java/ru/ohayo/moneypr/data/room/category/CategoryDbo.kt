package ru.ohayo.moneypr.data.room.category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
@Entity(
    indices = [Index(value = ["categoryName"], unique = true)]
)

data class CategoryDbo(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,

    @ColumnInfo(name = "categoryName")
    val categoryName: String,

    val type: CategoryType,
    val color: Long,
    val iconResId: Int,
    var order: Int = 1,
)

enum class CategoryType {
    Expense, Income, AccountTransfer
}