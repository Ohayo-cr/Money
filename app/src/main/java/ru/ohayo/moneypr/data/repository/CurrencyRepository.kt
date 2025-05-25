package ru.ohayo.moneypr.data.repository


import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.domain.allEntity.Currency

interface CurrencyRepository {
    suspend fun insertCurrency(currency: Currency)
    fun getAllCurrencies(): Flow<List<Currency>>
    suspend fun isCurrencyEmpty(): Boolean
    suspend fun insertAllCurrency(currency: List<Currency>)
    suspend fun getCurrencyById(id: Long): Currency?
    suspend fun getCurrencySymbol(id: Long): String?
}