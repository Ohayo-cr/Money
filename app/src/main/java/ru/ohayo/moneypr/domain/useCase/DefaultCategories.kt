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
            iconResId = R.drawable.cat__ic_fish // Пример иконки
        ),
        Category(
            type = CategoryType.INCOME,
            name = "Salary",
            color = 0xFF4CAF50, // Зеленый цвет
            iconResId = R.drawable.cat__ic_fish // Пример иконки
        ),
        Category(
            type = CategoryType.EXPENSE,
            name = "Машина",
            color = 0xFF8FBC8F, // Фиолетово синий
            iconResId = R.drawable.cat__ic_fish // Пример иконки
        ),
        Category(
            type = CategoryType.EXPENSE,
            name = "VAPE",
            color = 0xFFB0E0E6, // Фиолетово синий
            iconResId = R.drawable.cat__ic_fish // Пример иконки
        ),
        Category(
            type = CategoryType.EXPENSE,
            name = "YOU",
            color = 0xFF2F4F4F, // Фиолетово синий
            iconResId = R.drawable.cat__ic_fish // Пример иконки
        ),
        Category(
            type = CategoryType.INCOME,
            name = "Дивиденды",
            color = 0xFFDDA0DD, // Фиолетово синий
            iconResId = R.drawable.cat__ic_fish // Пример иконки
        ),


    )
}