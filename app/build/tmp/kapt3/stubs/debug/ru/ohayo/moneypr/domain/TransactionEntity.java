package ru.ohayo.moneypr.domain;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\rJ\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0007H\u00c6\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0015J\u0010\u0010 \u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0015J\u0010\u0010!\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0015J\u0010\u0010\"\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0015Jh\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001\u00a2\u0006\u0002\u0010$J\u0013\u0010%\u001a\u00020&2\b\u0010\'\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010(\u001a\u00020)H\u00d6\u0001J\t\u0010*\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0017\u0010\u0015R\u001a\u0010\f\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0018\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u0015\u0010\n\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u001a\u0010\u0015\u00a8\u0006+"}, d2 = {"Lru/ohayo/moneypr/domain/TransactionEntity;", "", "amount", "", "content", "", "timestamp", "", "category", "fromAccount", "toAccount", "currency", "id", "(DLjava/lang/String;JJLjava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;)V", "getAmount", "()D", "getCategory", "()J", "getContent", "()Ljava/lang/String;", "getCurrency", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getFromAccount", "getId", "getTimestamp", "getToAccount", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(DLjava/lang/String;JJLjava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;)Lru/ohayo/moneypr/domain/TransactionEntity;", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
@androidx.room.Entity(tableName = "transactions", indices = {@androidx.room.Index(value = {"timestamp"}), @androidx.room.Index(value = {"category"}), @androidx.room.Index(value = {"currency"}), @androidx.room.Index(value = {"fromAccount"}), @androidx.room.Index(value = {"toAccount"})}, foreignKeys = {@androidx.room.ForeignKey(entity = ru.ohayo.moneypr.domain.category.Category.class, parentColumns = {"id"}, childColumns = {"category"}, onDelete = 5), @androidx.room.ForeignKey(entity = ru.ohayo.moneypr.domain.currency.Currency.class, parentColumns = {"id"}, childColumns = {"currency"}, onDelete = 3), @androidx.room.ForeignKey(entity = ru.ohayo.moneypr.domain.Account.class, parentColumns = {"id"}, childColumns = {"fromAccount"}, onDelete = 3), @androidx.room.ForeignKey(entity = ru.ohayo.moneypr.domain.Account.class, parentColumns = {"id"}, childColumns = {"toAccount"}, onDelete = 3)})
public final class TransactionEntity {
    private final double amount = 0.0;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String content = null;
    private final long timestamp = 0L;
    private final long category = 0L;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long fromAccount = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long toAccount = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long currency = null;
    @androidx.room.PrimaryKey(autoGenerate = true)
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long id = null;
    
    public TransactionEntity(double amount, @org.jetbrains.annotations.Nullable
    java.lang.String content, long timestamp, long category, @org.jetbrains.annotations.Nullable
    java.lang.Long fromAccount, @org.jetbrains.annotations.Nullable
    java.lang.Long toAccount, @org.jetbrains.annotations.Nullable
    java.lang.Long currency, @org.jetbrains.annotations.Nullable
    java.lang.Long id) {
        super();
    }
    
    public final double getAmount() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getContent() {
        return null;
    }
    
    public final long getTimestamp() {
        return 0L;
    }
    
    public final long getCategory() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getFromAccount() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getToAccount() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getCurrency() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getId() {
        return null;
    }
    
    public final double component1() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component2() {
        return null;
    }
    
    public final long component3() {
        return 0L;
    }
    
    public final long component4() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final ru.ohayo.moneypr.domain.TransactionEntity copy(double amount, @org.jetbrains.annotations.Nullable
    java.lang.String content, long timestamp, long category, @org.jetbrains.annotations.Nullable
    java.lang.Long fromAccount, @org.jetbrains.annotations.Nullable
    java.lang.Long toAccount, @org.jetbrains.annotations.Nullable
    java.lang.Long currency, @org.jetbrains.annotations.Nullable
    java.lang.Long id) {
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