package ru.ohayo.moneypr.viewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\bJ\u0006\u0010 \u001a\u00020\u001eJ\u0006\u0010!\u001a\u00020\u001eJ\u0010\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020\bH\u0002J\u0018\u0010$\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\b2\u0006\u0010%\u001a\u00020\bH\u0002J\u0006\u0010&\u001a\u00020\u001eJ\u000e\u0010\'\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020\bR+\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u00118F\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00118F\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\u0013R+\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0018\u0010\u000f\u001a\u0004\b\u0016\u0010\u000b\"\u0004\b\u0017\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u001c\u0010\u000f\u001a\u0004\b\u001a\u0010\u000b\"\u0004\b\u001b\u0010\r\u00a8\u0006)"}, d2 = {"Lru/ohayo/moneypr/viewModel/KeyboardViewModel;", "Landroidx/lifecycle/ViewModel;", "evaluateExpressionUseCase", "Lru/ohayo/moneypr/domain/useCase/EvaluateExpressionUseCase;", "repository", "Lru/ohayo/moneypr/data/repository/ExpressionRepository;", "(Lru/ohayo/moneypr/domain/useCase/EvaluateExpressionUseCase;Lru/ohayo/moneypr/data/repository/ExpressionRepository;)V", "<set-?>", "", "currentInput", "getCurrentInput", "()Ljava/lang/String;", "setCurrentInput", "(Ljava/lang/String;)V", "currentInput$delegate", "Landroidx/compose/runtime/MutableState;", "isDeletePressed", "", "isExpressionReadyForEvaluation", "()Z", "isResultPositive", "note", "getNote", "setNote", "note$delegate", "result", "getResult", "setResult", "result$delegate", "appendToInput", "", "value", "calculateResult", "deleteLast", "getLastNumber", "input", "isValidInput", "newChar", "reset", "updateNote", "newNote", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class KeyboardViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final ru.ohayo.moneypr.domain.useCase.EvaluateExpressionUseCase evaluateExpressionUseCase = null;
    @org.jetbrains.annotations.NotNull
    private final ru.ohayo.moneypr.data.repository.ExpressionRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableState currentInput$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableState result$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableState note$delegate = null;
    private boolean isDeletePressed = false;
    
    @javax.inject.Inject
    public KeyboardViewModel(@org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.domain.useCase.EvaluateExpressionUseCase evaluateExpressionUseCase, @org.jetbrains.annotations.NotNull
    ru.ohayo.moneypr.data.repository.ExpressionRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getCurrentInput() {
        return null;
    }
    
    private final void setCurrentInput(java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getResult() {
        return null;
    }
    
    private final void setResult(java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getNote() {
        return null;
    }
    
    private final void setNote(java.lang.String p0) {
    }
    
    public final boolean isExpressionReadyForEvaluation() {
        return false;
    }
    
    public final void updateNote(@org.jetbrains.annotations.NotNull
    java.lang.String newNote) {
    }
    
    public final boolean isResultPositive() {
        return false;
    }
    
    public final void appendToInput(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    public final void calculateResult() {
    }
    
    public final void deleteLast() {
    }
    
    private final boolean isValidInput(java.lang.String currentInput, java.lang.String newChar) {
        return false;
    }
    
    public final void reset() {
    }
    
    private final java.lang.String getLastNumber(java.lang.String input) {
        return null;
    }
}