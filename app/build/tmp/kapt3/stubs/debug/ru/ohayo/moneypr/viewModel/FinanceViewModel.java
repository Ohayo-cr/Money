package ru.ohayo.moneypr.viewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000eJ\u000e\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u0012J\u000e\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u0015J\u000e\u0010!\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010\u00a8\u0006#"}, d2 = {"Lru/ohayo/moneypr/viewModel/FinanceViewModel;", "Landroidx/lifecycle/ViewModel;", "transactionDao", "Lru/ohayo/moneypr/data/data_source/transaction/TransactionDao;", "accountDao", "Lru/ohayo/moneypr/data/data_source/account/AccountDao;", "categoryDao", "Lru/ohayo/moneypr/data/data_source/category/CategoryDao;", "currencyDao", "Lru/ohayo/moneypr/data/data_source/currency/CurrencyDao;", "(Lru/ohayo/moneypr/data/data_source/transaction/TransactionDao;Lru/ohayo/moneypr/data/data_source/account/AccountDao;Lru/ohayo/moneypr/data/data_source/category/CategoryDao;Lru/ohayo/moneypr/data/data_source/currency/CurrencyDao;)V", "accounts", "Lkotlinx/coroutines/flow/Flow;", "", "Lru/ohayo/moneypr/domain/Account;", "getAccounts", "()Lkotlinx/coroutines/flow/Flow;", "categories", "Lru/ohayo/moneypr/domain/category/Category;", "getCategories", "currencies", "Lru/ohayo/moneypr/domain/currency/Currency;", "getCurrencies", "transactions", "Lru/ohayo/moneypr/domain/TransactionEntity;", "getTransactions", "addAccount", "Lkotlinx/coroutines/Job;", "account", "addCategory", "category", "addCurrency", "currency", "addTransaction", "transaction", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class FinanceViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final ru.ohayo.moneypr.data.data_source.transaction.TransactionDao transactionDao = null;
    @org.jetbrains.annotations.NotNull
    private final ru.ohayo.moneypr.data.data_source.account.AccountDao accountDao = null;
    @org.jetbrains.annotations.NotNull
    private final ru.ohayo.moneypr.data.data_source.category.CategoryDao categoryDao = null;
    @org.jetbrains.annotations.NotNull
    private final ru.ohayo.moneypr.data.data_source.currency.CurrencyDao currencyDao = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.TransactionEntity>> transactions = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.Account>> accounts = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.category.Category>> categories = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.currency.Currency>> currencies = null;
    
    @javax.inject.Inject
    public FinanceViewModel(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.data_source.transaction.TransactionDao transactionDao, @org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.data_source.account.AccountDao accountDao, @org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.data_source.category.CategoryDao categoryDao, @org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.data_source.currency.CurrencyDao currencyDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.TransactionEntity>> getTransactions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.Account>> getAccounts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.category.Category>> getCategories() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.currency.Currency>> getCurrencies() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job addTransaction(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.TransactionEntity transaction) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job addAccount(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.Account account) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job addCategory(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.category.Category category) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job addCurrency(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.currency.Currency currency) {
        return null;
    }
}