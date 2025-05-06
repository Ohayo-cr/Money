package ru.ohayo.moneypr.data.data_source.navigation

import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun MainScreen(navController: NavHostController) {
    // Список маршрутов, где BottomNavigation должен быть видимым
    val routesToShowBottomNav = listOf(
        Screen.Records.route,
        Screen.Reports.route,
        Screen.Charts.route,
        Screen.AddAccount.route
    )

    // Состояние для отслеживания текущего маршрута
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Определяем, нужно ли отображать BottomNavigation
    val shouldShowBottomNavigation = currentRoute in routesToShowBottomNav

    // Цвет фона для системной навигации
    val colorScheme = MaterialTheme.colorScheme
    val navigationBarColor = if (shouldShowBottomNavigation) {
        colorScheme.surface // Цвет по умолчанию для системной навигации
    } else {
        colorScheme.background // Цвет фона для экранов без BottomNavigation
    }

    // Вычисляем, используем ли мы светлые иконки в системной навигации
    val isDarkTheme = isSystemInDarkTheme() // Вызываем внутри @Composable контекста

    // Доступ к текущему окну
    val view = LocalView.current
    val window = (view.context as Activity).window

    // Динамическое изменение цвета системной навигации
    // Используем LaunchedEffect, чтобы обновить системную панель только при изменении
    LaunchedEffect(navigationBarColor, isDarkTheme) {
        withContext(Dispatchers.Main) {
            window.navigationBarColor = navigationBarColor.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars =
                !isDarkTheme
        }
    }

    Scaffold(
        bottomBar = {
            if (shouldShowBottomNavigation) {
                BottomNavigation(navController = navController)
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavHostScreen(navController = navController)
        }
    }
}