package ru.ohayo.moneypr.data.repository

import android.util.Log
import androidx.room.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import ru.ohayo.moneypr.data.data_source.allDao.CategoryDao
import ru.ohayo.moneypr.domain.allEntity.Category
import ru.ohayo.moneypr.domain.allEntity.CategoryType


class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryRepository {

    override fun getAllCategories(): Flow<List<Category>> {
        return categoryDao.getAllCategories()
            .catch { e -> throw e }
    }

    override suspend fun insertAll(categories: List<Category>) {
        categoryDao.insertAll(categories)
    }

    override suspend fun insertCategory(category: Category) {
        categoryDao.insertCategory(category)
    }
    override suspend fun isEmpty(): Boolean {
        return categoryDao.isEmpty()
    }

    override fun getCategoryById(id: Long): Flow<Category?> {
        return categoryDao.getCategoryById(id)
            .catch { e ->
                Log.e("CategoryRepo", "Error fetching categories", e)
                throw e
            }
    }



    override fun getCategoriesByType(type: CategoryType): Flow<List<Category>> =
        categoryDao.getCategoriesByType(type)
            .catch { e ->
                Log.e("CategoryRepo", "Error fetching categories by type", e)
                throw e
            }


    override suspend fun updateOrderByType(categories: List<Category>) {
        withContext(Dispatchers.IO) {
            // Инвертируем список
            val reversedList = categories.reversed()

            // Обновляем порядок для перевернутого списка
            categoryDao.updateOrderByType(reversedList)
        }
    }
}