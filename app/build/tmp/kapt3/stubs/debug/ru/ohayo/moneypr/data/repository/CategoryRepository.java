package ru.ohayo.moneypr.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH&J\u001c\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\b2\u0006\u0010\u000b\u001a\u00020\fH&J\u0018\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\b2\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0019\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\fH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u001f\u0010\u0013\u001a\u00020\u00032\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u0019\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0011\u0010\u0017\u001a\u00020\u0018H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J\u001f\u0010\u001a\u001a\u00020\u00032\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u0019\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001d"}, d2 = {"Lru/ohayo/moneypr/data/repository/CategoryRepository;", "", "deleteCategory", "", "category", "Lru/ohayo/moneypr/domain/category/Category;", "(Lru/ohayo/moneypr/domain/category/Category;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllCategories", "Lkotlinx/coroutines/flow/Flow;", "", "getCategoriesByType", "type", "Lru/ohayo/moneypr/domain/category/CategoryType;", "getCategoryById", "id", "", "getMaxOrder", "", "(Lru/ohayo/moneypr/domain/category/CategoryType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "categories", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertCategory", "isEmpty", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateCategories", "updatedCategories", "updateCategory", "app_debug"})
public abstract interface CategoryRepository {
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.category.Category>> getAllCategories();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull
    java.util.List<ru.ohayo.moneypr.domain.category.Category> categories, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertCategory(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.category.Category category, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object isEmpty(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateCategories(@org.jetbrains.annotations.NotNull
    java.util.List<ru.ohayo.moneypr.domain.category.Category> updatedCategories, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<ru.ohayo.moneypr.domain.category.Category> getCategoryById(long id);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteCategory(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.category.Category category, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.category.Category>> getCategoriesByType(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.category.CategoryType type);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getMaxOrder(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.category.CategoryType type, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateCategory(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.category.Category category, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}