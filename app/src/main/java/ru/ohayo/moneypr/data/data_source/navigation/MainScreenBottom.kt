package ru.ohayo.moneypr.data.data_source.navigation

import com.google.accompanist.systemuicontroller.rememberSystemUiController
import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.runtime.SideEffect

@Composable
fun MainScreen(navController: NavHostController) {
    // Список маршрутов, где BottomNavigation должен быть видимым
    val routesToShowBottomNav = listOf(
        Screen.Records.route,
        Screen.Reports.route,
        Screen.Charts.route,
        Screen.AddAccount.route,
        Screen.Settings.route
    )

    // Состояние для отслеживания текущего маршрута
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Определяем, нужно ли отображать BottomNavigation
    val shouldShowBottomNavigation = currentRoute in routesToShowBottomNav
    // Получаем контроллер системного интерфейса
    val systemUiController = rememberSystemUiController()

    // Определяем, использовать ли тёмные иконки
    val useDarkIcons = !isSystemInDarkTheme()
    // Цвет нижней панели системы
    val navBarColor = if (shouldShowBottomNavigation) {
        MaterialTheme.colorScheme.surface
    } else {
        MaterialTheme.colorScheme.background
    }

    // Применяем цвет к системной панели навигации
    SideEffect {
        systemUiController.setNavigationBarColor(
            color = navBarColor,
            darkIcons = useDarkIcons
        )
    }

    Scaffold(
        bottomBar = {
            if (shouldShowBottomNavigation) {
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.navigationBarsPadding()
                ) {
                    BottomNavigation(navController = navController)
                }
            }
        }
    ) { paddingValues ->
        val backgroundColor = if (shouldShowBottomNavigation) {
            MaterialTheme.colorScheme.surface
        } else {
            MaterialTheme.colorScheme.background
        }

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            NavHostScreen(navController = navController)
        }
    }
}