package ru.ohayo.moneypr.repository


import kotlinx.coroutines.flow.Flow
import ru.ohayo.moneypr.R
import ru.ohayo.moneypr.data.room.currency.CurrencyDao
import ru.ohayo.moneypr.data.room.currency.CurrencyDbo
import ru.ohayo.moneypr.ui.screens.addAccount.components.AccountItem

class CurrencyRepositoryImpl(
    private val currencyDao: CurrencyDao
) : CurrencyRepository {

    override suspend fun insertAllCurrency(currency: List<CurrencyDbo>) {
        val existingCategories =
            currencyDao.getAllCurrencySync() // Синхронный метод для получения всех категорий
        val newCategories = currency.filter { newCurrency ->
            !existingCategories.any { it.fullName == newCurrency.fullName }
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
    override fun getAllCurrenciesFlow(): Flow<List<CurrencyDbo>> {
        return currencyDao.getAllCurrencies()
    }

    override suspend fun getAllCurrenciesList(): List<CurrencyDbo> {
        return currencyDao.getAllCurrencySync()
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

    override suspend fun getAllAccountItems(): List<AccountItem> {
        val currencyList = getAllCurrenciesList()
        return currencyList.toAccountItems()
    }


    private fun List<CurrencyDbo>.toAccountItems(): List<AccountItem> {
        return this.map { currencyDbo ->
            AccountItem(
                iconResId = currencyDbo.iconResId
                    ?: R.drawable.ic_default_none,
                mainText = currencyDbo.code,
                secondaryText = currencyDbo.fullName,
                symbol = currencyDbo.symbol
            )
        }
    }
}