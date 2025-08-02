package ru.ohayo.moneypr.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import ru.ohayo.moneypr.data.room.category.CategoryDbo
import ru.ohayo.moneypr.data.room.category.CategoryType


interface CategoryRepository {
    fun getAllCategories(): Flow<List<CategoryDbo>>
    suspend fun insertAll(categories: List<CategoryDbo>)
    suspend fun insertCategory(categoryDbo: CategoryDbo)
    suspend fun isEmpty(): Boolean
    suspend fun getCategoryByName(name: String): CategoryDbo?


    fun getCategoryById(id: Long): Flow<CategoryDbo?>

    fun getCategoriesByType(type: CategoryType): Flow<List<CategoryDbo>>

    suspend fun updateOrderByType(categories: List<CategoryDbo>)

}




