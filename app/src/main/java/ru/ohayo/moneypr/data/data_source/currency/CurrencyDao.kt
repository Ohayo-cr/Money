package ru.ohayo.moneypr.data.data_source.currency

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.domain.category.Category
import ru.ohayo.moneypr.domain.currency.Currency

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(currency: Currency)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllCurrency(currency: List<Currency>)

    @Query("SELECT * FROM Currency")
    fun getAllCurrencies(): Flow<List<Currency>>

    @Query("SELECT COUNT(*) == 0 FROM Currency")
    suspend fun isCurrencyEmpty(): Boolean

    @Query("SELECT * FROM Currency")
    suspend fun getAllCurrencySync(): List<Currency>

    @Query("SELECT * FROM currency WHERE id = :id")
    suspend fun getCurrencyById(id: Long): Currency?

}