package ru.ohayo.moneypr.ui.navController


import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import ru.ohayo.moneypr.ui.screens.categoryList.CategoryViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import kotlinx.coroutines.flow.first
import ru.ohayo.moneypr.ui.screens.addCategory.AddCategoryScreen
import ru.ohayo.moneypr.ui.screens.addTransaction.AddTransaction
import ru.ohayo.moneypr.ui.screens.allSettings.SettingsScreen
import ru.ohayo.moneypr.ui.screens.categoryList.CategoryList
import ru.ohayo.moneypr.ui.screens.charts.ChartsScreen
import ru.ohayo.moneypr.ui.screens.transactionList.TransactionsList
import ru.ohayo.moneypr.ui.screens.accountScreen.AccountScreen

import ru.ohayo.moneypr.ui.screens.currencyScreen.CurrencyScreen

import ru.ohayo.moneypr.ui.screens.splashScreen.SplashScreen

import ru.ohayo.moneypr.ui.screens.accountScreen.AccountViewModel
import ru.ohayo.moneypr.ui.screens.addAccount.AddAccountScreen
import ru.ohayo.moneypr.ui.screens.addCategory.AddCategoryViewModel
import ru.ohayo.moneypr.ui.screens.currencyScreen.CurrencyViewModel
import ru.ohayo.moneypr.ui.screens.transaction_details.TransactionDetailsScreen
import ru.ohayo.moneypr.ui.screens.transaction_details.TransactionViewModel


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
                isEditMode = false,
                categoryId = null
            )
        }
        composable(
            route = "${Screen.AddCategoryWithId}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getLong("id")

            if (categoryId != null) {
                val addCategoryViewModel: AddCategoryViewModel = hiltViewModel()
                val categoryViewModel: CategoryViewModel = hiltViewModel()

                // Запрашиваем данные категории
                LaunchedEffect(Unit) {
                    categoryViewModel.loadCategory(categoryId)
                }

                AddCategoryScreen(
                    navController = navController,
                    addCategoryVM = addCategoryViewModel,
                    categoryVM = categoryViewModel,
                    isEditMode = true,
                    categoryId = categoryId
                )
            }
        }

        composable(Screen.Currency.route) {
            val currencyViewModel: CurrencyViewModel = hiltViewModel()
            CurrencyScreen(currencyViewModel)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }
        composable(Screen.Charts.route) {
            ChartsScreen()
        }
        composable(Screen.Records.route) {
            TransactionsList(navController = navController)
        }
        composable(Screen.AccountList.route) {
            val accountViewModel: AccountViewModel = hiltViewModel()
            AccountScreen(
                accountViewModel = accountViewModel,
                navController = navController
            )
        }
        composable(Screen.AddAccount.route) {
            AddAccountScreen(navController = navController)
        }
        composable(Screen.AddTransaction.route) {
            val categoryViewModel: CategoryViewModel = hiltViewModel()
            AddTransaction(navController = navController, viewModel = categoryViewModel)
        }
        composable("transaction_details/{transactionId}") { backStackEntry ->
            val transactionId = backStackEntry.arguments?.getString("transactionId")?.toLongOrNull()

            if (transactionId != null) {
                // Получаем ViewModel и СРАЗУ устанавливаем ID
                val viewModel: TransactionViewModel = hiltViewModel()
                viewModel.setTransactionId(transactionId)

                TransactionDetailsScreen(
                    transactionId = transactionId,
                    onBackClick = { navController.popBackStack() },
                    navController = navController
                )
            }
        }
    }
}





