package ru.ohayo.moneypr.data.data_source.navigation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u00c2\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0019\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u0005H\u0016R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000e"}, d2 = {"Lru/ohayo/moneypr/data/data_source/navigation/NoRippleInteractionSource;", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "()V", "interactions", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/compose/foundation/interaction/Interaction;", "getInteractions", "()Lkotlinx/coroutines/flow/Flow;", "emit", "", "interaction", "(Landroidx/compose/foundation/interaction/Interaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryEmit", "", "app_debug"})
final class NoRippleInteractionSource implements androidx.compose.foundation.interaction.MutableInteractionSource {
    @org.jetbrains.annotations.NotNull
    private static final kotlinx.coroutines.flow.Flow<androidx.compose.foundation.interaction.Interaction> interactions = null;
    @org.jetbrains.annotations.NotNull
    public static final ru.ohayo.moneypr.data.data_source.navigation.NoRippleInteractionSource INSTANCE = null;
    
    private NoRippleInteractionSource() {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<androidx.compose.foundation.interaction.Interaction> getInteractions() {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object emit(@org.jetbrains.annotations.NotNull
    androidx.compose.foundation.interaction.Interaction interaction, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override
    public boolean tryEmit(@org.jetbrains.annotations.NotNull
    androidx.compose.foundation.interaction.Interaction interaction) {
        return false;
    }
}