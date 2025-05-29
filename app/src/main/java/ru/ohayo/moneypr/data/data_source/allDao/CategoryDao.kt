package ru.ohayo.moneypr.data.data_source.allDao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.domain.allEntity.Category
import ru.ohayo.moneypr.domain.allEntity.CategoryType

@Dao
interface CategoryDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categories: List<Category>)

    @Query("SELECT * FROM category ORDER BY `order` DESC")
    fun getAllCategories(): Flow<List<Category>>

    @Delete
    suspend fun deleteCategory(category: Category)

    @Query("SELECT COUNT(*) == 0 FROM Category")
    suspend fun isEmpty(): Boolean

    @Query("SELECT * FROM Category WHERE id = :id")
    fun getCategoryById(id: Long): Flow<Category?>
    @Update
    suspend fun update(category: Category)

    // Если нужно массовое обновление
    @Query("UPDATE category SET `order` = :newOrder WHERE id = :id")
    suspend fun updateOrder(id: Long, newOrder: Int)
    @Query("SELECT MAX(`order`) FROM category WHERE type = :type")
    suspend fun getMaxOrder(type: CategoryType): Int?
    @Query("SELECT MAX(`order`) FROM Category WHERE type = :type")
    suspend fun getNextOrder(type: CategoryType): Int?
    // Получить категории по типу с сортировкой по order
    @Query("SELECT * FROM category WHERE type = :type ORDER BY `order` ASC")
    fun getCategoriesByType(type: CategoryType): Flow<List<Category>>

    @Transaction
    suspend fun updateOrderByType(categories: List<Category>) {
        categories.forEachIndexed { index, category ->
            updateOrder(category.id, index + 1)
        }
    }
    @Update
    suspend fun updateCategory(category: Category)
    @Query("SELECT * FROM category WHERE id = :id")
    suspend fun getCategoryByIdUpdate(id: Long): Category?

}







