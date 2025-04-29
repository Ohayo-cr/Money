package ru.ohayo.moneypr.ui.theme.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.ohayo.moneypr.domain.Account
import ru.ohayo.moneypr.domain.AccountType
import ru.ohayo.moneypr.domain.TransactionEntity
import ru.ohayo.moneypr.domain.category.CategoryType
import ru.ohayo.moneypr.ui.theme.screens.components.AccountSelectionDialog
import ru.ohayo.moneypr.ui.theme.screens.components.CalculatorKeyboard
import ru.ohayo.moneypr.ui.theme.screens.components.DropdownSelector
import ru.ohayo.moneypr.ui.theme.screens.components.NoteField
import ru.ohayo.moneypr.ui.theme.screens.components.TopPanelKeyboard
import ru.ohayo.moneypr.viewModel.KeyboardViewModel
import ru.ohayo.moneypr.viewModel.TransactionViewModel


@Composable
fun AddTransactionScreen(
    categoryId: Int,
    viewModel: TransactionViewModel = hiltViewModel(),
    keyboardViewModel: KeyboardViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    var content by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    // Выбор счета
    var selectedFromAccount by remember { mutableStateOf<Account?>(null) }
    var selectedToAccount by remember { mutableStateOf<Account?>(null) }

    // Получаем данные из ViewModel
    val accounts by viewModel.accounts.collectAsState(initial = emptyList())
    val currencies by viewModel.currencies.collectAsState(initial = emptyList())
    val category by viewModel.getCategoryById(categoryId.toLong()).collectAsState(initial = null)

    // Автоматически выбираем счета по умолчанию
    val defaultFromAccount = if (category?.type == CategoryType.EXPENSE) {
        accounts.firstOrNull { it.type == AccountType.Card || it.type == AccountType.Cash }
    } else {
        null
    }

    val defaultToAccount = if (category?.type == CategoryType.INCOME) {
        accounts.firstOrNull { it.type == AccountType.Card || it.type == AccountType.Cash }
    } else {
        null
    }

    // Устанавливаем значения по умолчанию
    LaunchedEffect(defaultFromAccount, defaultToAccount) {
        selectedFromAccount = defaultFromAccount
        selectedToAccount = defaultToAccount
    }

    // Определяем валюту на основе выбранного счета
    val currency = remember(selectedFromAccount, selectedToAccount) {
        val account = selectedFromAccount ?: selectedToAccount
        currencies.find { it.id == account?.currency }
    }

    // Менеджер фокуса
    val focusManager = LocalFocusManager.current

    // Состояние для отображения диалога выбора счета
    var showAccountSelectionDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { focusManager.clearFocus() }, // Убираем фокус при клике вне TextField
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(1.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            // Верхняя панель с отображением ввода
            TopPanelKeyboard(
                viewModel = keyboardViewModel,
                onAccountButtonClicked = {
                    showAccountSelectionDialog = true
                },
                selectedAccountName = if (category?.type == CategoryType.EXPENSE) {
                    selectedFromAccount?.name ?: "Выбрать счет"
                } else {
                    selectedToAccount?.name ?: "Выбрать счет"
                },
                currencyText = if (currency != null) {
                    "${currency.name}"
                } else {
                    "Currency: No currency available"
                }
            )
            // Поле для заметки
            NoteField(
                viewModel = keyboardViewModel,
                onFocusChanged = { isFocused ->
                    // Handle focus change logic here if needed
                },
                onTextChanged = { newText ->
                    content = newText // Обновляем состояние content при изменении текста
                }
            )
            // Калькуляторная клавиатура
            CalculatorKeyboard(
                viewModel = keyboardViewModel,
                onDateButtonClicked = { /* Handle date selection */ },
                onHideKeyboardClicked = { /* Handle hide keyboard */ },
                onOkClicked = {
                    val parsedAmount = keyboardViewModel.result.toDoubleOrNull() ?: keyboardViewModel.currentInput.toDoubleOrNull()
                    if (parsedAmount != null && currency != null) {
                        val transaction = TransactionEntity(
                            amount = parsedAmount,
                            content = if (content.isBlank()) null else content, // Сохраняем заметку
                            timestamp = System.currentTimeMillis(),
                            category = categoryId.toLong(),
                            fromAccount = selectedFromAccount?.id,
                            toAccount = selectedToAccount?.id,
                            currency = currency?.id
                        )
                        viewModel.addTransaction(transaction) // Добавляем транзакцию в базу данных
                        onBack()
                    } else {
                        isError = true
                    }
                }
            )
        }
    }

    // Диалог выбора счета
    if (showAccountSelectionDialog) {
        AccountSelectionDialog(
            accounts = accounts,
            selectedAccount = if (category?.type == CategoryType.EXPENSE) selectedFromAccount else selectedToAccount,
            onDismiss = { showAccountSelectionDialog = false },
            onAccountSelected = { account ->
                if (category?.type == CategoryType.EXPENSE) {
                    selectedFromAccount = account
                } else {
                    selectedToAccount = account
                }
                showAccountSelectionDialog = false
            }
        )
    }
}