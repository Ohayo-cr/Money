package ru.ohayo.moneypr

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.repository.AccountRepository
import ru.ohayo.moneypr.repository.CategoryRepository
import ru.ohayo.moneypr.repository.CurrencyRepository
import ru.ohayo.moneypr.models.initialData.InitialAccounts
import ru.ohayo.moneypr.models.initialData.InitialCategories
import ru.ohayo.moneypr.models.initialData.InitialCurrency
import javax.inject.Inject

@HiltAndroidApp
class MoneyPrApp : Application() {

    @Inject
    lateinit var categoryRepository: CategoryRepository
    @Inject
    lateinit var accountRepository: AccountRepository

    @Inject
    lateinit var currencyRepository: CurrencyRepository

    override fun onCreate() {
        super.onCreate()

        // Инициализация базовых категорий
        MainScope().launch {
            if (categoryRepository.isEmpty()) {
                // Группируем категории по типу и назначаем порядок для каждой группы
                val initializedCategories = InitialCategories.INITIAL_CATEGORIES
                    .groupBy { it.type }
                    .flatMap { (type, categories) ->
                        categories.mapIndexed { index, category ->
                            category.copy(order = index + 1) // order начинается с 1
                        }
                    }

                categoryRepository.insertAll(initializedCategories)
            }
        }
        // Инициализация базовых валют
        MainScope().launch {
            if (currencyRepository.isCurrencyEmpty()) {
                currencyRepository.insertAllCurrency(InitialCurrency.INITIAL_CURRENCY)
            }
                if (accountRepository.isAccountsEmpty()) {
                    accountRepository.insertAllAccount(InitialAccounts.INITIAL_ACCOUNT)
                }
            }
        }
    }


