package ru.ohayo.moneypr.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00040\u0003H&J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H&\u00a8\u0006\n"}, d2 = {"Lru/ohayo/moneypr/data/repository/ExpressionRepository;", "", "getHistory", "", "Lkotlin/Pair;", "", "saveExpression", "", "expression", "result", "app_debug"})
public abstract interface ExpressionRepository {
    
    public abstract void saveExpression(@org.jetbrains.annotations.NotNull
    java.lang.String expression, @org.jetbrains.annotations.NotNull
    java.lang.String result);
    
    @org.jetbrains.annotations.NotNull
    public abstract java.util.List<kotlin.Pair<java.lang.String, java.lang.String>> getHistory();
}