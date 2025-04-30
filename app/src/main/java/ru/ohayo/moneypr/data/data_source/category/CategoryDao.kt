package ru.ohayo.moneypr.data.data_source.category

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.domain.category.Category

@Dao
interface CategoryDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categories: List<Category>)

    @Query("SELECT * FROM category ORDER BY `order` ASC")
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

}







