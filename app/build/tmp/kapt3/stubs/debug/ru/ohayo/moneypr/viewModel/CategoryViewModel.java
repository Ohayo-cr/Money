package ru.ohayo.moneypr.viewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001eB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0015\u001a\u00020\nJ\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0015\u001a\u00020\nJ\u001e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\nJ\u0006\u0010\u001c\u001a\u00020\u0018J\u000e\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\nR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\n0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lru/ohayo/moneypr/viewModel/CategoryViewModel;", "Landroidx/lifecycle/ViewModel;", "categoryRepository", "Lru/ohayo/moneypr/data/repository/CategoryRepository;", "(Lru/ohayo/moneypr/data/repository/CategoryRepository;)V", "_categories", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lru/ohayo/moneypr/domain/category/Category;", "_selectedCategoryType", "Lru/ohayo/moneypr/domain/category/CategoryType;", "categories", "Lkotlinx/coroutines/flow/StateFlow;", "getCategories", "()Lkotlinx/coroutines/flow/StateFlow;", "changedTypes", "", "selectedCategoryType", "getSelectedCategoryType", "tempUpdatedList", "filterCategoriesByType", "type", "getCategoriesByType", "moveCategory", "", "fromIndex", "", "toIndex", "saveOrderChanges", "setSelectedCategoryType", "CategoryTypeHolder", "app_debug"})
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
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<ru.ohayo.moneypr.domain.category.CategoryType> _selectedCategoryType = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<ru.ohayo.moneypr.domain.category.CategoryType> selectedCategoryType = null;
    
    @javax.inject.Inject
    public CategoryViewModel(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.repository.CategoryRepository categoryRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<ru.ohayo.moneypr.domain.category.Category>> getCategories() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<ru.ohayo.moneypr.domain.category.CategoryType> getSelectedCategoryType() {
        return null;
    }
    
    public final void moveCategory(int fromIndex, int toIndex, @org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.category.CategoryType type) {
    }
    
    public final void setSelectedCategoryType(@org.jetbrains.annotations.NotNull
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lru/ohayo/moneypr/viewModel/CategoryViewModel$CategoryTypeHolder;", "", "()V", "currentType", "Lru/ohayo/moneypr/domain/category/CategoryType;", "getCurrentType", "()Lru/ohayo/moneypr/domain/category/CategoryType;", "setCurrentType", "(Lru/ohayo/moneypr/domain/category/CategoryType;)V", "app_debug"})
    public static final class CategoryTypeHolder {
        @org.jetbrains.annotations.NotNull
        private static ru.ohayo.moneypr.domain.category.CategoryType currentType = ru.ohayo.moneypr.domain.category.CategoryType.EXPENSE;
        @org.jetbrains.annotations.NotNull
        public static final ru.ohayo.moneypr.viewModel.CategoryViewModel.CategoryTypeHolder INSTANCE = null;
        
        private CategoryTypeHolder() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final ru.ohayo.moneypr.domain.category.CategoryType getCurrentType() {
            return null;
        }
        
        public final void setCurrentType(@org.jetbrains.annotations.NotNull
        ru.ohayo.moneypr.domain.category.CategoryType p0) {
        }
    }
}