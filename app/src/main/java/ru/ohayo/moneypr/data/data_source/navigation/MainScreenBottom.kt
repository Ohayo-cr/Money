package ru.ohayo.moneypr.data.data_source.navigation

import com.google.accompanist.systemuicontroller.rememberSystemUiController
import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.ohayo.moneypr.data.data_source.navigation.NavigationRoutes.routesToShowBottomNav
import ru.ohayo.moneypr.ui.theme.screens.components.UpdateSystemNavigationBar
import ru.ohayo.moneypr.viewModel.BottomNavViewModel

@Composable
fun MainScreen(navController: NavHostController,viewModel: BottomNavViewModel = hiltViewModel()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(currentRoute) {
        viewModel.updateNavigation(currentRoute)
    }

    // Состояние для показа/скрытия bottom navigation
    val showBottomNav by viewModel.shouldShowBottomNavigation.collectAsState()

    // Общая высота bottom navigation bar (примерно 56.dp по умолчанию в M3)
    val bottomBarHeight = 64.dp

    Box(modifier = Modifier.fillMaxSize()) {
        // Основной контент
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = if (showBottomNav) bottomBarHeight else 0.dp),
        ) {
            NavHostScreen(navController = navController)
            UpdateSystemNavigationBar(navController)
        }

        // Bottom Navigation Bar — теперь правильно выровнена
        if (showBottomNav) {
            Box(
                modifier = Modifier.align(Alignment.BottomCenter),
                contentAlignment = Alignment.Center
            ) {
                BottomNavigation(navController = navController, viewModel = viewModel)
            }
        }
    }


}