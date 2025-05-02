package ru.ohayo.moneypr.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject
import ru.ohayo.moneypr.data.data_source.category.CategoryDao
import ru.ohayo.moneypr.domain.category.Category
import ru.ohayo.moneypr.domain.category.CategoryType


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
    override suspend fun updateCategories(updatedCategories: List<Category>) {
        // Используем транзакцию для массового обновления
        categoryDao.updateOrderByType(updatedCategories)
    }
    override suspend fun deleteCategory(category: Category) =
        categoryDao.deleteCategory(category)

    override fun getCategoriesByType(type: CategoryType): Flow<List<Category>> =
        categoryDao.getCategoriesByType(type)
            .catch { e ->
                Log.e("CategoryRepo", "Error fetching categories by type", e)
                throw e
            }

    override suspend fun getMaxOrder(type: CategoryType): Int =
        categoryDao.getMaxOrder(type)

    override suspend fun updateCategory(category: Category) =
        categoryDao.update(category)

}