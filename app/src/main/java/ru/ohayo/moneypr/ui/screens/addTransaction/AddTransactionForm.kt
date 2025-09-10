package ru.ohayo.moneypr.ui.screens.addTransaction



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
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
import ru.ohayo.moneypr.data.room.account.AccountDbo
import ru.ohayo.moneypr.data.room.transaction.TransactionDbo
import ru.ohayo.moneypr.data.room.category.CategoryType
import ru.ohayo.moneypr.ui.screens.addTransaction.componens.AccountSelectSheet
import ru.ohayo.moneypr.ui.screens.addTransaction.componens.DatePickerSheet
import ru.ohayo.moneypr.ui.component.customeKeyboard.TransactionKeyboard
import ru.ohayo.moneypr.ui.component.customeKeyboard.NoteField
import ru.ohayo.moneypr.ui.component.customeKeyboard.TopPanelKeyboard
import ru.ohayo.moneypr.utils.formate.formatLocalDateTime
import ru.ohayo.moneypr.utils.formate.millisToLocalDateTime
import ru.ohayo.moneypr.utils.formate.localDateTimeToMillis
import ru.ohayo.moneypr.ui.screens.accountScreen.AccountViewModel




@Composable
fun AddTransactionForm(
    categoryId: Int,
    viewModel: AddTransactionViewModel = hiltViewModel(),
    keyboardViewModel: KeyboardViewModel = hiltViewModel(),
    accountViewModel: AccountViewModel = hiltViewModel(),
    onTransactionAdded: () -> Unit,
    transferMod: Boolean = false,
    modifier: Modifier
) {
    val selectedAccount by accountViewModel.selectedAccountDbo.collectAsState()
    val note by viewModel.note
    var isError by remember { mutableStateOf(false) }
    val accounts by viewModel.accounts.collectAsState(initial = emptyList())

    val selectedCategory = viewModel.getSelectedCategory()
    val currencyAcc = selectedAccount?.currency ?: "Not"

    val focusManager = LocalFocusManager.current
    var showAccountSelection by remember { mutableStateOf(false) }

    var showDatePickerDialog by remember { mutableStateOf(false) }
    val lastDateFromDB by viewModel.lastSelectedDate.collectAsState(initial = System.currentTimeMillis())
    val selectedDate by viewModel.selectedDate.collectAsState()
    var transactionDate by remember { mutableLongStateOf(selectedDate) }
    // Для переводов
    val fromAccount by viewModel.fromAccount
    val toAccount by viewModel.toAccount

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
                        showAccountSelection = true
                    },
                    selectedAccountName = if(!transferMod)selectedAccount?.name ?: "X account" else "",
                    currencyText = if(!transferMod) currencyAcc else ""
                )

                NoteField(
                    note = note,
                    onTextChanged = { viewModel.updateNote(it) },
                    onFocusChanged = {}
                )

                TransactionKeyboard(
                    viewModel = keyboardViewModel,
                    onDateButtonClicked = {
                        showDatePickerDialog = true
                    },
                    onHideKeyboardClicked = {},
                    dateText = formatLocalDateTime(transactionDate),
                    onOkClicked = {
                        val parsedAmount = keyboardViewModel.getParsedAmount()
                        if (parsedAmount != null) {
                            if (transferMod) {
                                // Режим перевода
                                if (fromAccount != null && toAccount != null) {
                                    viewModel.addTransferTransaction(
                                        amount = parsedAmount,
                                        note = note.ifBlank { null }
                                    )
                                    onTransactionAdded()
                                } else {
                                    isError = true
                                }
                            } else {
                                // Обычный режим транзакции
                                if (currencyAcc != "Not" && selectedCategory != null) {
                                    val amountWithSign = when (selectedCategory.type) {
                                        CategoryType.Expense -> -parsedAmount // Расход — отрицательная сумма
                                        else -> parsedAmount // Доход — положительная сумма
                                    }

                                    val transaction = TransactionDbo(
                                        amount = amountWithSign,
                                        note = note.ifBlank { null },
                                        type = selectedCategory.type,
                                        timestamp = transactionDate,
                                        category = selectedCategory.categoryName,
                                        account = selectedAccount?.name,
                                        currency = currencyAcc
                                    )
                                    viewModel.addTransaction(transaction)
                                    onTransactionAdded()
                                } else {
                                    isError = true
                                }
                            }
                        } else {
                            isError = true
                        }
                    }
                )


                if (showDatePickerDialog) {
                    val initialDateTime = millisToLocalDateTime(transactionDate)
                    DatePickerSheet(
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

                if (showAccountSelection) {
                    AccountSelectSheet(
                        account = accounts,
                        selectedAccountDbo = selectedAccount,
                        onDismiss = { showAccountSelection = false },
                        onAccountSelected = { accountDbo: AccountDbo ->
                            accountViewModel.setSelectedAccount(accountDbo)
                            showAccountSelection = false
                        }
                    )
                }

            }
        }
    }

}
