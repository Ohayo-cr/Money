package ru.ohayo.moneypr.ui.theme.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.domain.allEntity.Account

@Composable
fun AccountSelectionDialog(
    accounts: List<Account>,
    selectedAccount: Account?,
    onDismiss: () -> Unit,
    onAccountSelected: (Account) -> Unit
) {
    var expanded by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }

    // Фильтрация счетов по поисковому запросу
    val filteredAccounts = remember(accounts, searchQuery) {
        if (searchQuery.isBlank()) {
            accounts
        } else {
            accounts.filter { account ->
                account.name.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    if (expanded) {
        AlertDialog(
            onDismissRequest = {
                expanded = false
                onDismiss()
            },
            title = { Text("Выберите счет") },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 300.dp) // Ограничиваем высоту диалога
                ) {
                    // Поле для поиска
                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Поиск счета...") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Список счетов
                    LazyColumn {
                        items(filteredAccounts) { account ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onAccountSelected(account)
                                        expanded = false
                                    }
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = selectedAccount?.id == account.id,
                                    onClick = {
                                        onAccountSelected(account)
                                        expanded = false
                                    }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = account.name)
                            }
                        }

                        // Если нет результатов поиска
                        if (filteredAccounts.isEmpty()) {
                            item {
                                Text(
                                    text = "Счета не найдены",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(onClick = { expanded = false }) {
                    Text("Отмена")
                }
            }
        )
    }
}