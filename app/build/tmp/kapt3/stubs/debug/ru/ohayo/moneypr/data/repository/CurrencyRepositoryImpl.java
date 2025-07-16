package ru.ohayo.moneypr.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006H\u0016J\u001b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\u000bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u001b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\n\u001a\u00020\u000bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u001f\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u0019\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J\u0011\u0010\u0015\u001a\u00020\u0016H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0018"}, d2 = {"Lru/ohayo/moneypr/data/repository/CurrencyRepositoryImpl;", "Lru/ohayo/moneypr/data/repository/CurrencyRepository;", "currencyDao", "Lru/ohayo/moneypr/data/data_source/allDao/CurrencyDao;", "(Lru/ohayo/moneypr/data/data_source/allDao/CurrencyDao;)V", "getAllCurrencies", "Lkotlinx/coroutines/flow/Flow;", "", "Lru/ohayo/moneypr/domain/allEntity/CurrencyDbo;", "getCurrencyById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrencySymbol", "", "insertAllCurrency", "", "currency", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertCurrency", "(Lru/ohayo/moneypr/domain/allEntity/CurrencyDbo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isCurrencyEmpty", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class CurrencyRepositoryImpl implements ru.ohayo.moneypr.data.repository.CurrencyRepository {
    @org.jetbrains.annotations.NotNull
    private final ru.ohayo.moneypr.data.data_source.allDao.CurrencyDao currencyDao = null;
    
    public CurrencyRepositoryImpl(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.data_source.allDao.CurrencyDao currencyDao) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object insertAllCurrency(@org.jetbrains.annotations.NotNull
    java.util.List<ru.ohayo.moneypr.domain.allEntity.CurrencyDbo> currency, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Вставка валюты в базу данных.
     */
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object insertCurrency(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.allEntity.CurrencyDbo currency, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Получение всех валют из базы данных.
     */
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.allEntity.CurrencyDbo>> getAllCurrencies() {
        return null;
    }
    
    /**
     * Проверка, пуста ли таблица валют.
     */
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object isCurrencyEmpty(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getCurrencyById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super ru.ohayo.moneypr.domain.allEntity.CurrencyDbo> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getCurrencySymbol(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
}