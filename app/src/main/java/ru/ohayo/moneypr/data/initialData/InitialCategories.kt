package ru.ohayo.moneypr.data.initialData


import ru.ohayo.moneypr.R
import ru.ohayo.moneypr.data.room.category.CategoryDbo
import ru.ohayo.moneypr.data.room.category.CategoryType

object InitialCategories {
    private const val INCOME_COLOR = 0xFF50C878 // Зелёный для доходов
    private const val EXPENSE_COLOR = 0xFF67676B // Серый для расходов
    private const val TRANSFER_COLOR = 0xFF2196F3 // Синий цвет для трансферов


    fun category(
        name: String,
        iconResId: Int,
        isIncome: Boolean = false,
        isTransfer: Boolean = false,
        id: Long = 0
    ) = CategoryDbo(
        id = id,
        type = when {
            isTransfer -> CategoryType.AccountTransfer
            isIncome -> CategoryType.Income
            else -> CategoryType.Expense
        },
        categoryName = name,
        color = when {
            isTransfer -> TRANSFER_COLOR
            isIncome -> INCOME_COLOR
            else -> EXPENSE_COLOR
        },
        iconResId = iconResId
    )

    val INITIAL_CATEGORIES = listOf(
        category("Drinks", R.drawable.cat__ic_energy),
        category("Products", R.drawable.cat__ic_supermarket),
        category("Music", R.drawable.cat__ic_acustik),
        category("Delivery", R.drawable.cat__ic_delivery),
        category("Computer", R.drawable.cat__ic_pc),
        category("Diamond", R.drawable.cat__ic_diamond),
        category("Smoking", R.drawable.cat__ic_puff),
        category("Cats", R.drawable.notint_cat_3),
        category("Salary", R.drawable.cat__ic_power, isIncome = true),
        category("Cashback", R.drawable.cat__ic_premium, isIncome = true),
        category("Transfer", R.drawable.cat__ic_transf, isTransfer = true, id = 999L),


    )
}