package ru.ohayo.moneypr.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.ohayo.moneypr.data.data_source.navigation.Screen


@Composable
fun SettingsScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Кнопка "Категории"
        Button(
            onClick = { navController.navigate(Screen.Categories.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Категории")
        }

        // Кнопка "Валюта"
        Button(
            onClick = { navController.navigate(Screen.Currency.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Валюта")
        }
    }
}