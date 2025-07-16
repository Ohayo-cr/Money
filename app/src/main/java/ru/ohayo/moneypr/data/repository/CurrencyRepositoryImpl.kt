package ru.ohayo.moneypr.data.repository


import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.data.data_source.allDao.CurrencyDao
import ru.ohayo.moneypr.domain.allEntity.CurrencyDbo

class CurrencyRepositoryImpl(
    private val currencyDao: CurrencyDao
) : CurrencyRepository {

    override suspend fun insertAllCurrency(currency: List<CurrencyDbo>) {
        val existingCategories = currencyDao.getAllCurrencySync() // Синхронный метод для получения всех категорий
        val newCategories = currency.filter { newCurrency ->
            !existingCategories.any { it.name == newCurrency.name }
        }
        currencyDao.insertAllCurrency(currency)
    }

    /**
     * Вставка валюты в базу данных.
     */
    override suspend fun insertCurrency(currency: CurrencyDbo) {
        currencyDao.insertCurrency(currency)
    }

    /**
     * Получение всех валют из базы данных.
     */
    override fun getAllCurrencies(): Flow<List<CurrencyDbo>> {
        return currencyDao.getAllCurrencies()
    }

    /**
     * Проверка, пуста ли таблица валют.
     */
    override suspend fun isCurrencyEmpty(): Boolean {
        return currencyDao.isCurrencyEmpty()
    }
    override suspend fun getCurrencyById(id: Long): CurrencyDbo? {
        return currencyDao.getCurrencyById(id)
    }
    override suspend fun getCurrencySymbol(id: Long): String? =
        currencyDao.getCurrencyById(id)?.symbol
}