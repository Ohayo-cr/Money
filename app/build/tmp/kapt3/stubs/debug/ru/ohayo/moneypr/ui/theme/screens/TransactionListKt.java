package ru.ohayo.moneypr.ui.theme.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0003\u001a2\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\tH\u0003\u001a&\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007\u001a\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014\u001a\u001e\u0010\u0015\u001a\u00020\u00122\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0016\u001a\u00020\u0014H\u0007\u00a8\u0006\u0017"}, d2 = {"EmptyTransactionsPlaceholder", "", "TransactionItem", "transaction", "Lru/ohayo/moneypr/domain/TransactionEntity;", "categories", "", "Lru/ohayo/moneypr/domain/category/Category;", "onTransactionClick", "Lkotlin/Function1;", "TransactionsList", "transactionlistViewModel", "Lru/ohayo/moneypr/viewModel/TransactionListViewModel;", "categoryViewModel", "Lru/ohayo/moneypr/viewModel/CategoryViewModel;", "transactionViewModel", "Lru/ohayo/moneypr/viewModel/TransactionViewModel;", "formatTimestamp", "", "timestamp", "", "getCategoryName", "categoryId", "app_debug"})
public final class TransactionListKt {
    
    @androidx.compose.runtime.Composable
    public static final void TransactionsList(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.viewModel.TransactionListViewModel transactionlistViewModel, @org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.viewModel.CategoryViewModel categoryViewModel, @org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.viewModel.TransactionViewModel transactionViewModel) {
    }
    
    @androidx.compose.runtime.Composable
    private static final void EmptyTransactionsPlaceholder() {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable
    private static final void TransactionItem(ru.ohayo.moneypr.domain.TransactionEntity transaction, java.util.List<ru.ohayo.moneypr.domain.category.Category> categories, kotlin.jvm.functions.Function1<? super ru.ohayo.moneypr.domain.TransactionEntity, kotlin.Unit> onTransactionClick) {
    }
    
    @androidx.compose.runtime.Composable
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String getCategoryName(@org.jetbrains.annotations.NotNull
    java.util.List<ru.ohayo.moneypr.domain.category.Category> categories, long categoryId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String formatTimestamp(long timestamp) {
        return null;
    }
}