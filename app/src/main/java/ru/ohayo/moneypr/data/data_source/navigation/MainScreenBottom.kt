package ru.ohayo.moneypr.data.data_source.navigation

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MainScreen(navController: NavHostController) {
    // Список маршрутов, на которых BottomNavigation должен быть скрыт
    val routesToHideBottomNav = listOf(
        Screen.Settings.route,
        Screen.Categories.route, // Пример: добавляем еще один экран
        Screen.Currency.route, // Пример: добавляем еще один экран
        Screen.CategoryForTransact.route,
        Screen.Splash.route,

        "${Screen.AddTransaction.route}?categoryId={categoryId}"

    )

    // Состояние для отслеживания текущего маршрута
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Определяем, нужно ли отображать BottomNavigation
    val shouldShowBottomNavigation = currentRoute !in routesToHideBottomNav

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
    SideEffect {
        window.navigationBarColor = navigationBarColor.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars =
            !isDarkTheme // Используем предварительно вычисленное значение
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