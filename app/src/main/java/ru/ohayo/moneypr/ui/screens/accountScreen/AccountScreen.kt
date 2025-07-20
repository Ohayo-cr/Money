package ru.ohayo.moneypr.ui.screens.accountScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.ohayo.moneypr.data.room.account.AccountDbo
import ru.ohayo.moneypr.ui.navController.Screen
import ru.ohayo.moneypr.utils.formate.NumberFormatter


@Composable
fun AccountScreen(navController: NavController,
                  accountViewModel: AccountViewModel,

) {

    val accounts by accountViewModel.accounts.collectAsState(initial = emptyList()) // Получаем список счетов

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {


        Text(text = "Добавить счет", style = MaterialTheme.typography.headlineMedium)
            TextButton(onClick = { navController.navigate(Screen.AddAccount.route)}) {
                Text(text = "Add", color = MaterialTheme.colorScheme.onPrimary)

            }
    }


        Spacer(modifier = Modifier.height(16.dp))

        // Отображение списка счетов
        Text(
            text = "Список счетов",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(accounts) { account ->
                AccountItem(
                    accountDbo = account,
                )
            }
        }
    }
}
@Composable
fun AccountItem(accountDbo: AccountDbo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = accountDbo.name,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Тип: ${accountDbo.type}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            // Используем NumberFormatter напрямую в тексте
            Text(
                text = "Баланс: ${NumberFormatter.format(accountDbo.balance)} ${accountDbo.currency}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
