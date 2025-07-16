package ru.ohayo.moneypr.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.ohayo.moneypr.data.data_source.allDao.CategoryDao
import ru.ohayo.moneypr.domain.allEntity.CategoryDbo
import ru.ohayo.moneypr.domain.allEntity.CategoryType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddCategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
) {
    suspend fun addCategory(categoryDbo: CategoryDbo) {
        categoryDao.insertCategory(categoryDbo)
    }

    // Получить следующий порядковый номер
    suspend fun getNextOrder(type: CategoryType): Int {
        val maxOrder = categoryDao.getMaxOrder(type)
        return maxOrder?.plus(1) ?: 1
    }
 suspend fun getCategoryByIdUpdate(id: Long): CategoryDbo? {
        return categoryDao.getCategoryByIdUpdate(id)
    }
   suspend fun updateCategory(categoryDbo: CategoryDbo) {
        withContext(Dispatchers.IO) {
            categoryDao.updateCategory(categoryDbo)
        }
    }
    suspend fun deleteCategory(categoryDbo: CategoryDbo) =
        categoryDao.deleteCategory(categoryDbo)

}