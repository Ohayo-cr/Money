package ru.ohayo.moneypr.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject
import ru.ohayo.moneypr.data.data_source.category.CategoryDao
import ru.ohayo.moneypr.domain.category.Category



class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryRepository {

    override fun getAllCategories(): Flow<List<Category>> {
        return categoryDao.getAllCategories()
            .catch { e -> throw e } // Передаем ошибку выше
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
            .catch { e -> throw e }
    }
}