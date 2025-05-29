package ru.ohayo.moneypr.data.repository

import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.domain.allEntity.Category
import ru.ohayo.moneypr.domain.allEntity.CategoryType


interface CategoryRepository {
    fun getAllCategories(): Flow<List<Category>>
    suspend fun insertAll(categories: List<Category>)
    suspend fun insertCategory(category: Category)
    suspend fun isEmpty(): Boolean

    fun getCategoryById(id: Long): Flow<Category?>

    fun getCategoriesByType(type: CategoryType): Flow<List<Category>>

    suspend fun updateOrderByType(categories: List<Category>)

}




