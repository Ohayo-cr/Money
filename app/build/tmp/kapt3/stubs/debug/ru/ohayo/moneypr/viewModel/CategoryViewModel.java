package ru.ohayo.moneypr.viewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001:\u00010B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\"\u001a\u00020\rJ\u000e\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&J\u001e\u0010\'\u001a\u00020$2\u0006\u0010(\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\rJ\u0006\u0010*\u001a\u00020$J\u000e\u0010+\u001a\u00020$2\u0006\u0010,\u001a\u00020\u000bJ\u000e\u0010-\u001a\u00020$2\u0006\u0010\"\u001a\u00020\rJ\u000e\u0010.\u001a\u00020$2\u0006\u0010/\u001a\u00020\u000fR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\r0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0013R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00061"}, d2 = {"Lru/ohayo/moneypr/viewModel/CategoryViewModel;", "Landroidx/lifecycle/ViewModel;", "categoryRepository", "Lru/ohayo/moneypr/data/repository/CategoryRepository;", "(Lru/ohayo/moneypr/data/repository/CategoryRepository;)V", "_categories", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lru/ohayo/moneypr/domain/allEntity/Category;", "_category", "_scrollPosition", "", "_selectedCategoryType", "Lru/ohayo/moneypr/domain/allEntity/CategoryType;", "_shouldScrollToTop", "", "categories", "Lkotlinx/coroutines/flow/StateFlow;", "getCategories", "()Lkotlinx/coroutines/flow/StateFlow;", "category", "getCategory", "changedTypes", "", "filteredCategories", "getFilteredCategories", "scrollPosition", "getScrollPosition", "selectedCategoryType", "getSelectedCategoryType", "shouldScrollToTop", "getShouldScrollToTop", "tempUpdatedList", "filterCategoriesByType", "type", "loadCategory", "", "id", "", "moveCategory", "fromIndex", "toIndex", "saveOrderChanges", "saveScrollPosition", "index", "setSelectedCategoryType", "setShouldScrollToTop", "value", "CategoryTypeHolder", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class CategoryViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final ru.ohayo.moneypr.data.repository.CategoryRepository categoryRepository = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<ru.ohayo.moneypr.domain.allEntity.Category>> _categories = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<ru.ohayo.moneypr.domain.allEntity.Category>> categories = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<ru.ohayo.moneypr.domain.allEntity.Category> _category = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<ru.ohayo.moneypr.domain.allEntity.Category> category = null;
    @org.jetbrains.annotations.NotNull
    private java.util.List<ru.ohayo.moneypr.domain.allEntity.Category> tempUpdatedList;
    @org.jetbrains.annotations.NotNull
    private final java.util.Set<ru.ohayo.moneypr.domain.allEntity.CategoryType> changedTypes = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<ru.ohayo.moneypr.domain.allEntity.CategoryType> _selectedCategoryType = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<ru.ohayo.moneypr.domain.allEntity.CategoryType> selectedCategoryType = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<ru.ohayo.moneypr.domain.allEntity.Category>> filteredCategories = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _scrollPosition = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> scrollPosition = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _shouldScrollToTop = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> shouldScrollToTop = null;
    
    @javax.inject.Inject
    public CategoryViewModel(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.repository.CategoryRepository categoryRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<ru.ohayo.moneypr.domain.allEntity.Category>> getCategories() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<ru.ohayo.moneypr.domain.allEntity.Category> getCategory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<ru.ohayo.moneypr.domain.allEntity.CategoryType> getSelectedCategoryType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<ru.ohayo.moneypr.domain.allEntity.Category>> getFilteredCategories() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getScrollPosition() {
        return null;
    }
    
    public final void saveScrollPosition(int index) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getShouldScrollToTop() {
        return null;
    }
    
    public final void setShouldScrollToTop(boolean value) {
    }
    
    public final void moveCategory(int fromIndex, int toIndex, @org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.allEntity.CategoryType type) {
    }
    
    public final void setSelectedCategoryType(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.allEntity.CategoryType type) {
    }
    
    public final void saveOrderChanges() {
    }
    
    public final void loadCategory(long id) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<ru.ohayo.moneypr.domain.allEntity.Category> filterCategoriesByType(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.allEntity.CategoryType type) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lru/ohayo/moneypr/viewModel/CategoryViewModel$CategoryTypeHolder;", "", "()V", "currentType", "Lru/ohayo/moneypr/domain/allEntity/CategoryType;", "getCurrentType", "()Lru/ohayo/moneypr/domain/allEntity/CategoryType;", "setCurrentType", "(Lru/ohayo/moneypr/domain/allEntity/CategoryType;)V", "app_debug"})
    public static final class CategoryTypeHolder {
        @org.jetbrains.annotations.NotNull
        private static ru.ohayo.moneypr.domain.allEntity.CategoryType currentType = ru.ohayo.moneypr.domain.allEntity.CategoryType.EXPENSE;
        @org.jetbrains.annotations.NotNull
        public static final ru.ohayo.moneypr.viewModel.CategoryViewModel.CategoryTypeHolder INSTANCE = null;
        
        private CategoryTypeHolder() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final ru.ohayo.moneypr.domain.allEntity.CategoryType getCurrentType() {
            return null;
        }
        
        public final void setCurrentType(@org.jetbrains.annotations.NotNull
        ru.ohayo.moneypr.domain.allEntity.CategoryType p0) {
        }
    }
}