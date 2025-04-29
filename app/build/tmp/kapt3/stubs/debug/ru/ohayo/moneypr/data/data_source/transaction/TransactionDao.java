package ru.ohayo.moneypr.data.data_source.transaction;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH\'J\u001b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0019\u0010\u000f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lru/ohayo/moneypr/data/data_source/transaction/TransactionDao;", "", "deleteTransaction", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllTransactions", "Lkotlinx/coroutines/flow/Flow;", "", "Lru/ohayo/moneypr/domain/TransactionEntity;", "getTransactionById", "insertTransaction", "transaction", "(Lru/ohayo/moneypr/domain/TransactionEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTransaction", "app_debug"})
@androidx.room.Dao
public abstract interface TransactionDao {
    
    @androidx.room.Query(value = "SELECT * FROM transactions")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.TransactionEntity>> getAllTransactions();
    
    @androidx.room.Query(value = "SELECT * FROM transactions WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTransactionById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super ru.ohayo.moneypr.domain.TransactionEntity> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertTransaction(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.TransactionEntity transaction, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateTransaction(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.TransactionEntity transaction, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM transactions WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteTransaction(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}