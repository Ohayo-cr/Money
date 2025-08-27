package ru.ohayo.moneypr.ui.screens.bottomNavigator.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.ohayo.moneypr.ui.navController.NavigationRoutes

@Composable
fun UpdateSystemNavigationBar(navController: NavHostController) {
    val routesToShowBottomNav = NavigationRoutes.routesToShowBottomNav
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    // Сохраняем цвета схемы из MaterialTheme
    val colorScheme = MaterialTheme.colorScheme
    val primaryColor = colorScheme.primary
    val backgroundColor = colorScheme.background

    // Сохраняем последнее значение маршрута
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    // Реагируем на изменения маршрута и обновляем цвет навбар'а
    LaunchedEffect(currentRoute) {
        val navBarColor = if (currentRoute != null && currentRoute in routesToShowBottomNav) {
            primaryColor
        } else {
            backgroundColor
        }

        systemUiController.setNavigationBarColor(
            color = navBarColor,
            darkIcons = useDarkIcons
        )
    }
}
