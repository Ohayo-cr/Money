package ru.ohayo.moneypr.ui.screens.addAccount

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.ohayo.moneypr.data.room.account.AccountType
import ru.ohayo.moneypr.ui.component.dropDown.DropdownSelector
import ru.ohayo.moneypr.ui.screens.currencyScreen.CurrencyViewModel

@Composable
fun AddAccountScreenA(accountVM: AddAccountViewModel = hiltViewModel(),
                     currencyVM: CurrencyViewModel = hiltViewModel()) {

    val currencies by currencyVM.currencies.collectAsState(initial = emptyList())
    var name by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf(AccountType.Cash) }
    var balance by remember { mutableStateOf("") }
    var selectedCurrencySymbol by remember { mutableStateOf("") }
    val selectedCurrency = currencies.find { it.symbol == selectedCurrencySymbol }
    val context = LocalContext.current


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
                    accountVM.addAccount(
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
    }
}