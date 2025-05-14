package ru.ohayo.moneypr.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.ohayo.moneypr.data.data_source.account.AccountDao
import ru.ohayo.moneypr.data.data_source.category.CategoryDao
import ru.ohayo.moneypr.data.data_source.currency.CurrencyDao
import ru.ohayo.moneypr.data.data_source.transaction.AppDatabase
import ru.ohayo.moneypr.data.data_source.transaction.TransactionDao
import ru.ohayo.moneypr.data.repository.AccountRepositoryImpl
import ru.ohayo.moneypr.data.repository.CategoryRepository
import ru.ohayo.moneypr.data.repository.CategoryRepositoryImpl
import ru.ohayo.moneypr.data.repository.CurrencyRepository
import ru.ohayo.moneypr.data.repository.CurrencyRepositoryImpl
import ru.ohayo.moneypr.data.repository.ExpressionRepository
import ru.ohayo.moneypr.data.repository.ExpressionRepositoryImpl
import ru.ohayo.moneypr.domain.AccountRepository
import ru.ohayo.moneypr.viewModel.use_case.EvaluateExpressionUseCase
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
    fun provideEvaluateExpressionUseCase(): EvaluateExpressionUseCase {
        return EvaluateExpressionUseCase()
    }

    @Provides
    @Singleton
    fun provideExpressionRepository(): ExpressionRepository {
        return ExpressionRepositoryImpl()
    }

}

