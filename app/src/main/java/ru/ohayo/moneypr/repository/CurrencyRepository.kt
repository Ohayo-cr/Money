package ru.ohayo.moneypr.repository


import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.data.room.currency.CurrencyDbo
import ru.ohayo.moneypr.ui.screens.addAccount.components.AccountItem

interface CurrencyRepository {
    suspend fun insertCurrency(currency: CurrencyDbo)
    fun getAllCurrenciesFlow(): Flow<List<CurrencyDbo>>
    suspend fun getAllCurrenciesList(): List<CurrencyDbo>
    suspend fun getAllAccountItems() : List<AccountItem>
    suspend fun isCurrencyEmpty(): Boolean
    suspend fun insertAllCurrency(currency: List<CurrencyDbo>)
    suspend fun getCurrencyById(id: Long): CurrencyDbo?
    suspend fun getCurrencySymbol(id: Long): String?
}