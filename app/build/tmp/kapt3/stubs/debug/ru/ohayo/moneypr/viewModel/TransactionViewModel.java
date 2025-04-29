package ru.ohayo.moneypr.viewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJK\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\"2\b\u0010&\u001a\u0004\u0018\u00010\"2\b\u0010\'\u001a\u0004\u0018\u00010\"\u00a2\u0006\u0002\u0010(J\u000e\u0010\u001c\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020*J\u000e\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\"J\u001b\u0010-\u001a\u0004\u0018\u00010 2\u0006\u0010.\u001a\u00020\"H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010/J\u0016\u00100\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001010\u00102\u0006\u00102\u001a\u00020\"J\u001b\u00103\u001a\u0004\u0018\u00010 2\u0006\u00104\u001a\u00020\"H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010/J\u001b\u00105\u001a\u0004\u0018\u00010*2\u0006\u0010,\u001a\u00020\"H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010/J\u000e\u00106\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020*R\u001f\u0010\u000b\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r0\fX\u0082\u0004\u00f8\u0001\u0000\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00110\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\"\u0010\u0018\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r0\u0019\u00f8\u0001\u0000\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00067"}, d2 = {"Lru/ohayo/moneypr/viewModel/TransactionViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lru/ohayo/moneypr/data/repository/TransactionRepository;", "accountRepository", "Lru/ohayo/moneypr/domain/AccountRepository;", "currencyRepository", "Lru/ohayo/moneypr/data/repository/CurrencyRepository;", "categoryRepository", "Lru/ohayo/moneypr/data/repository/CategoryRepository;", "(Lru/ohayo/moneypr/data/repository/TransactionRepository;Lru/ohayo/moneypr/domain/AccountRepository;Lru/ohayo/moneypr/data/repository/CurrencyRepository;Lru/ohayo/moneypr/data/repository/CategoryRepository;)V", "_transactionResult", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lkotlin/Result;", "", "accounts", "Lkotlinx/coroutines/flow/Flow;", "", "Lru/ohayo/moneypr/domain/Account;", "getAccounts", "()Lkotlinx/coroutines/flow/Flow;", "currencies", "Lru/ohayo/moneypr/domain/currency/Currency;", "getCurrencies", "transactionResult", "Lkotlinx/coroutines/flow/StateFlow;", "getTransactionResult", "()Lkotlinx/coroutines/flow/StateFlow;", "addTransaction", "amount", "", "content", "", "timestamp", "", "category", "", "fromAccount", "toAccount", "currency", "(DLjava/lang/String;JILjava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;)V", "transaction", "Lru/ohayo/moneypr/domain/TransactionEntity;", "deleteTransaction", "id", "getAccountName", "accountId", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCategoryById", "Lru/ohayo/moneypr/domain/category/Category;", "categoryId", "getCurrencyName", "currencyId", "getTransactionById", "updateTransaction", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class TransactionViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final ru.ohayo.moneypr.data.repository.TransactionRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final ru.ohayo.moneypr.domain.AccountRepository accountRepository = null;
    @org.jetbrains.annotations.NotNull
    private final ru.ohayo.moneypr.data.repository.CurrencyRepository currencyRepository = null;
    @org.jetbrains.annotations.NotNull
    private final ru.ohayo.moneypr.data.repository.CategoryRepository categoryRepository = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<kotlin.Result<kotlin.Unit>> _transactionResult = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<kotlin.Result<kotlin.Unit>> transactionResult = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.Account>> accounts = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.currency.Currency>> currencies = null;
    
    @javax.inject.Inject
    public TransactionViewModel(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.repository.TransactionRepository repository, @org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.AccountRepository accountRepository, @org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.repository.CurrencyRepository currencyRepository, @org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.repository.CategoryRepository categoryRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<kotlin.Result<kotlin.Unit>> getTransactionResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.Account>> getAccounts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.currency.Currency>> getCurrencies() {
        return null;
    }
    
    public final void addTransaction(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.TransactionEntity transaction) {
    }
    
    public final void updateTransaction(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.TransactionEntity transaction) {
    }
    
    public final void deleteTransaction(long id) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getTransactionById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super ru.ohayo.moneypr.domain.TransactionEntity> $completion) {
        return null;
    }
    
    public final void addTransaction(double amount, @org.jetbrains.annotations.Nullable
    java.lang.String content, long timestamp, int category, @org.jetbrains.annotations.Nullable
    java.lang.Long fromAccount, @org.jetbrains.annotations.Nullable
    java.lang.Long toAccount, @org.jetbrains.annotations.Nullable
    java.lang.Long currency) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getCurrencyName(long currencyId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<ru.ohayo.moneypr.domain.category.Category> getCategoryById(long categoryId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getAccountName(long accountId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
}