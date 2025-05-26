package ru.ohayo.moneypr.ui.theme.screens.navController

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.ohayo.moneypr.ui.theme.screens.categoryList.CategoryList
import ru.ohayo.moneypr.viewModel.CategoryViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import ru.ohayo.moneypr.ui.theme.screens.AddAccountScreen
import ru.ohayo.moneypr.ui.theme.screens.addCategory.AddCategoryScreen
import ru.ohayo.moneypr.ui.theme.screens.addTransaction.AddTransaction
import ru.ohayo.moneypr.ui.theme.screens.CurrencyScreen
import ru.ohayo.moneypr.ui.theme.screens.RecordsScreen
import ru.ohayo.moneypr.ui.theme.screens.allSettings.SettingsScreen
import ru.ohayo.moneypr.ui.theme.screens.SplashScreen
import ru.ohayo.moneypr.ui.theme.screens.transactionList.TransactionsList
import ru.ohayo.moneypr.viewModel.AccountViewModel
import ru.ohayo.moneypr.viewModel.AddCategoryViewModel
import ru.ohayo.moneypr.viewModel.CurrencyViewModel


@RequiresApi(Build.VERSION_CODES.O)
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
            CategoryList(
                categoryVM = categoryViewModel,
                navController = navController,
            )
        }
        composable(
            route = Screen.AddCategory.route
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
        composable(Screen.AddTransaction.route) {
            val categoryViewModel: CategoryViewModel = hiltViewModel()
            AddTransaction(navController = navController, viewModel = categoryViewModel)
        }
    }
}


