package ru.ohayo.moneypr.ui.screens.transactionList.components.models

import androidx.compose.runtime.compositionLocalOf
import ru.ohayo.moneypr.data.room.category.CategoryDbo

val LocalCategoryMap = compositionLocalOf<Map<String, CategoryDbo>> {
    error("CategoryMap not provided")
}