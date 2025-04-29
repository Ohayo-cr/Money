package ru.ohayo.moneypr.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u001b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eH\u0016J\u0019\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0010H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J!\u0010\u0014\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0018"}, d2 = {"Lru/ohayo/moneypr/data/repository/AccountRepositoryImpl;", "Lru/ohayo/moneypr/domain/AccountRepository;", "accountDao", "Lru/ohayo/moneypr/data/data_source/account/AccountDao;", "(Lru/ohayo/moneypr/data/data_source/account/AccountDao;)V", "deleteAccountById", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAccountName", "", "accountId", "getAllAccounts", "Lkotlinx/coroutines/flow/Flow;", "", "Lru/ohayo/moneypr/domain/Account;", "insertAccount", "account", "(Lru/ohayo/moneypr/domain/Account;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateBalance", "amount", "", "(JDLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class AccountRepositoryImpl implements ru.ohayo.moneypr.domain.AccountRepository {
    @org.jetbrains.annotations.NotNull
    private final ru.ohayo.moneypr.data.data_source.account.AccountDao accountDao = null;
    
    @javax.inject.Inject
    public AccountRepositoryImpl(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.data_source.account.AccountDao accountDao) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object insertAccount(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.Account account, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.Account>> getAllAccounts() {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object deleteAccountById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object updateBalance(long accountId, double amount, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getAccountName(long accountId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
}