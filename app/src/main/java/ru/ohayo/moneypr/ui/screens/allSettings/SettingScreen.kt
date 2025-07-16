package ru.ohayo.moneypr.ui.screens.allSettings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.ohayo.moneypr.ui.navController.Screen


@Composable
fun SettingsScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = { navController.navigate(Screen.Categories.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Категории")
        }
        Button(
            onClick = { navController.navigate(Screen.Currency.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Валюта")
        }
    }
}