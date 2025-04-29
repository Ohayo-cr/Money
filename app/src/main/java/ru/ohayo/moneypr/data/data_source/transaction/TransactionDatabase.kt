package ru.ohayo.moneypr.data.data_source.transaction

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.ohayo.moneypr.data.data_source.account.AccountDao
import ru.ohayo.moneypr.data.data_source.category.CategoryDao
import ru.ohayo.moneypr.data.data_source.currency.CurrencyDao
import ru.ohayo.moneypr.domain.Account
import ru.ohayo.moneypr.domain.TransactionEntity
import ru.ohayo.moneypr.domain.category.Category
import ru.ohayo.moneypr.domain.currency.Currency


@Database(
    entities = [TransactionEntity::class, Account::class, Category::class, Currency::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun accountDao(): AccountDao
    abstract fun categoryDao(): CategoryDao
    abstract fun currencyDao(): CurrencyDao

    companion object {
        const val DATABASE_NAME = "transaction_db"
    }
}

