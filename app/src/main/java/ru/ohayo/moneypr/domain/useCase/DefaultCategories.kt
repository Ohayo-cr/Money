package ru.ohayo.moneypr.domain.useCase


import ru.ohayo.moneypr.R
import ru.ohayo.moneypr.domain.allEntity.Category
import ru.ohayo.moneypr.domain.allEntity.CategoryType

object DefaultCategories {
    private const val INCOME_COLOR = 0xFF50C878 // Зелёный для доходов
    private const val EXPENSE_COLOR = 0xFF67676B // Серый для расходов

    fun category(name: String, iconResId: Int, isIncome: Boolean = false) =
        Category(
            type = if (isIncome) CategoryType.INCOME else CategoryType.EXPENSE,
            categoryName = name,
            color = if (isIncome) INCOME_COLOR else EXPENSE_COLOR,
            iconResId = iconResId
        )

    val DEFAULT_CATEGORIES = listOf(
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
        category("Cats", R.drawable.notint_cat_3),
        category("Cats", R.drawable.notint_cat_3),
        category("Cats", R.drawable.notint_cat_3),
        category("Cats", R.drawable.notint_cat_3),
        category("Cats", R.drawable.notint_cat_3),
        category("Cats", R.drawable.notint_cat_3),
        category("Cats", R.drawable.notint_cat_3),
        category("Cats", R.drawable.notint_cat_3),
        category("Cats", R.drawable.notint_cat_3),
        category("Cats", R.drawable.notint_cat_3),
        category("Cats", R.drawable.notint_cat_3),
        category("Cats", R.drawable.notint_cat_3),
        category("Cats", R.drawable.notint_cat_3),
        category("Cats", R.drawable.notint_cat_3),
        category("Cats", R.drawable.notint_cat_3),
        category("Cats", R.drawable.notint_cat_3),
        category("Cats", R.drawable.notint_cat_3),
        category("Cats", R.drawable.notint_cat_3),
        category("Cats", R.drawable.notint_cat_3),
    )
}