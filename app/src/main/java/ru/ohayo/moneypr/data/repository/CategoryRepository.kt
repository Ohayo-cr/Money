package ru.ohayo.moneypr.data.repository

import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.domain.allEntity.CategoryDbo
import ru.ohayo.moneypr.domain.allEntity.CategoryType


interface CategoryRepository {
    fun getAllCategories(): Flow<List<CategoryDbo>>
    suspend fun insertAll(categories: List<CategoryDbo>)
    suspend fun insertCategory(categoryDbo: CategoryDbo)
    suspend fun isEmpty(): Boolean

    fun getCategoryById(id: Long): Flow<CategoryDbo?>

    fun getCategoriesByType(type: CategoryType): Flow<List<CategoryDbo>>

    suspend fun updateOrderByType(categories: List<CategoryDbo>)

}




