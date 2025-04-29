package ru.ohayo.moneypr.data.repository

import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.domain.category.Category


interface CategoryRepository {
    fun getAllCategories(): Flow<List<Category>>
    suspend fun insertAll(categories: List<Category>)
    suspend fun insertCategory(category: Category)
    suspend fun isEmpty(): Boolean
    fun getCategoryById(id: Long): Flow<Category?>
}