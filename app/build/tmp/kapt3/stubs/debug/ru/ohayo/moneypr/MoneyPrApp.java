package ru.ohayo.moneypr;

@dagger.hilt.android.HiltAndroidApp
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006\u0011"}, d2 = {"Lru/ohayo/moneypr/MoneyPrApp;", "Landroid/app/Application;", "()V", "categoryRepository", "Lru/ohayo/moneypr/data/repository/CategoryRepository;", "getCategoryRepository", "()Lru/ohayo/moneypr/data/repository/CategoryRepository;", "setCategoryRepository", "(Lru/ohayo/moneypr/data/repository/CategoryRepository;)V", "currencyRepository", "Lru/ohayo/moneypr/data/repository/CurrencyRepository;", "getCurrencyRepository", "()Lru/ohayo/moneypr/data/repository/CurrencyRepository;", "setCurrencyRepository", "(Lru/ohayo/moneypr/data/repository/CurrencyRepository;)V", "onCreate", "", "app_debug"})
public final class MoneyPrApp extends android.app.Application {
    @javax.inject.Inject
    public ru.ohayo.moneypr.data.repository.CategoryRepository categoryRepository;
    @javax.inject.Inject
    public ru.ohayo.moneypr.data.repository.CurrencyRepository currencyRepository;
    
    public MoneyPrApp() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final ru.ohayo.moneypr.data.repository.CategoryRepository getCategoryRepository() {
        return null;
    }
    
    public final void setCategoryRepository(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.repository.CategoryRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final ru.ohayo.moneypr.data.repository.CurrencyRepository getCurrencyRepository() {
        return null;
    }
    
    public final void setCurrencyRepository(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.repository.CurrencyRepository p0) {
    }
    
    @java.lang.Override
    public void onCreate() {
    }
}