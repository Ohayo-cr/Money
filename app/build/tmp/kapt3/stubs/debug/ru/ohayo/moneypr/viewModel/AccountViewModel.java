package ru.ohayo.moneypr.viewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001:\u0001 B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J&\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ*\u0010\u001b\u001a\u0004\u0018\u0001H\u001c\"\u0010\b\u0000\u0010\u001c\u0018\u0001*\b\u0012\u0004\u0012\u0002H\u001c0\u001d2\u0006\u0010\u001e\u001a\u00020\u0015H\u0082\b\u00a2\u0006\u0002\u0010\u001fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006!"}, d2 = {"Lru/ohayo/moneypr/viewModel/AccountViewModel;", "Landroidx/lifecycle/ViewModel;", "accountRepository", "Lru/ohayo/moneypr/data/repository/AccountRepository;", "(Lru/ohayo/moneypr/data/repository/AccountRepository;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lru/ohayo/moneypr/viewModel/AccountViewModel$AccountState;", "accounts", "Lkotlinx/coroutines/flow/Flow;", "", "Lru/ohayo/moneypr/domain/allEntity/Account;", "getAccounts", "()Lkotlinx/coroutines/flow/Flow;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "addAccount", "", "name", "", "type", "balance", "", "currencyId", "", "enumValueOrNull", "T", "", "value", "(Ljava/lang/String;)Ljava/lang/Enum;", "AccountState", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class AccountViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final ru.ohayo.moneypr.data.repository.AccountRepository accountRepository = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.allEntity.Account>> accounts = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<ru.ohayo.moneypr.viewModel.AccountViewModel.AccountState> _state = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<ru.ohayo.moneypr.viewModel.AccountViewModel.AccountState> state = null;
    
    @javax.inject.Inject
    public AccountViewModel(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.repository.AccountRepository accountRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.allEntity.Account>> getAccounts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<ru.ohayo.moneypr.viewModel.AccountViewModel.AccountState> getState() {
        return null;
    }
    
    public final void addAccount(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String type, double balance, long currencyId) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lru/ohayo/moneypr/viewModel/AccountViewModel$AccountState;", "", "()V", "Error", "Idle", "Loading", "Success", "Lru/ohayo/moneypr/viewModel/AccountViewModel$AccountState$Error;", "Lru/ohayo/moneypr/viewModel/AccountViewModel$AccountState$Idle;", "Lru/ohayo/moneypr/viewModel/AccountViewModel$AccountState$Loading;", "Lru/ohayo/moneypr/viewModel/AccountViewModel$AccountState$Success;", "app_debug"})
    public static abstract class AccountState {
        
        private AccountState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lru/ohayo/moneypr/viewModel/AccountViewModel$AccountState$Error;", "Lru/ohayo/moneypr/viewModel/AccountViewModel$AccountState;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
        public static final class Error extends ru.ohayo.moneypr.viewModel.AccountViewModel.AccountState {
            @org.jetbrains.annotations.NotNull
            private final java.lang.String message = null;
            
            public Error(@org.jetbrains.annotations.NotNull
            java.lang.String message) {
            }
            
            @org.jetbrains.annotations.NotNull
            public final java.lang.String getMessage() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull
            public final ru.ohayo.moneypr.viewModel.AccountViewModel.AccountState.Error copy(@org.jetbrains.annotations.NotNull
            java.lang.String message) {
                return null;
            }
            
            @java.lang.Override
            public boolean equals(@org.jetbrains.annotations.Nullable
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override
            @org.jetbrains.annotations.NotNull
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lru/ohayo/moneypr/viewModel/AccountViewModel$AccountState$Idle;", "Lru/ohayo/moneypr/viewModel/AccountViewModel$AccountState;", "()V", "app_debug"})
        public static final class Idle extends ru.ohayo.moneypr.viewModel.AccountViewModel.AccountState {
            @org.jetbrains.annotations.NotNull
            public static final ru.ohayo.moneypr.viewModel.AccountViewModel.AccountState.Idle INSTANCE = null;
            
            private Idle() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lru/ohayo/moneypr/viewModel/AccountViewModel$AccountState$Loading;", "Lru/ohayo/moneypr/viewModel/AccountViewModel$AccountState;", "()V", "app_debug"})
        public static final class Loading extends ru.ohayo.moneypr.viewModel.AccountViewModel.AccountState {
            @org.jetbrains.annotations.NotNull
            public static final ru.ohayo.moneypr.viewModel.AccountViewModel.AccountState.Loading INSTANCE = null;
            
            private Loading() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lru/ohayo/moneypr/viewModel/AccountViewModel$AccountState$Success;", "Lru/ohayo/moneypr/viewModel/AccountViewModel$AccountState;", "()V", "app_debug"})
        public static final class Success extends ru.ohayo.moneypr.viewModel.AccountViewModel.AccountState {
            @org.jetbrains.annotations.NotNull
            public static final ru.ohayo.moneypr.viewModel.AccountViewModel.AccountState.Success INSTANCE = null;
            
            private Success() {
            }
        }
    }
}