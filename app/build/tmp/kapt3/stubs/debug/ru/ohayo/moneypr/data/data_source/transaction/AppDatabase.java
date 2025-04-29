package ru.ohayo.moneypr.data.data_source.transaction;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&\u00a8\u0006\f"}, d2 = {"Lru/ohayo/moneypr/data/data_source/transaction/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "accountDao", "Lru/ohayo/moneypr/data/data_source/account/AccountDao;", "categoryDao", "Lru/ohayo/moneypr/data/data_source/category/CategoryDao;", "currencyDao", "Lru/ohayo/moneypr/data/data_source/currency/CurrencyDao;", "transactionDao", "Lru/ohayo/moneypr/data/data_source/transaction/TransactionDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {ru.ohayo.moneypr.domain.TransactionEntity.class, ru.ohayo.moneypr.domain.Account.class, ru.ohayo.moneypr.domain.category.Category.class, ru.ohayo.moneypr.domain.currency.Currency.class}, version = 1)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String DATABASE_NAME = "transaction_db";
    @org.jetbrains.annotations.NotNull
    public static final ru.ohayo.moneypr.data.data_source.transaction.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract ru.ohayo.moneypr.data.data_source.transaction.TransactionDao transactionDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract ru.ohayo.moneypr.data.data_source.account.AccountDao accountDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract ru.ohayo.moneypr.data.data_source.category.CategoryDao categoryDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract ru.ohayo.moneypr.data.data_source.currency.CurrencyDao currencyDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lru/ohayo/moneypr/data/data_source/transaction/AppDatabase$Companion;", "", "()V", "DATABASE_NAME", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}