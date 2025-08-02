package ru.ohayo.moneypr.ui.component.customeTab

import ru.ohayo.moneypr.data.room.category.CategoryType

val CategoryTypeDisplayNameMap = mapOf(
    CategoryType.Expense to "Расход",
    CategoryType.Income to "Доход",
    CategoryType.AccountTransfer to "Перевод"
)