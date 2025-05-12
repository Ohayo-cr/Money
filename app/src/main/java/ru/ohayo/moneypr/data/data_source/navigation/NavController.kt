package ru.ohayo.moneypr.data.data_source.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.ohayo.moneypr.ui.theme.screens.CategoryList
import ru.ohayo.moneypr.viewModel.CategoryViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.navArgument
import ru.ohayo.moneypr.ui.theme.screens.AddAccountScreen
import ru.ohayo.moneypr.ui.theme.screens.AddCategoryScreen
import ru.ohayo.moneypr.ui.theme.screens.AddTransactionCategory
import ru.ohayo.moneypr.ui.theme.screens.AddTransactionScreen
import ru.ohayo.moneypr.ui.theme.screens.CurrencyScreen
import ru.ohayo.moneypr.ui.theme.screens.RecordsScreen
import ru.ohayo.moneypr.ui.theme.screens.SettingsScreen
import ru.ohayo.moneypr.ui.theme.screens.SplashScreen
import ru.ohayo.moneypr.ui.theme.screens.TransactionsList
import ru.ohayo.moneypr.viewModel.AccountViewModel
import ru.ohayo.moneypr.viewModel.AddCategoryViewModel
import ru.ohayo.moneypr.viewModel.CurrencyViewModel
import ru.ohayo.moneypr.viewModel.TransactionViewModel


@Composable
fun NavHostScreen(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Records.route,
        enterTransition = { fadeIn(animationSpec = tween(0)) },
        exitTransition = { fadeOut(animationSpec = tween(0)) },
        popEnterTransition = { fadeIn(animationSpec = tween(0)) },
        popExitTransition = { fadeOut(animationSpec = tween(0)) }
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screen.Categories.route) {
            val categoryViewModel: CategoryViewModel = hiltViewModel()

            CategoryList(categoryVM = categoryViewModel, navController = navController)
        }
        // Маршрут с аргументом для типа категории
        composable(
            route = Screen.AddCategory.route // Убран аргумент из маршрута
        ) {
            val addCategoryViewModel: AddCategoryViewModel = hiltViewModel()
            val categoryViewModel: CategoryViewModel = hiltViewModel()
            AddCategoryScreen(
                navController = navController,
                addCategoryVM = addCategoryViewModel,
                categoryVM = categoryViewModel,
            )
        }
        composable(Screen.Currency.route) {
            val currencyViewModel: CurrencyViewModel = hiltViewModel()
            CurrencyScreen(currencyViewModel)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }
        composable(Screen.Charts.route) {
            RecordsScreen()
        }
        composable(Screen.Records.route) {
            TransactionsList()
        }
        composable(Screen.AddAccount.route) {
            val accountViewModel: AccountViewModel = hiltViewModel()
            val currencyViewModel: CurrencyViewModel = hiltViewModel()

            AddAccountScreen(
                accountViewModel = accountViewModel,
                currencyViewModel = currencyViewModel
            )
        }
        composable(Screen.CategoryForTransact.route,

        ) {
            val categoryViewModel: CategoryViewModel = hiltViewModel()
            AddTransactionCategory(navController = navController, viewModel = categoryViewModel)
        }
        composable(
            route = "${Screen.AddTransaction.route}?categoryId={categoryId}",
            arguments = listOf(navArgument("categoryId") { defaultValue = "-1" })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")?.toIntOrNull() ?: -1
            if (categoryId != -1) {
                val transactionViewModel: TransactionViewModel = hiltViewModel()
                AddTransactionScreen(
                    categoryId = categoryId,
                    viewModel = transactionViewModel,
                    onBack = { navController.popBackStack() }
                )
            } else {
                Text("Invalid category ID")
            }
        }
    }
}


