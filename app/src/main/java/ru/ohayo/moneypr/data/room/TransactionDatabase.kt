package ru.ohayo.moneypr.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.ohayo.moneypr.data.room.account.AccountDao
import ru.ohayo.moneypr.data.room.category.CategoryDao
import ru.ohayo.moneypr.data.room.currency.CurrencyDao
import ru.ohayo.moneypr.data.room.transaction.TransactionDao
import ru.ohayo.moneypr.data.room.account.AccountDbo
import ru.ohayo.moneypr.data.room.transaction.TransactionDbo
import ru.ohayo.moneypr.data.room.category.CategoryDbo
import ru.ohayo.moneypr.data.room.currency.CurrencyDbo


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

