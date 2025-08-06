package ru.ohayo.moneypr.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.ohayo.moneypr.data.room.account.AccountDao
import ru.ohayo.moneypr.data.room.category.CategoryDao
import ru.ohayo.moneypr.data.room.currency.CurrencyDao
import ru.ohayo.moneypr.data.room.AppDatabase
import ru.ohayo.moneypr.data.room.transaction.TransactionDao
import ru.ohayo.moneypr.repository.AccountRepositoryImpl
import ru.ohayo.moneypr.repository.CategoryRepository
import ru.ohayo.moneypr.repository.CategoryRepositoryImpl
import ru.ohayo.moneypr.repository.CurrencyRepository
import ru.ohayo.moneypr.repository.CurrencyRepositoryImpl
import ru.ohayo.moneypr.repository.CalculatorRepository
import ru.ohayo.moneypr.repository.CalculatorRepositoryImpl
import ru.ohayo.moneypr.repository.AccountRepository
import ru.ohayo.moneypr.domain.calculator.ExpressionCalculator
import ru.ohayo.moneypr.repository.ChartsRepository
import ru.ohayo.moneypr.repository.ChartsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTransactionDao(appDatabase: AppDatabase): TransactionDao {
        return appDatabase.transactionDao()
    }

    @Provides
    @Singleton
    fun provideAccountDao(appDatabase: AppDatabase): AccountDao {
        return appDatabase.accountDao()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(appDatabase: AppDatabase): CategoryDao {
        return appDatabase.categoryDao()
    }

    @Provides
    @Singleton
    fun provideCurrencyDao(appDatabase: AppDatabase): CurrencyDao {
        return appDatabase.currencyDao()
    }
    @Provides
    @Singleton
    fun provideCategoryRepository(categoryDao: CategoryDao): CategoryRepository {
        return CategoryRepositoryImpl(categoryDao)
    }
    @Provides
    @Singleton
    fun provideCurrencyRepository(currencyDao: CurrencyDao): CurrencyRepository {
        return CurrencyRepositoryImpl(currencyDao)
    }
    @Provides
    @Singleton
    fun provideAccountRepository(accountDao: AccountDao): AccountRepository {
        return AccountRepositoryImpl(accountDao)
    }
    @Provides
    @Singleton
    fun provideEvaluateExpressionUseCase(): ExpressionCalculator {
        return ExpressionCalculator()
    }

    @Provides
    @Singleton
    fun provideExpressionRepository(): CalculatorRepository {
        return CalculatorRepositoryImpl()
    }
    @Provides
    @Singleton
    fun provideChartsRepository(transactionDao: TransactionDao): ChartsRepository {
        return ChartsRepositoryImpl(transactionDao)
    }

}

