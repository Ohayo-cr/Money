package ru.ohayo.moneypr.ui.navController

sealed class Screen(val route: String) {

    data object Splash : Screen("splash")

    data object Records : Screen("records")
    data object Charts : Screen("charts")
    data object Reports : Screen("reports")
    data object Settings : Screen("settings")
    data object AddTransaction : Screen("add_transaction")

    data object AddAccount : Screen("add_account")

    data object Categories : Screen("categories")
    data  object Currency : Screen("currency")
    data object AddCategory : Screen("add_category")
    data object AddCategoryWithId :  Screen("add_category/{id}")

}
