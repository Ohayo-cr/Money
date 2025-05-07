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
    object AddCategory : Screen("add_category")

}
