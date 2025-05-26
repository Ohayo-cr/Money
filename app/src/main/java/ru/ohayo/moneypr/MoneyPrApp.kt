package ru.ohayo.moneypr

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.data.repository.AccountRepository
import ru.ohayo.moneypr.data.repository.CategoryRepository
import ru.ohayo.moneypr.data.repository.CurrencyRepository
import ru.ohayo.moneypr.domain.allEntity.Account
import ru.ohayo.moneypr.domain.useCase.DefaultAccounts
import ru.ohayo.moneypr.domain.useCase.DefaultCategories
import ru.ohayo.moneypr.domain.useCase.DefaultCurrency
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
                val initializedCategories = DefaultCategories.DEFAULT_CATEGORIES
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
                currencyRepository.insertAllCurrency(DefaultCurrency.DEFAULT_CURRENCY)
            }
                if (accountRepository.isAccountsEmpty()) {
                    accountRepository.insertAllAccount(DefaultAccounts.DEFAULT_ACCOUNTS)
                }
            }
        }
    }


