package ru.ohayo.moneypr.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import ru.ohayo.moneypr.data.room.category.CategoryDao
import ru.ohayo.moneypr.data.room.category.CategoryDbo
import ru.ohayo.moneypr.data.room.category.CategoryType


class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryRepository {

    override fun getAllCategories(): Flow<List<CategoryDbo>> {
        return categoryDao.getAllCategories()
            .catch { e -> throw e }
    }

    override suspend fun insertAll(categories: List<CategoryDbo>) {
        categoryDao.insertAll(categories)
    }

    override suspend fun insertCategory(categoryDbo: CategoryDbo) {
        categoryDao.insertCategory(categoryDbo)
    }
    override suspend fun isEmpty(): Boolean {
        return categoryDao.isEmpty()
    }

    override fun getCategoryById(id: Long): Flow<CategoryDbo?> {
        return categoryDao.getCategoryById(id)
            .catch { e ->
                Log.e("CategoryRepo", "Error fetching categories", e)
                throw e
            }
    }



    override fun getCategoriesByType(type: CategoryType): Flow<List<CategoryDbo>> =
        categoryDao.getCategoriesByType(type)
            .catch { e ->
                Log.e("CategoryRepo", "Error fetching categories by type", e)
                throw e
            }


    override suspend fun updateOrderByType(categories: List<CategoryDbo>) {
        withContext(Dispatchers.IO) {
            // Инвертируем список
            val reversedList = categories.reversed()

            // Обновляем порядок для перевернутого списка
            categoryDao.updateOrderByType(reversedList)
        }
    }
}