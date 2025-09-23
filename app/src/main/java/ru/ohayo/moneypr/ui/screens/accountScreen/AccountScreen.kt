package ru.ohayo.moneypr.ui.screens.accountScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.ohayo.moneypr.R
import ru.ohayo.moneypr.data.room.account.AccountDbo
import ru.ohayo.moneypr.ui.component.categoryIcon.CategoryIcon
import ru.ohayo.moneypr.ui.component.top_app_panel.TopAppPanel
import ru.ohayo.moneypr.ui.navController.Screen
import ru.ohayo.moneypr.utils.formate.NumberFormatter


@Composable
fun AccountScreen(navController: NavController,
                  accountViewModel: AccountViewModel,

) {

    val accounts by accountViewModel.accounts.collectAsState(initial = emptyList()) // Получаем список счетов
    val balanceInfo by accountViewModel.balanceInfo.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppPanel(
            title = "Список счетов",
            leftIcon1 = painterResource(id = R.drawable.bot_add_icon),
            onIconClick1 = { navController.navigate(Screen.AddAccount.route)}
        )
        TotalBalanceBox(
            totalBalance = balanceInfo.totalBalance,
            currencySymbol = balanceInfo.currencySymbol
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 4.dp, end = 4.dp)
                .weight(1f)
        ) {
            items(accounts) { account ->
                AccountItem(
                    accountDbo = account,
                )
            }
            item {
                Spacer(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
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
            .padding(vertical = 2.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 4.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row( modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
            accountDbo.icon?.let {
                CategoryIcon(
                    iconResId = it,
                    backgroundColor = Color.Gray
                )
            }
                Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = accountDbo.name,
                style = MaterialTheme.typography.titleMedium
            )
        }


            Text(
                text = "${NumberFormatter.format(accountDbo.balance)} ${accountDbo.currencySymbol}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
@Composable
fun TotalBalanceBox(totalBalance: Double, currencySymbol: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, top = 8.dp, end = 4.dp)
            .height(60.dp)
            .background(
                color = MaterialTheme.colorScheme.tertiary,
                shape = RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Общий баланс: ${NumberFormatter.format(totalBalance)} $currencySymbol",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold
        )
    }
}
