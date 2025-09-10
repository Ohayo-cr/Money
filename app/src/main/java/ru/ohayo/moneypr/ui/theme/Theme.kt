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
val AppleGreenColor = Color(0xFF50C878)
val ErrorColor = Color(0xFF8B0000)
val CornflowerBlueColor = Color(0xFF2196F3)


// Цвета текста
val TextBlack = Color.Black
val TextWhite = Color.White
val TextSecondary = Color(0xFF383A3F)
val TextDisabled = Color(0xFF898E98)
val TextOnDark = Color.White

// Темная тема
val DarkPrimary = Color(0xFF0F0F0F)
val DarkSecondary = Color(0xBF7E7E7E)
val DarkBackground = Color(0xFF222222)

val DarkAccent = Color(0xFF1C6F6F)
val DarkTertiary = Color(0xFF505050)

// Светлая тема
val LightPrimary = Color(0xFFDDDFE2)
val LightSecondary = Color(0xBF7E7E7E)
val LightBackground = Color.White

val LightAccent = Color(0xFF20B2AA)
val LightTertiary = Color(0xFFE2E2E2)

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
            primary = Color(0xFF101624),
            onPrimary = Color(0xFFF5F7FA),
            secondary = Color(0xFF3D4966),
            onSecondary = Color(0xFFF5F7FA),
            background = Color(0xFF1A2035),
            onBackground = Color(0xFFEDEFF4),
            surface = Color(0xFF1C2338),
            onSurface = Color(0xFFBEC5D4),
            tertiary = Color(0xFF4A5772),
            onTertiary = Color(0xFFF5F7FA),
            error = Color(0xFFD63031),
            onError = Color.White,
            inversePrimary = Color(0xFF00A896),
            surfaceVariant = Color(0xFF424242)
        )
        else -> lightColorScheme(
            primary = Color(0xFFF5F7FA),
            onPrimary = Color(0xFF1F2937),
            secondary = Color(0xFFCBD5E1),
            onSecondary = Color(0xFF1F2937),
            background = Color.White,
            onBackground = Color(0xFF1F2937),
            surface = Color(0xFFF1F5F9),
            onSurface = Color(0xFF475569),
            tertiary = Color(0xFFE2E8F0),
            onTertiary = Color(0xFF475569),
            error = Color(0xFFD63031),
            onError = Color.White,
            inversePrimary = Color(0xFF67E4D6),
            surfaceVariant = Color(0xFFDBDBDB)
        )
    }

    val view = LocalView.current
    val systemBarsColor = systemUiColor ?: colorScheme.primary

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

