package ru.ohayo.moneypr.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.ohayo.moneypr.data.data_source.allDao.AccountDao
import ru.ohayo.moneypr.data.data_source.allDao.CategoryDao
import ru.ohayo.moneypr.data.data_source.allDao.CurrencyDao
import ru.ohayo.moneypr.data.data_source.allDao.TransactionDao
import ru.ohayo.moneypr.domain.allEntity.Account
import ru.ohayo.moneypr.domain.allEntity.TransactionEntity
import ru.ohayo.moneypr.domain.allEntity.Category
import ru.ohayo.moneypr.domain.allEntity.Currency


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

