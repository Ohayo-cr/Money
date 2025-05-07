package ru.ohayo.moneypr.data.repository

import ru.ohayo.moneypr.data.data_source.category.CategoryDao
import ru.ohayo.moneypr.domain.category.Category
import ru.ohayo.moneypr.domain.category.CategoryType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddCategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
) {
    suspend fun addCategory(category: Category) {
        categoryDao.insertCategory(category)
    }

    suspend fun getNextOrder(type: CategoryType): Int {
        return categoryDao.getNextOrder(type) ?: 1
    }
}