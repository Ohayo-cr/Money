package ru.ohayo.moneypr.data.data_source.navigation

sealed class Screen(val route: String) {
    // Стартовые экраны
    object Splash : Screen("splash")

    // Основные экраны (вкладки)
    object Records : Screen("records")  // Записи
    object Charts : Screen("charts")    // Графики
    object Reports : Screen("reports")  // Отчеты
    object Settings : Screen("settings") // Настройки

    // Управление транзакциями
    object AddTransaction : Screen("add_transaction")       // Добавить транзакцию
    object CategoryForTransact : Screen("category_list")          // Список категорий

    // Управление аккаунтами
    object AddAccount : Screen("add_account")              // Добавить счет

    // Настройки
    object Categories : Screen("categories")               // Категории
    object Currency : Screen("currency")                   // Валюта

    companion object {
        // Поиск экрана по route
        fun fromRoute(route: String?): Screen =
            when (route) {
                Splash.route -> Splash
                Records.route -> Records
                Charts.route -> Charts
                Reports.route -> Reports
                Settings.route -> Settings
                AddTransaction.route -> AddTransaction
                CategoryForTransact.route -> CategoryForTransact
                AddAccount.route -> AddAccount
                Categories.route -> Categories
                Currency.route -> Currency
                else -> Splash // или обработка неизвестного route
            }
    }
}
