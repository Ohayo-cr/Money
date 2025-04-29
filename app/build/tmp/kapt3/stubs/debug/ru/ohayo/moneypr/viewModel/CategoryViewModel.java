package ru.ohayo.moneypr.viewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u000e\u001a\u00020\u000fJ\u0014\u0010\u0010\u001a\u00020\u00112\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007J\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\bR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lru/ohayo/moneypr/viewModel/CategoryViewModel;", "Landroidx/lifecycle/ViewModel;", "categoryRepository", "Lru/ohayo/moneypr/data/repository/CategoryRepository;", "(Lru/ohayo/moneypr/data/repository/CategoryRepository;)V", "_categories", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lru/ohayo/moneypr/domain/category/Category;", "categories", "Lkotlinx/coroutines/flow/StateFlow;", "getCategories", "()Lkotlinx/coroutines/flow/StateFlow;", "filterCategoriesByType", "type", "Lru/ohayo/moneypr/domain/category/CategoryType;", "insertAll", "", "insertCategory", "category", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class CategoryViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final ru.ohayo.moneypr.data.repository.CategoryRepository categoryRepository = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<ru.ohayo.moneypr.domain.category.Category>> _categories = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<ru.ohayo.moneypr.domain.category.Category>> categories = null;
    
    @javax.inject.Inject
    public CategoryViewModel(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.repository.CategoryRepository categoryRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<ru.ohayo.moneypr.domain.category.Category>> getCategories() {
        return null;
    }
    
    public final void insertCategory(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.category.Category category) {
    }
    
    public final void insertAll(@org.jetbrains.annotations.NotNull
    java.util.List<ru.ohayo.moneypr.domain.category.Category> categories) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<ru.ohayo.moneypr.domain.category.Category> filterCategoriesByType(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.category.CategoryType type) {
        return null;
    }
}