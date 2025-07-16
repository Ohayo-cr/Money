package ru.ohayo.moneypr.data.repository


import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.domain.allEntity.CurrencyDbo

interface CurrencyRepository {
    suspend fun insertCurrency(currency: CurrencyDbo)
    fun getAllCurrencies(): Flow<List<CurrencyDbo>>
    suspend fun isCurrencyEmpty(): Boolean
    suspend fun insertAllCurrency(currency: List<CurrencyDbo>)
    suspend fun getCurrencyById(id: Long): CurrencyDbo?
    suspend fun getCurrencySymbol(id: Long): String?
}