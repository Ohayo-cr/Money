package ru.ohayo.moneypr.domain.useCase


import ru.ohayo.moneypr.R
import ru.ohayo.moneypr.domain.category.Category
import ru.ohayo.moneypr.domain.category.CategoryType

object DefaultCategories {
    private const val INCOME_COLOR = 0xFF4CAF50 // Зелёный для доходов
    private const val EXPENSE_COLOR = 0xFFA9A9A9 // Серый для расходов

    fun category(name: String, iconResId: Int, isIncome: Boolean = false) =
        Category(
            type = if (isIncome) CategoryType.INCOME else CategoryType.EXPENSE,
            name = name,
            color = if (isIncome) INCOME_COLOR else EXPENSE_COLOR,
            iconResId = iconResId
        )

    val DEFAULT_CATEGORIES = listOf(
        category("Продукты", R.drawable.cat__ic_energy),
        category("Музыка", R.drawable.cat__ic_acustik),
        category("Вейп", R.drawable.cat__ic_vape),
        category("Компег", R.drawable.cat__ic_pc),
        category("Зарплата", R.drawable.cat__ic_power, isIncome = true),
        category("Кебшбек", R.drawable.cat__ic_energy, isIncome = true)
    )
}