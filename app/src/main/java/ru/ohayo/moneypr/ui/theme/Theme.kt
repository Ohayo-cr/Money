package ru.ohayo.moneypr.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Цветовая палитра приложения
val BrightTurquoise = Color(0xFF00C8B8) // Ярко-бирюзовый
val DeepPurple = Color(0xFF8A2BE2)     // Темно-фиолетовый
val SoftPink = Color(0xFFE073B9)       // Нежно-розовый
val LightTurquoise = Color(0xFF64D8C9) // Светло-бирюзовый
val WarmOrange = Color(0xFFFF933C)     // Теплый оранжевый
val WhiteBackground = Color.White      // Белый фон
val DarkBackground = Color(0xFF1C1B1F) // Темный фон (почти черный)
val GraySurface = Color(0xFF2B2B2B)    // Серая поверхность (темная тема)
val BlackText = Color(0xFF000000)           // Черный текст
val WhiteText = Color.White            // Белый текст
val ErrorRed = Color.Red               // Красный цвет для ошибок
val SuccessGreen = Color.Green         // Зеленый цвет для успеха
val MintColor = Color(0xFF3EB489)

@Composable
fun MoneyPrTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        // Динамические цвета доступны только на Android 12+
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> darkColorScheme(
            primary = DeepPurple,
            onPrimary = BlackText,
            secondary = SoftPink,
            onSecondary = WhiteText,
            background = DarkBackground,
            onBackground = WhiteText,
            surface = GraySurface,
            onSurface = WhiteText,
            tertiary = DeepPurple,
            onTertiary = BlackText,
            error = ErrorRed,
            onError = WhiteText
        )
        else -> lightColorScheme(
            primary = BrightTurquoise,
            onPrimary = WhiteText,
            secondary = LightTurquoise,
            onSecondary = BlackText,
            background = WhiteBackground,
            onBackground = BlackText,
            surface = BrightTurquoise,
            onSurface = BlackText,
            tertiary = MintColor,
            onTertiary = WhiteText,
            error = ErrorRed,
            onError = WhiteText
        )
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            configureWindowColors(view, colorScheme, darkTheme)
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

/**
 * Настройка цветов статус-бара и навигационной панели
 */
private fun configureWindowColors(
    view: android.view.View,
    colorScheme: androidx.compose.material3.ColorScheme,
    darkTheme: Boolean
) {
    val window = (view.context as Activity).window
    window.statusBarColor = colorScheme.background.toArgb()
    WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme

    window.navigationBarColor = colorScheme.surface.toArgb()
    WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = !darkTheme
}