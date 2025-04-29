package ru.ohayo.moneypr

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.data.repository.CategoryRepository
import ru.ohayo.moneypr.data.repository.CurrencyRepository
import ru.ohayo.moneypr.domain.useCase.DefaultCategories
import ru.ohayo.moneypr.domain.useCase.DefaultCurrency
import javax.inject.Inject

@HiltAndroidApp
class MoneyPrApp : Application() {

    @Inject
    lateinit var categoryRepository: CategoryRepository

    @Inject
    lateinit var currencyRepository: CurrencyRepository

    override fun onCreate() {
        super.onCreate()

        // Инициализация базовых категорий
        MainScope().launch {
            if (categoryRepository.isEmpty()) {
                categoryRepository.insertAll(DefaultCategories.DEFAULT_CATEGORIES)
            }
        }

        // Инициализация базовых валют
        MainScope().launch {
            if (currencyRepository.isCurrencyEmpty()) {
                currencyRepository.insertAllCurrency(DefaultCurrency.DEFAULT_CURRENCY)
            }
        }
    }
}

