package ru.ohayo.moneypr.data.data_source.currency;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u001b\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u001f\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0019\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0011\u0010\u0012\u001a\u00020\u0013H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0014"}, d2 = {"Lru/ohayo/moneypr/data/data_source/currency/CurrencyDao;", "", "getAllCurrencies", "Lkotlinx/coroutines/flow/Flow;", "", "Lru/ohayo/moneypr/domain/currency/Currency;", "getAllCurrencySync", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrencyById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAllCurrency", "", "currency", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertCurrency", "(Lru/ohayo/moneypr/domain/currency/Currency;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isCurrencyEmpty", "", "app_debug"})
@androidx.room.Dao
public abstract interface CurrencyDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertCurrency(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.currency.Currency currency, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 5)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertAllCurrency(@org.jetbrains.annotations.NotNull
    java.util.List<ru.ohayo.moneypr.domain.currency.Currency> currency, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM Currency")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.currency.Currency>> getAllCurrencies();
    
    @androidx.room.Query(value = "SELECT COUNT(*) == 0 FROM Currency")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object isCurrencyEmpty(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM Currency")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAllCurrencySync(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<ru.ohayo.moneypr.domain.currency.Currency>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM currency WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getCurrencyById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super ru.ohayo.moneypr.domain.currency.Currency> $completion);
}