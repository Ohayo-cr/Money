package ru.ohayo.moneypr.data.data_source.navigation
import ru.ohayo.moneypr.R


sealed class BottomItem(val title: String, val iconId: Int, val route: String) {
    object Screen1 : BottomItem("Записи", R.drawable._record_icon, Screen.Records.route)
    object Screen2 : BottomItem("Графики", R.drawable._chart_icon, Screen.Charts.route)
    object Screen3 : BottomItem("", R.drawable._add_icon, Screen.CategoryForTransact.route)
    object Screen4 : BottomItem("Отчеты", R.drawable._reports_icon, Screen.AddAccount.route)
    object Screen5 : BottomItem("Настройки", R.drawable._settings_icon, Screen.Settings.route)
}