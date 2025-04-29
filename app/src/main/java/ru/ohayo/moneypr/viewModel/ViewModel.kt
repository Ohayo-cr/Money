package ru.ohayo.moneypr.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.data.data_source.account.AccountDao
import ru.ohayo.moneypr.data.data_source.category.CategoryDao
import ru.ohayo.moneypr.data.data_source.currency.CurrencyDao
import ru.ohayo.moneypr.data.data_source.transaction.TransactionDao
import ru.ohayo.moneypr.domain.Account
import ru.ohayo.moneypr.domain.TransactionEntity
import ru.ohayo.moneypr.domain.category.Category
import ru.ohayo.moneypr.domain.currency.Currency
import javax.inject.Inject

@HiltViewModel
class FinanceViewModel @Inject constructor(
    private val transactionDao: TransactionDao,
    private val accountDao: AccountDao,
    private val categoryDao: CategoryDao,
    private val currencyDao: CurrencyDao
) : ViewModel() {

    val transactions = transactionDao.getAllTransactions()
    val accounts = accountDao.getAllAccounts()
    val categories = categoryDao.getAllCategories()
    val currencies = currencyDao.getAllCurrencies()

    fun addTransaction(transaction: TransactionEntity) = viewModelScope.launch {
        transactionDao.insertTransaction(transaction)
    }

    fun addAccount(account: Account) = viewModelScope.launch {
        accountDao.insertAccount(account)
    }

    fun addCategory(category: Category) = viewModelScope.launch {
        categoryDao.insertCategory(category)
    }

    fun addCurrency(currency: Currency) = viewModelScope.launch {
        currencyDao.insertCurrency(currency)
    }
}