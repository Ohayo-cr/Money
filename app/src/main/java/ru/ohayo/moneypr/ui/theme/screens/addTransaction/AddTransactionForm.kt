package ru.ohayo.moneypr.ui.theme.screens.addTransaction

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.onEach
import ru.ohayo.moneypr.domain.allEntity.Account
import ru.ohayo.moneypr.domain.allEntity.AccountType
import ru.ohayo.moneypr.domain.allEntity.TransactionEntity
import ru.ohayo.moneypr.domain.allEntity.CategoryType
import ru.ohayo.moneypr.ui.theme.screens.components.AccountSelectionDialog
import ru.ohayo.moneypr.ui.theme.screens.components.DatePickerScrollDialog
import ru.ohayo.moneypr.ui.theme.screens.components.customeKeyboard.CalculatorKeyboard
import ru.ohayo.moneypr.ui.theme.screens.components.customeKeyboard.NoteField
import ru.ohayo.moneypr.ui.theme.screens.components.customeKeyboard.TopPanelKeyboard
import ru.ohayo.moneypr.ui.theme.screens.components.customeKeyboard.formatLocalDateTime
import ru.ohayo.moneypr.ui.theme.screens.components.customeKeyboard.millisToLocalDateTime
import ru.ohayo.moneypr.ui.theme.screens.components.localDateTimeToMillis
import ru.ohayo.moneypr.viewModel.KeyboardViewModel
import ru.ohayo.moneypr.viewModel.TransactionViewModel



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddTransactionForm(
    categoryId: Int,
    viewModel: TransactionViewModel = hiltViewModel(),
    keyboardViewModel: KeyboardViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onTransactionAdded: () -> Unit
) {
    var content by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var selectedFromAccount by remember { mutableStateOf<Account?>(null) }
    var selectedToAccount by remember { mutableStateOf<Account?>(null) }
    val accounts by viewModel.accounts.collectAsState(initial = emptyList())
    val currencies by viewModel.currencies.collectAsState(initial = emptyList())
    val category by viewModel.getCategoryById(categoryId.toLong()).collectAsState(initial = null)

    val defaultAccount = accounts.firstOrNull {
        it.type == AccountType.Card || it.type == AccountType.Cash
    }

    LaunchedEffect(category, defaultAccount) {
        selectedFromAccount = when (category?.type) {
            CategoryType.EXPENSE -> defaultAccount
            else -> null
        }
        selectedToAccount = when (category?.type) {
            CategoryType.INCOME -> defaultAccount
            else -> null
        }
    }

    val currency = remember(selectedFromAccount, selectedToAccount) {
        currencies.find { it.id == (selectedFromAccount ?: selectedToAccount)?.currency }
    }

    val focusManager = LocalFocusManager.current
    var showAccountSelectionDialog by remember { mutableStateOf(false) }
    var showDatePickerDialog by remember { mutableStateOf(false) }
    val selectedDate by viewModel.selectedDate.collectAsState()

    var transactionDate by remember { mutableLongStateOf(selectedDate) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Divider(
            modifier = Modifier
                .height(1.dp),
            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() },
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalArrangement = Arrangement.Top
            ) {
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
                    currencyText = if (currency != null) "${currency.name}" else "Non"
                )

                NoteField(
                    viewModel = keyboardViewModel,
                    onFocusChanged = {},
                    onTextChanged = { newText ->
                        content = newText
                    }
                )

                CalculatorKeyboard(
                    viewModel = keyboardViewModel,
                    onDateButtonClicked = {
                        showDatePickerDialog = true
                    },
                    onHideKeyboardClicked = {},
                    onOkClicked = {
                        val parsedAmount = keyboardViewModel.result.toDoubleOrNull()
                            ?: keyboardViewModel.currentInput.toDoubleOrNull()

                        if (parsedAmount != null && currency != null) {
                            val transaction = TransactionEntity(
                                amount = parsedAmount,
                                content = if (content.isBlank()) null else content,
                                timestamp = transactionDate,
                                category = categoryId.toLong(),
                                fromAccount = selectedFromAccount?.id,
                                toAccount = selectedToAccount?.id,
                                currency = currency?.id
                            )
                            viewModel.addTransaction(transaction)
                            keyboardViewModel.reset()
                            content = ""
                            onTransactionAdded()
                            onBack()
                        } else {
                            isError = true
                        }
                    },
                    dateText =  formatLocalDateTime(transactionDate)
                )


                if (showDatePickerDialog) {
                    val initialDateTime = millisToLocalDateTime(transactionDate)
                    DatePickerScrollDialog(
                        onDismiss = { showDatePickerDialog = false },
                        onDateSelected = { dateTime ->
                            val newDate = localDateTimeToMillis(dateTime)
                            transactionDate = newDate
                            viewModel.setSelectedDate(newDate)
                            showDatePickerDialog = false
                        },
                        initialDateTime = initialDateTime
                    )
                }

                if (showAccountSelectionDialog) {
                    val selectedAccount = when (category?.type) {
                        CategoryType.EXPENSE -> selectedFromAccount
                        else -> selectedToAccount
                    }

                    AccountSelectionDialog(
                        accounts = accounts,
                        selectedAccount = selectedAccount,
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
        }
    }
}