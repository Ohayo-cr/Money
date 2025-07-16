package ru.ohayo.moneypr.di;

@dagger.Module
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0012\u0010\n\u001a\u00020\u00062\b\b\u0001\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000eH\u0007J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0013H\u0007J\b\u0010\u0017\u001a\u00020\u0018H\u0007J\b\u0010\u0019\u001a\u00020\u001aH\u0007J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u001d"}, d2 = {"Lru/ohayo/moneypr/di/AppModule;", "", "()V", "provideAccountDao", "Lru/ohayo/moneypr/data/room/account/AccountDao;", "appDatabase", "Lru/ohayo/moneypr/data/room/AppDatabase;", "provideAccountRepository", "Lru/ohayo/moneypr/repository/AccountRepository;", "accountDao", "provideAppDatabase", "context", "Landroid/content/Context;", "provideCategoryDao", "Lru/ohayo/moneypr/data/room/category/CategoryDao;", "provideCategoryRepository", "Lru/ohayo/moneypr/repository/CategoryRepository;", "categoryDao", "provideCurrencyDao", "Lru/ohayo/moneypr/data/room/currency/CurrencyDao;", "provideCurrencyRepository", "Lru/ohayo/moneypr/repository/CurrencyRepository;", "currencyDao", "provideEvaluateExpressionUseCase", "Lru/ohayo/moneypr/ui/component/customeKeyboard/ExpressionCalculator;", "provideExpressionRepository", "Lru/ohayo/moneypr/repository/ExpressionRepository;", "provideTransactionDao", "Lru/ohayo/moneypr/data/room/transaction/TransactionDao;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class AppModule {
    @org.jetbrains.annotations.NotNull
    public static final ru.ohayo.moneypr.di.AppModule INSTANCE = null;
    
    private AppModule() {
        super();
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final ru.ohayo.moneypr.data.room.AppDatabase provideAppDatabase(@dagger.hilt.android.qualifiers.ApplicationContext
    @org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final ru.ohayo.moneypr.data.room.transaction.TransactionDao provideTransactionDao(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.room.AppDatabase appDatabase) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final ru.ohayo.moneypr.data.room.account.AccountDao provideAccountDao(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.room.AppDatabase appDatabase) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final ru.ohayo.moneypr.data.room.category.CategoryDao provideCategoryDao(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.room.AppDatabase appDatabase) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final ru.ohayo.moneypr.data.room.currency.CurrencyDao provideCurrencyDao(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.room.AppDatabase appDatabase) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final ru.ohayo.moneypr.repository.CategoryRepository provideCategoryRepository(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.room.category.CategoryDao categoryDao) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final ru.ohayo.moneypr.repository.CurrencyRepository provideCurrencyRepository(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.room.currency.CurrencyDao currencyDao) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final ru.ohayo.moneypr.repository.AccountRepository provideAccountRepository(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.room.account.AccountDao accountDao) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final ru.ohayo.moneypr.ui.component.customeKeyboard.ExpressionCalculator provideEvaluateExpressionUseCase() {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final ru.ohayo.moneypr.repository.ExpressionRepository provideExpressionRepository() {
        return null;
    }
}