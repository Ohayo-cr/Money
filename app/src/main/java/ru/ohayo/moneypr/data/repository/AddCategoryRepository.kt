package ru.ohayo.moneypr.data.repository

import ru.ohayo.moneypr.data.data_source.allDao.CategoryDao
import ru.ohayo.moneypr.domain.allEntity.Category
import ru.ohayo.moneypr.domain.allEntity.CategoryType
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