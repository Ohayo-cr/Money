package ru.ohayo.moneypr.ui.theme

import android.app.Activity
import android.os.Build
import android.view.Window
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



// Основные цвета
val LightTurquoise = Color(0xFF64D8C9)
val ErrorRed = Color.Red

// Цвета текста
val TextPrimaryWhite = Color.Black
val TextPrimaryBlack = Color.White
val TextSecondary = Color(0xFF383A3F)
val TextDisabled = Color(0xFF898E98)
val TextOnDark = Color.White

// Темная тема
val DarkPrimary = Color(0xFF2F3030)
val DarkSecondary = Color(0xFF4A4B4B)
val DarkBackground = Color(0xFF737274)
val DarkSurface = DarkPrimary
val DarkAccent = Color(0xFF008B8B)

// Светлая тема
val LightPrimary = Color(0xFFDDDFE2)
val LightSecondary = LightTurquoise
val LightBackground = Color.White
val LightSurface = LightPrimary
val LightAccent = Color(0xFF20B2AA)
val LightTertiary = Color(0xFF3EB489)

@Composable
fun MoneyPrTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    systemUiColor: Color? = null, // Опционально: свой цвет для системных баров
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> darkColorScheme(
            primary = DarkPrimary,
            onPrimary = TextPrimaryBlack,
            secondary = DarkSecondary,
            onSecondary = DarkPrimary,
            background = DarkBackground,
            onBackground = TextOnDark,
            surface = DarkSurface,
            onSurface = TextPrimaryBlack,
            tertiary = DarkSecondary,
            onTertiary = TextOnDark,
            error = ErrorRed,
            onError = TextOnDark,
            inversePrimary = DarkAccent
        )
        else -> lightColorScheme(
            primary = LightPrimary,
            onPrimary = TextPrimaryWhite,
            secondary = LightSecondary,
            onSecondary = LightPrimary,
            background = LightBackground,
            onBackground = TextPrimaryWhite,
            surface = LightSurface,
            onSurface = TextDisabled,
            tertiary = LightTertiary,
            onTertiary = TextOnDark,
            error = ErrorRed,
            onError = TextOnDark,
            inversePrimary = LightAccent
        )
    }

    val view = LocalView.current
    val systemBarsColor = systemUiColor ?: colorScheme.surface

    if (!view.isInEditMode) {
        SideEffect {
            val context = view.context
            if (context is Activity) {
                val window = context.window
                window.statusBarColor = systemBarsColor.toArgb()
                window.navigationBarColor = Color.Transparent.toArgb() // Прозрачный цвет для нижней панели
                val insetsController = WindowCompat.getInsetsController(window, view)
                insetsController.isAppearanceLightStatusBars = !darkTheme
                insetsController.isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
fun Window.setSystemBarsColor(color: Color) {
    statusBarColor = color.toArgb()
    navigationBarColor = color.toArgb()
}

