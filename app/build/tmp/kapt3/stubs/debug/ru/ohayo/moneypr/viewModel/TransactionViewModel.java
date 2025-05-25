package ru.ohayo.moneypr.viewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u000e\u0010%\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\'J\u001b\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010*\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010+J\u0016\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010-0\u00132\u0006\u0010.\u001a\u00020\rJ\u001b\u0010/\u001a\u0004\u0018\u00010)2\u0006\u00100\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010+J\u0006\u00101\u001a\u00020\u0011J\u000e\u00102\u001a\u00020\u00112\u0006\u00103\u001a\u00020\rR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001f\u0010\u000f\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00100\fX\u0082\u0004\u00f8\u0001\u0000\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00140\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\r0\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\r0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\r0\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001eR\"\u0010#\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00100\u001c\u00f8\u0001\u0000\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001e\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00064"}, d2 = {"Lru/ohayo/moneypr/viewModel/TransactionViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lru/ohayo/moneypr/data/repository/TransactionRepository;", "accountRepository", "Lru/ohayo/moneypr/data/repository/AccountRepository;", "currencyRepository", "Lru/ohayo/moneypr/data/repository/CurrencyRepository;", "categoryRepository", "Lru/ohayo/moneypr/data/repository/CategoryRepository;", "(Lru/ohayo/moneypr/data/repository/TransactionRepository;Lru/ohayo/moneypr/data/repository/AccountRepository;Lru/ohayo/moneypr/data/repository/CurrencyRepository;Lru/ohayo/moneypr/data/repository/CategoryRepository;)V", "_currentDate", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_selectedDate", "_transactionResult", "Lkotlin/Result;", "", "accounts", "Lkotlinx/coroutines/flow/Flow;", "", "Lru/ohayo/moneypr/domain/allEntity/Account;", "getAccounts", "()Lkotlinx/coroutines/flow/Flow;", "currencies", "Lru/ohayo/moneypr/domain/allEntity/Currency;", "getCurrencies", "currentDate", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrentDate", "()Lkotlinx/coroutines/flow/StateFlow;", "lastSelectedDate", "getLastSelectedDate", "selectedDate", "getSelectedDate", "transactionResult", "getTransactionResult", "addTransaction", "transaction", "Lru/ohayo/moneypr/domain/allEntity/TransactionEntity;", "getAccountName", "", "accountId", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCategoryById", "Lru/ohayo/moneypr/domain/allEntity/Category;", "categoryId", "getCurrencySymbol", "currencyId", "resetSelectedDate", "setSelectedDate", "date", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class TransactionViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final ru.ohayo.moneypr.data.repository.TransactionRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final ru.ohayo.moneypr.data.repository.AccountRepository accountRepository = null;
    @org.jetbrains.annotations.NotNull
    private final ru.ohayo.moneypr.data.repository.CurrencyRepository currencyRepository = null;
    @org.jetbrains.annotations.NotNull
    private final ru.ohayo.moneypr.data.repository.CategoryRepository categoryRepository = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<kotlin.Result<kotlin.Unit>> _transactionResult = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<kotlin.Result<kotlin.Unit>> transactionResult = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.allEntity.Account>> accounts = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.allEntity.Currency>> currencies = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Long> _currentDate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Long> currentDate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.lang.Long> lastSelectedDate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Long> _selectedDate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Long> selectedDate = null;
    
    @javax.inject.Inject
    public TransactionViewModel(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.repository.TransactionRepository repository, @org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.repository.AccountRepository accountRepository, @org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.repository.CurrencyRepository currencyRepository, @org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.repository.CategoryRepository categoryRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<kotlin.Result<kotlin.Unit>> getTransactionResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.allEntity.Account>> getAccounts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.allEntity.Currency>> getCurrencies() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Long> getCurrentDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.lang.Long> getLastSelectedDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Long> getSelectedDate() {
        return null;
    }
    
    public final void setSelectedDate(long date) {
    }
    
    public final void resetSelectedDate() {
    }
    
    public final void addTransaction(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.allEntity.TransactionEntity transaction) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<ru.ohayo.moneypr.domain.allEntity.Category> getCategoryById(long categoryId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getAccountName(long accountId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getCurrencySymbol(long currencyId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
}