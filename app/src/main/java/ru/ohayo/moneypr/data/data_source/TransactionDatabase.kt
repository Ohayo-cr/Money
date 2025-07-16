package ru.ohayo.moneypr.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.ohayo.moneypr.data.data_source.allDao.AccountDao
import ru.ohayo.moneypr.data.data_source.allDao.CategoryDao
import ru.ohayo.moneypr.data.data_source.allDao.CurrencyDao
import ru.ohayo.moneypr.data.data_source.allDao.TransactionDao
import ru.ohayo.moneypr.domain.allEntity.AccountDbo
import ru.ohayo.moneypr.domain.allEntity.TransactionDbo
import ru.ohayo.moneypr.domain.allEntity.CategoryDbo
import ru.ohayo.moneypr.domain.allEntity.CurrencyDbo


@Database(
    entities = [TransactionDbo::class, AccountDbo::class, CategoryDbo::class, CurrencyDbo::class],
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

