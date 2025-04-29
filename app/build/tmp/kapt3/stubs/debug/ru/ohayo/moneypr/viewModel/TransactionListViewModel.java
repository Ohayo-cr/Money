package ru.ohayo.moneypr.viewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0015\u001a\u00020\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012\u00a8\u0006\u0017"}, d2 = {"Lru/ohayo/moneypr/viewModel/TransactionListViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lru/ohayo/moneypr/data/repository/TransactionRepository;", "(Lru/ohayo/moneypr/data/repository/TransactionRepository;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lru/ohayo/moneypr/viewModel/TransactionState;", "_transactions", "", "Lru/ohayo/moneypr/domain/TransactionEntity;", "scrollState", "Landroidx/compose/foundation/lazy/LazyListState;", "getScrollState", "()Landroidx/compose/foundation/lazy/LazyListState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "transactions", "getTransactions", "loadTransactions", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class TransactionListViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final ru.ohayo.moneypr.data.repository.TransactionRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<ru.ohayo.moneypr.viewModel.TransactionState> _state = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<ru.ohayo.moneypr.viewModel.TransactionState> state = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<ru.ohayo.moneypr.domain.TransactionEntity>> _transactions = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<ru.ohayo.moneypr.domain.TransactionEntity>> transactions = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.foundation.lazy.LazyListState scrollState = null;
    
    @javax.inject.Inject
    public TransactionListViewModel(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.repository.TransactionRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<ru.ohayo.moneypr.viewModel.TransactionState> getState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<ru.ohayo.moneypr.domain.TransactionEntity>> getTransactions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.compose.foundation.lazy.LazyListState getScrollState() {
        return null;
    }
    
    public final void loadTransactions() {
    }
}