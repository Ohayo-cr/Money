package ru.ohayo.moneypr.ui.theme.screens.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import ru.ohayo.moneypr.data.data_source.navigation.NavigationRoutes
import ru.ohayo.moneypr.data.data_source.navigation.Screen

@Composable
fun UpdateSystemNavigationBar(navController: NavHostController) {
    val routesToShowBottomNav = NavigationRoutes.routesToShowBottomNav
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    // Сохраняем цвета схемы из MaterialTheme
    val colorScheme = MaterialTheme.colorScheme
    val surfaceColor = colorScheme.surface
    val backgroundColor = colorScheme.background

    // Сохраняем последнее значение маршрута
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    // Реагируем на изменения маршрута и обновляем цвет навбар'а
    LaunchedEffect(currentRoute) {
        val navBarColor = if (currentRoute != null && currentRoute in routesToShowBottomNav) {
            surfaceColor
        } else {
            backgroundColor
        }

        systemUiController.setNavigationBarColor(
            color = navBarColor,
            darkIcons = useDarkIcons
        )
    }
}
