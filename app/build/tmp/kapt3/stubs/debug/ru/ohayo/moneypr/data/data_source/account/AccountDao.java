package ru.ohayo.moneypr.data.data_source.account;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bH\'J\u0019\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\rH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J!\u0010\u0011\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0015"}, d2 = {"Lru/ohayo/moneypr/data/data_source/account/AccountDao;", "", "deleteAccountById", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAccountNameById", "", "accountId", "getAllAccounts", "Lkotlinx/coroutines/flow/Flow;", "", "Lru/ohayo/moneypr/domain/Account;", "insertAccount", "account", "(Lru/ohayo/moneypr/domain/Account;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateBalance", "amount", "", "(JDLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao
public abstract interface AccountDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertAccount(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.Account account, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM account")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.Account>> getAllAccounts();
    
    @androidx.room.Query(value = "DELETE FROM account WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAccountById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE account SET balance = ROUND(balance + :amount, 2) WHERE id = :accountId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateBalance(long accountId, double amount, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT name FROM account WHERE id = :accountId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAccountNameById(long accountId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> $completion);
}