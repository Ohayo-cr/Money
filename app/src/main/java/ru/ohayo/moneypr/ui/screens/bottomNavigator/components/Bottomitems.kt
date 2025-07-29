package ru.ohayo.moneypr.ui.screens.bottomNavigator.components
import ru.ohayo.moneypr.R
import ru.ohayo.moneypr.ui.navController.Screen


sealed class BottomItem(val title: String, val iconId: Int, val route: String) {
    data object Screen1 : BottomItem("Records", R.drawable.bot_record_icon, Screen.Records.route)
    data object Screen2 : BottomItem("Charts", R.drawable.bot_chart_icon, Screen.Charts.route)
    data object Screen3 : BottomItem("", R.drawable.bot_add_icon, Screen.AddTransaction.route)
    data object Screen4 : BottomItem("Accounts", R.drawable.bot_reports_icon, Screen.AccountList.route)
    data object Screen5 : BottomItem("Settings", R.drawable.bot_settings_icon, Screen.Settings.route)
}