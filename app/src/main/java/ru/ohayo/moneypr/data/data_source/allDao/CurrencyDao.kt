package ru.ohayo.moneypr.data.data_source.allDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.domain.allEntity.CurrencyDbo

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(currency: CurrencyDbo)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllCurrency(currency: List<CurrencyDbo>)

    @Query("SELECT * FROM CurrencyDbo")
    fun getAllCurrencies(): Flow<List<CurrencyDbo>>

    @Query("SELECT COUNT(*) == 0 FROM CurrencyDbo")
    suspend fun isCurrencyEmpty(): Boolean

    @Query("SELECT * FROM CurrencyDbo")
    suspend fun getAllCurrencySync(): List<CurrencyDbo>

    @Query("SELECT * FROM CurrencyDbo WHERE id = :id")
    suspend fun getCurrencyById(id: Long): CurrencyDbo?

}