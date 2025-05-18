package ru.ohayo.moneypr.ui.theme.screens.bottomNavigator
import ru.ohayo.moneypr.R
import ru.ohayo.moneypr.ui.theme.screens.navController.Screen


sealed class BottomItem(val title: String, val iconId: Int, val route: String) {
    object Screen1 : BottomItem("Записи", R.drawable.bot_record_icon, Screen.Records.route)
    object Screen2 : BottomItem("Графики", R.drawable.bot_chart_icon, Screen.Charts.route)
    object Screen3 : BottomItem("", R.drawable.bot_add_icon, Screen.AddTransaction.route)
    object Screen4 : BottomItem("Счета", R.drawable.bot_reports_icon, Screen.AddAccount.route)
    object Screen5 : BottomItem("Настройки", R.drawable.bot_settings_icon, Screen.Settings.route)
}