package ru.ohayo.moneypr.viewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0012\u001a\u00020\u000fJ\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0012\u001a\u00020\u000fJ\u001e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u000fJ\u0006\u0010\u0019\u001a\u00020\u0015R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lru/ohayo/moneypr/viewModel/CategoryViewModel;", "Landroidx/lifecycle/ViewModel;", "categoryRepository", "Lru/ohayo/moneypr/data/repository/CategoryRepository;", "(Lru/ohayo/moneypr/data/repository/CategoryRepository;)V", "_categories", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lru/ohayo/moneypr/domain/category/Category;", "categories", "Lkotlinx/coroutines/flow/StateFlow;", "getCategories", "()Lkotlinx/coroutines/flow/StateFlow;", "changedTypes", "", "Lru/ohayo/moneypr/domain/category/CategoryType;", "tempUpdatedList", "filterCategoriesByType", "type", "getCategoriesByType", "moveCategory", "", "fromIndex", "", "toIndex", "saveOrderChanges", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class CategoryViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final ru.ohayo.moneypr.data.repository.CategoryRepository categoryRepository = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<ru.ohayo.moneypr.domain.category.Category>> _categories = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<ru.ohayo.moneypr.domain.category.Category>> categories = null;
    @org.jetbrains.annotations.NotNull
    private java.util.List<ru.ohayo.moneypr.domain.category.Category> tempUpdatedList;
    @org.jetbrains.annotations.NotNull
    private final java.util.Set<ru.ohayo.moneypr.domain.category.CategoryType> changedTypes = null;
    
    @javax.inject.Inject
    public CategoryViewModel(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.repository.CategoryRepository categoryRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<ru.ohayo.moneypr.domain.category.Category>> getCategories() {
        return null;
    }
    
    public final void moveCategory(int fromIndex, int toIndex, @org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.category.CategoryType type) {
    }
    
    public final void saveOrderChanges() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<ru.ohayo.moneypr.domain.category.Category> getCategoriesByType(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.category.CategoryType type) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<ru.ohayo.moneypr.domain.category.Category> filterCategoriesByType(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.category.CategoryType type) {
        return null;
    }
}