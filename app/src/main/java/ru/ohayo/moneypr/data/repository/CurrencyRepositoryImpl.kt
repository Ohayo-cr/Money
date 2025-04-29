package ru.ohayo.moneypr.data.repository


import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.data.data_source.currency.CurrencyDao
import ru.ohayo.moneypr.domain.category.Category
import ru.ohayo.moneypr.domain.currency.Currency

class CurrencyRepositoryImpl(
    private val currencyDao: CurrencyDao
) : CurrencyRepository {

    override suspend fun insertAllCurrency(currency: List<Currency>) {
        val existingCategories = currencyDao.getAllCurrencySync() // Синхронный метод для получения всех категорий
        val newCategories = currency.filter { newCurrency ->
            !existingCategories.any { it.name == newCurrency.name }
        }
        currencyDao.insertAllCurrency(currency)
    }

    /**
     * Вставка валюты в базу данных.
     */
    override suspend fun insertCurrency(currency: Currency) {
        currencyDao.insertCurrency(currency)
    }

    /**
     * Получение всех валют из базы данных.
     */
    override fun getAllCurrencies(): Flow<List<Currency>> {
        return currencyDao.getAllCurrencies()
    }

    /**
     * Проверка, пуста ли таблица валют.
     */
    override suspend fun isCurrencyEmpty(): Boolean {
        return currencyDao.isCurrencyEmpty()
    }
    override suspend fun getCurrencyById(id: Long): Currency? {
        return currencyDao.getCurrencyById(id)
    }
}