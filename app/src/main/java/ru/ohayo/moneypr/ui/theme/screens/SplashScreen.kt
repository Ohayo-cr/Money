package ru.ohayo.moneypr.ui.theme.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import ru.ohayo.moneypr.data.data_source.navigation.Screen
import ru.ohayo.moneypr.viewModel.TransactionListViewModel
import ru.ohayo.moneypr.viewModel.TransactionState
import ru.ohayo.moneypr.viewModel.TransactionViewModel

@Composable
fun SplashScreen(navController: NavHostController) {
    val transactionListViewModel: TransactionListViewModel = hiltViewModel()

    // Наблюдаем за состоянием загрузки данных
    val state by transactionListViewModel.state.collectAsState()

    LaunchedEffect(state) {
        when (state) {
            is TransactionState.Success -> {
                // Добавляем задержку перед переходом
                delay(500) // Увеличиваем задержку для лучшего восприятия
                navController.navigate(Screen.Records.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true } // Удаляем SplashScreen из стека
                }
            }
            is TransactionState.Error -> {
                // Обработка ошибки: например, показываем уведомление пользователю
                println("Error loading data: ${(state as TransactionState.Error).message}")
            }
            TransactionState.Loading -> {
                // Просто ждем завершения загрузки
            }
        }
    }

    // UI экрана загрузки
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        when (state) {
            TransactionState.Loading -> {
                // Индикатор загрузки
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Загрузка...", style = MaterialTheme.typography.bodyMedium)
                }
            }
            is TransactionState.Success -> {
                // Анимация или логотип после завершения загрузки
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Загрузка завершена",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Готово!", style = MaterialTheme.typography.titleMedium)
                }
            }
            is TransactionState.Error -> {
                // Сообщение об ошибке с возможностью повторить попытку
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Ошибка",
                        tint = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Ошибка: ${(state as TransactionState.Error).message}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { transactionListViewModel.loadTransactions() }) {
                        Text("Повторить")
                    }
                }
            }
        }
    }
}