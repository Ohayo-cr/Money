package ru.ohayo.moneypr.ui.screens.accountScreen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.domain.allEntity.AccountDbo
import ru.ohayo.moneypr.domain.allEntity.AccountType
import ru.ohayo.moneypr.ui.component.others.DropdownSelector
import ru.ohayo.moneypr.ui.component.others.NumberFormatter

import ru.ohayo.moneypr.ui.screens.currencyScreen.CurrencyViewModel

@Composable
fun AddAccountScreen(
    accountViewModel: AccountViewModel,
    currencyViewModel: CurrencyViewModel
) {
    // Собираем состояние валют из ViewModel
    val currencies by currencyViewModel.currencies.collectAsState(initial = emptyList())
    var name by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf(AccountType.Cash) }
    var balance by remember { mutableStateOf("") }
    var selectedCurrencySymbol by remember { mutableStateOf("") }
    val selectedCurrency = currencies.find { it.symbol == selectedCurrencySymbol }
    val context = LocalContext.current
    val accounts by accountViewModel.accounts.collectAsState(initial = emptyList()) // Получаем список счетов

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Добавить счет", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Поле для ввода названия счета
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Название счета") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Выбор типа счета
        DropdownSelector(
            items = AccountType.entries,
            selectedItem = selectedType,
            onItemSelected = { type -> selectedType = type }, // Обновляем выбранный тип
            itemToString = { it.name },
            label = "Тип счета",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Поле для ввода баланса
        OutlinedTextField(
            value = balance,
            onValueChange = { balance = it },
            label = { Text("Баланс") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Выбор валюты
        if (currencies.isEmpty()) {
            Text(
                text = "Нет доступных валют",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error
            )
        } else {
            DropdownSelector(
                items = currencies,
                selectedItem = selectedCurrency,
                onItemSelected = { currency -> selectedCurrencySymbol = currency.symbol },
                itemToString = { it.name },
                itemIcon = { it.iconResId },
                label = "Выберите валюту",
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка сохранения
        Button(
            onClick = {
                // Проверяем, что все поля заполнены корректно
                if (name.isBlank()) {
                    Toast.makeText(context, "Введите название счета", Toast.LENGTH_SHORT).show()
                } else if (balance.toDoubleOrNull() == null) {
                    Toast.makeText(context, "Введите корректный баланс", Toast.LENGTH_SHORT).show()
                } else if (selectedCurrencySymbol.isEmpty()) {
                    Toast.makeText(context, "Выберите валюту", Toast.LENGTH_SHORT).show()
                } else {
                    // Если все данные корректны, добавляем счет
                    accountViewModel.addAccount(
                        name = name,
                        type = selectedType.name,
                        balance = balance.toDoubleOrNull() ?: 0.0,
                        currency = selectedCurrencySymbol
                    )
                    Toast.makeText(context, "Счет добавлен", Toast.LENGTH_SHORT).show()

                    // Очищаем поля после успешного добавления
                    name = ""
                    selectedType = AccountType.Cash
                    balance = ""
                    selectedCurrencySymbol = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Сохранить")
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
