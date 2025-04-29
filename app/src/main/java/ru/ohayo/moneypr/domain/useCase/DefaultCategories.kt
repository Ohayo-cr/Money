package ru.ohayo.moneypr.domain.useCase


import ru.ohayo.moneypr.R
import ru.ohayo.moneypr.domain.category.Category
import ru.ohayo.moneypr.domain.category.CategoryType

object DefaultCategories {
    val DEFAULT_CATEGORIES = listOf(
        Category(
            type = CategoryType.EXPENSE,
            name = "Food",
            color = 0xFF7FFFD4, // Оранжевый цвет
            iconResId = R.drawable.category_icon_test // Пример иконки
        ),
        Category(
            type = CategoryType.INCOME,
            name = "Salary",
            color = 0xFF4CAF50, // Зеленый цвет
            iconResId = R.drawable.category_icon_test // Пример иконки
        ),
        Category(
            type = CategoryType.EXPENSE,
            name = "Машина",
            color = 0xFF8A2BE2, // Фиолетово синий
            iconResId = R.drawable.category_icon_test // Пример иконки
        )

    )
}