package ru.ohayo.moneypr.ui.screens.addAccount


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.ohayo.moneypr.data.room.account.AccountType
import ru.ohayo.moneypr.ui.component.categoryIcon.CategoryIcon
import ru.ohayo.moneypr.ui.component.customeButton.FullWidthButton
import ru.ohayo.moneypr.ui.component.top_app_panel.TopAppPanel
import ru.ohayo.moneypr.ui.screens.addAccount.components.AccountCardItem
import ru.ohayo.moneypr.ui.screens.addAccount.components.AccountInfoSelector
import ru.ohayo.moneypr.ui.screens.addAccount.components.AccountTextDialog
import ru.ohayo.moneypr.ui.screens.addAccount.components.KeyboardSheet
import ru.ohayo.moneypr.ui.screens.addAccount.components.accountTypeList
import ru.ohayo.moneypr.ui.theme.TextDisabled



@Composable
fun AddAccountScreen(
    accountVM: AddAccountViewModel = hiltViewModel(),
    navController: NavController
) {


    val currencies = accountVM.currencyList
    val dialogStates by accountVM.dialogStates.collectAsState()
    val fieldValues by accountVM.fieldValues.collectAsState()
    val tempFieldValues by accountVM.tempFieldValues.collectAsState()
    val context = LocalContext.current
    val accountState by accountVM.state.collectAsState()
    val selectedIcon by accountVM.selectedIcon.collectAsState(initial = null)


    Column(
        modifier = Modifier
            .fillMaxSize()
            ,

    ) {
        TopAppPanel(
            title = "Add account",
            showBackButton = true,
            navController = navController
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            AccountInfoCard(
                items = listOf(
                    AccountCardItem(
                        key = "Account type",
                        valueText = fieldValues["type"] ?: "",
                        onClick = { accountVM.setShowDialog("accountType", true) }
                    ),
                    AccountCardItem(
                        key = "Account name",
                        valueText = if (fieldValues["name"].isNullOrBlank()) "Please enter name" else fieldValues["name"]
                            ?: "",
                        onClick = { accountVM.setShowDialog("name", true) }
                    )
                )
            )
            AccountInfoCard(
                items = listOf(
                    AccountCardItem(
                        key = "Account balance",
                        valueText = fieldValues["balance"] ?: "0",
                        onClick = { accountVM.setShowDialog("balance", true) }
                    )
                )
            )
            AccountInfoCard(
                items = listOf(
                    AccountCardItem(
                        key = "Account icon",
                        valueIcon = selectedIcon,
                        isClickable = false
                    ),
                    AccountCardItem(
                        key = "Account currency",
                        valueText = fieldValues["currency_display"] ?: "",
                        onClick = { accountVM.setShowDialog("currency", true) }
                    ),
                    AccountCardItem(
                        key = "Account note",
                        valueText = if (fieldValues["note"].isNullOrBlank()) "Note text" else fieldValues["note"]
                            ?: "",
                        onClick = { accountVM.setShowDialog("note", true) }
                    )
                )
            )
        }

        FullWidthButton(
            text = "Save account",
            onClick = {

                val name = fieldValues["name"] ?: ""
                val type = fieldValues["type"] ?: ""
                val balanceString = fieldValues["balance"] ?: "0"
                val note = fieldValues["note"] ?: ""
                val balance = balanceString.replace(" ", "").toDoubleOrNull() ?: 0.0
                if (name.isEmpty()) {
                    Toast.makeText(context, "Введите название счета", Toast.LENGTH_SHORT).show()

                } else {
                    accountVM.addAccount(name, type, balance,  note)
                }
            }
        )
        when (val currentState = accountState) {
            is AddAccountViewModel.AccountState.Success -> {
                val name = fieldValues["name"] ?: ""
                LaunchedEffect(currentState) {
                    navController.popBackStack()
                    Toast.makeText(context, "Счет $name успешно добавлен", Toast.LENGTH_SHORT)
                        .show()

                    accountVM.resetState()
                }
            }

            is AddAccountViewModel.AccountState.Error -> {
                val errorMessage = (currentState).message
                LaunchedEffect(currentState) {
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                    accountVM.resetState()
                }
            }

            AddAccountViewModel.AccountState.Loading -> {}
            AddAccountViewModel.AccountState.Idle -> {}
        }
    }

    if (dialogStates["name"] == true) {
        AccountTextDialog(
            onDismissRequest = {
                accountVM.confirmField("name")
            },
            tempName = tempFieldValues["name"] ?: "",
            onTempNameChange = { accountVM.setTempFieldValue("name", it) },
            onConfirm = {
                accountVM.confirmField("name")
            },
            title = "Enter Account Name"

        )
    }

    if (dialogStates["note"] == true) {
        AccountTextDialog(
            onDismissRequest = {
                accountVM.confirmField("note")
            },
            tempName = tempFieldValues["note"] ?: "",
            onTempNameChange = { accountVM.setTempFieldValue("note", it) },
            onConfirm = {
                accountVM.confirmField("note")
            },
            title = "Enter Note"
        )
    }

    if (dialogStates["accountType"] == true) {
        AccountInfoSelector(
            text = "Enter account type",
            onDismissRequest = { accountVM.confirmField("accountType") },
            items = accountTypeList,
            selectedItem = accountTypeList.find { it.mainText == fieldValues["type"] },
            onItemSelected = { selectedItem ->
                accountVM.setAccountType(AccountType.entries.first { it.name == selectedItem.mainText })
                accountVM.confirmField("accountType")
            }
        )
    }
    if (dialogStates["currency"] == true) {
        AccountInfoSelector(
            text = "Enter Currency",
            onDismissRequest = { accountVM.setShowDialog("currency", false) },
            items = currencies,
            selectedItem = currencies.find { it.mainText == fieldValues["currency"] },
            onItemSelected = { selectedItem ->
                accountVM.setCurrency(selectedItem)
                accountVM.setShowDialog("currency", false)
            }
        )
    }
    if (dialogStates["balance"] == true) {
        KeyboardSheet(
            onDismiss = { accountVM.setShowDialog("balance", false) },
            onOkClicked = { result ->
                accountVM.setFieldValue("balance", result)
                accountVM.setShowDialog("balance", false)

            }
        )
    }

}


@Composable
fun AccountInfoCard(items: List<AccountCardItem>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(colorScheme.surface)
    ) {
        Column {
            items.forEachIndexed { index, item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            enabled = item.isClickable,
                            onClick = item.onClick
                        )
                        .padding(horizontal = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item.key,
                            color = colorScheme.onPrimary,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )
                        if (item.valueText != null) {
                            Text(text = item.valueText, color = TextDisabled)
                        } else if (item.valueIcon != null) {
                            CategoryIcon(
                                iconResId = item.valueIcon,
                                backgroundColor = Color.Gray
                            )
                        }
                    }
                }
                if (index < items.size - 1) {
                    Divider(modifier = Modifier.padding(horizontal = 8.dp))
                }
            }
        }
    }
}