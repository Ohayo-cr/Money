package ru.ohayo.moneypr.ui.theme.screens.addTransaction

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
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
import ru.ohayo.moneypr.domain.allEntity.Account
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
import ru.ohayo.moneypr.viewModel.AccountViewModel
import ru.ohayo.moneypr.viewModel.KeyboardViewModel
import ru.ohayo.moneypr.viewModel.TransactionViewModel




@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddTransactionForm(
    categoryId: Int,
    viewModel: TransactionViewModel = hiltViewModel(),
    keyboardViewModel: KeyboardViewModel = hiltViewModel(),
    accountViewModel: AccountViewModel = hiltViewModel(),
    onTransactionAdded: () -> Unit,
    modifier: Modifier
) {
    val selectedAccount by accountViewModel.selectedAccount.collectAsState()
    val note by viewModel.note
    var isError by remember { mutableStateOf(false) }
    var selectedFromAccount by remember { mutableStateOf(selectedAccount) }
    var selectedToAccount by remember { mutableStateOf(selectedAccount) }
    val accounts by viewModel.accounts.collectAsState(initial = emptyList())
    val category by viewModel.getCategoryById(categoryId.toLong()).collectAsState(initial = null)

    LaunchedEffect(category, selectedAccount) {
        selectedFromAccount = when (category?.type) {
            CategoryType.EXPENSE -> selectedAccount
            else -> null
        }
        selectedToAccount = when (category?.type) {
            CategoryType.INCOME -> selectedAccount
            else -> null
        }
    }

    val currencyAcc = selectedAccount?.currency ?: "Not"

    val focusManager = LocalFocusManager.current
    var showAccountSelectionDialog by remember { mutableStateOf(false) }

    var showDatePickerDialog by remember { mutableStateOf(false) }
    val lastDateFromDB by viewModel.lastSelectedDate.collectAsState(initial = System.currentTimeMillis())
    val selectedDate by viewModel.selectedDate.collectAsState()
    var transactionDate by remember { mutableLongStateOf(selectedDate) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Divider(
            modifier = Modifier
                .height(1.dp),
            color = colorScheme.outlineVariant.copy(alpha = 0.4f)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorScheme.background)
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
                    selectedAccountName = selectedAccount?.name ?: "X account",
                    currencyText = currencyAcc
                )

                NoteField(
                    note = note,
                    onTextChanged = { viewModel.updateNote(it) },
                    onFocusChanged = {}
                )

                CalculatorKeyboard(
                    viewModel = keyboardViewModel,
                    onDateButtonClicked = {
                        showDatePickerDialog = true
                    },
                    onHideKeyboardClicked = {},
                    dateText = formatLocalDateTime(transactionDate),
                    onOkClicked = {
                        val parsedAmount = keyboardViewModel.getParsedAmount()
                        if (parsedAmount != null && currencyAcc != "Not") {
                            val amountWithSign = when (category?.type) {
                                CategoryType.EXPENSE -> -parsedAmount // Расход — отрицательная сумма
                                else -> parsedAmount // Доход — положительная сумма
                            }

                            val transaction = TransactionEntity(
                                amount = amountWithSign,
                                content = note.ifBlank { null },
                                timestamp = transactionDate,
                                category = categoryId.toLong(),
                                fromAccount = selectedFromAccount?.id,
                                toAccount = selectedToAccount?.id,
                                currency = currencyAcc
                            )
                            viewModel.addTransaction(transaction)
                            onTransactionAdded()

                        } else {
                            isError = true
                        }
                    }
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
                        initialDateTime = initialDateTime,
                        lastSelectedDate = lastDateFromDB
                    )
                }

                if (showAccountSelectionDialog) {

                    AccountSelectionDialog(
                        accounts = accounts,
                        selectedAccount = selectedAccount,
                        onDismiss = { showAccountSelectionDialog = false },
                        onAccountSelected = { account: Account ->
                            accountViewModel.setSelectedAccount(account)
                            showAccountSelectionDialog = false
                        }
                    )
                }

            }
        }
    }

}
