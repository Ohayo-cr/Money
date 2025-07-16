package ru.ohayo.moneypr.data.data_source.allDao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.domain.allEntity.CategoryDbo
import ru.ohayo.moneypr.domain.allEntity.CategoryType

@Dao
interface CategoryDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryDbo: CategoryDbo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categories: List<CategoryDbo>)

    @Query("SELECT * FROM categoryDbo ORDER BY `order` DESC")
    fun getAllCategories(): Flow<List<CategoryDbo>>

    @Delete
    suspend fun deleteCategory(categoryDbo: CategoryDbo)

    @Query("SELECT COUNT(*) == 0 FROM CategoryDbo")
    suspend fun isEmpty(): Boolean

    @Query("SELECT * FROM CategoryDbo WHERE id = :id")
    fun getCategoryById(id: Long): Flow<CategoryDbo?>
    @Update
    suspend fun update(categoryDbo: CategoryDbo)

    // Если нужно массовое обновление
    @Query("UPDATE categoryDbo SET `order` = :newOrder WHERE id = :id")
    suspend fun updateOrder(id: Long, newOrder: Int)
    @Query("SELECT MAX(`order`) FROM categoryDbo WHERE type = :type")
    suspend fun getMaxOrder(type: CategoryType): Int?
    @Query("SELECT MAX(`order`) FROM CategoryDbo WHERE type = :type")
    suspend fun getNextOrder(type: CategoryType): Int?
    // Получить категории по типу с сортировкой по order
    @Query("SELECT * FROM categoryDbo WHERE type = :type ORDER BY `order` ASC")
    fun getCategoriesByType(type: CategoryType): Flow<List<CategoryDbo>>

    @Transaction
    suspend fun updateOrderByType(categories: List<CategoryDbo>) {
        categories.forEachIndexed { index, category ->
            updateOrder(category.id, index + 1)
        }
    }
    @Update
    suspend fun updateCategory(categoryDbo: CategoryDbo)
    @Query("SELECT * FROM categoryDbo WHERE id = :id")
    suspend fun getCategoryByIdUpdate(id: Long): CategoryDbo?

}







