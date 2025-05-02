package ru.ohayo.moneypr.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u000bH\u0016J\u001c\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0019\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000fH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u001f\u0010\u0016\u001a\u00020\u00062\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\fH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J\u0019\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0011\u0010\u001a\u001a\u00020\u001bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001cJ\u001f\u0010\u001d\u001a\u00020\u00062\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\b0\fH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J\u0019\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006 "}, d2 = {"Lru/ohayo/moneypr/data/repository/CategoryRepositoryImpl;", "Lru/ohayo/moneypr/data/repository/CategoryRepository;", "categoryDao", "Lru/ohayo/moneypr/data/data_source/category/CategoryDao;", "(Lru/ohayo/moneypr/data/data_source/category/CategoryDao;)V", "deleteCategory", "", "category", "Lru/ohayo/moneypr/domain/category/Category;", "(Lru/ohayo/moneypr/domain/category/Category;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllCategories", "Lkotlinx/coroutines/flow/Flow;", "", "getCategoriesByType", "type", "Lru/ohayo/moneypr/domain/category/CategoryType;", "getCategoryById", "id", "", "getMaxOrder", "", "(Lru/ohayo/moneypr/domain/category/CategoryType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "categories", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertCategory", "isEmpty", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateCategories", "updatedCategories", "updateCategory", "app_debug"})
public final class CategoryRepositoryImpl implements ru.ohayo.moneypr.data.repository.CategoryRepository {
    @org.jetbrains.annotations.NotNull
    private final ru.ohayo.moneypr.data.data_source.category.CategoryDao categoryDao = null;
    
    @javax.inject.Inject
    public CategoryRepositoryImpl(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.data_source.category.CategoryDao categoryDao) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.category.Category>> getAllCategories() {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object insertAll(@org.jetbrains.annotations.NotNull
    java.util.List<ru.ohayo.moneypr.domain.category.Category> categories, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object insertCategory(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.category.Category category, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object isEmpty(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<ru.ohayo.moneypr.domain.category.Category> getCategoryById(long id) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object updateCategories(@org.jetbrains.annotations.NotNull
    java.util.List<ru.ohayo.moneypr.domain.category.Category> updatedCategories, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object deleteCategory(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.category.Category category, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<java.util.List<ru.ohayo.moneypr.domain.category.Category>> getCategoriesByType(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.category.CategoryType type) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getMaxOrder(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.category.CategoryType type, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object updateCategory(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.category.Category category, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}