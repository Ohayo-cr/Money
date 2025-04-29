package ru.ohayo.moneypr.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0012\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bJ\u001b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0019\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0019\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0013"}, d2 = {"Lru/ohayo/moneypr/data/repository/TransactionRepository;", "", "transactionDao", "Lru/ohayo/moneypr/data/data_source/transaction/TransactionDao;", "(Lru/ohayo/moneypr/data/data_source/transaction/TransactionDao;)V", "deleteTransaction", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllTransactions", "Lkotlinx/coroutines/flow/Flow;", "", "Lru/ohayo/moneypr/domain/TransactionEntity;", "getTransactionById", "insertTransaction", "transaction", "(Lru/ohayo/moneypr/domain/TransactionEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTransaction", "app_debug"})
public final class TransactionRepository {
    @org.jetbrains.annotations.NotNull
    private final ru.ohayo.moneypr.data.data_source.transaction.TransactionDao transactionDao = null;
    
    @javax.inject.Inject
    public TransactionRepository(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.data_source.transaction.TransactionDao transactionDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.TransactionEntity>> getAllTransactions() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getTransactionById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super ru.ohayo.moneypr.domain.TransactionEntity> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object insertTransaction(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.TransactionEntity transaction, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateTransaction(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.TransactionEntity transaction, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteTransaction(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}