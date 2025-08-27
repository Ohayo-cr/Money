package ru.ohayo.moneypr.ui.screens.bottomNavigator


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.ohayo.moneypr.ui.screens.bottomNavigator.components.BottomNavigationBar
import ru.ohayo.moneypr.ui.navController.NavHostScreen
import ru.ohayo.moneypr.ui.screens.bottomNavigator.components.UpdateSystemNavigationBar



@Composable
fun MainScreen(navController: NavHostController,viewModel: BottomNavViewModel = hiltViewModel()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(currentRoute) {
        viewModel.updateNavigation(currentRoute)
    }


    val showBottomNav by viewModel.shouldShowBottomNavigation.collectAsState()

    // Общая высота bottom navigation bar (примерно 56.dp по умолчанию в M3)
    val bottomBarHeight = 64.dp

    Box(modifier = Modifier.fillMaxSize()) {
        // Основной контент
        Box(
            modifier = Modifier
                .fillMaxSize()
               
        ) {
            NavHostScreen(navController = navController)
            UpdateSystemNavigationBar(navController)
        }

        // Bottom Navigation Bar — теперь правильно выровнена
        if (showBottomNav) {
            Box(
                modifier = Modifier.align(Alignment.BottomCenter).background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                BottomNavigationBar(navController = navController, viewModel = viewModel)
            }
        }
    }


}