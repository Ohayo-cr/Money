package ru.ohayo.moneypr.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006H\u0016J\u0018\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001f\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0019\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u0011\u0010\u0013\u001a\u00020\u0014H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0016"}, d2 = {"Lru/ohayo/moneypr/data/repository/CategoryRepositoryImpl;", "Lru/ohayo/moneypr/data/repository/CategoryRepository;", "categoryDao", "Lru/ohayo/moneypr/data/data_source/category/CategoryDao;", "(Lru/ohayo/moneypr/data/data_source/category/CategoryDao;)V", "getAllCategories", "Lkotlinx/coroutines/flow/Flow;", "", "Lru/ohayo/moneypr/domain/category/Category;", "getCategoryById", "id", "", "insertAll", "", "categories", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertCategory", "category", "(Lru/ohayo/moneypr/domain/category/Category;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isEmpty", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
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
}