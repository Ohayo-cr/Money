package ru.ohayo.moneypr.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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

    // Получить следующий порядковый номер
    suspend fun getNextOrder(type: CategoryType): Int {
        val maxOrder = categoryDao.getMaxOrder(type)
        return maxOrder?.plus(1) ?: 1
    }
 suspend fun getCategoryByIdUpdate(id: Long): Category? {
        return categoryDao.getCategoryByIdUpdate(id)
    }
   suspend fun updateCategory(category: Category) {
        withContext(Dispatchers.IO) {
            categoryDao.updateCategory(category)
        }
    }
    suspend fun deleteCategory(category: Category) =
        categoryDao.deleteCategory(category)

}