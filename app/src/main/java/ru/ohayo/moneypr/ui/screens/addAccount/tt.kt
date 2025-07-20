package ru.ohayo.moneypr.ui.screens.addAccount

import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.ohayo.moneypr.data.room.account.AccountType
import ru.ohayo.moneypr.ui.component.customeButton.BackButton
import ru.ohayo.moneypr.ui.component.dropDown.DropdownSelector
import ru.ohayo.moneypr.ui.screens.addAccount.components.AccountNameDialog
import ru.ohayo.moneypr.ui.screens.currencyScreen.CurrencyViewModel
import ru.ohayo.moneypr.ui.theme.TextDisabled

@Composable
fun AddAccountScreen(accountVM: AddAccountViewModel = hiltViewModel(),
                     currencyVM: CurrencyViewModel = hiltViewModel(),
                     navController: NavController) {

    val currencies by currencyVM.currencies.collectAsState(initial = emptyList())

    var selectedType by remember { mutableStateOf(AccountType.Cash) }
    var balance by remember { mutableStateOf("") }
    var selectedCurrencySymbol by remember { mutableStateOf("") }
    val selectedCurrency = currencies.find { it.symbol == selectedCurrencySymbol }
    val context = LocalContext.current
    val dialogStates by accountVM.dialogStates.collectAsState()
    val fieldValues by accountVM.fieldValues.collectAsState()
    val tempFieldValues by accountVM.tempFieldValues.collectAsState()



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                BackButton(navController)
                Text(text = "Add account", color = colorScheme.onPrimary, fontSize = 18.sp)
            }

        }
        AccountInfoCard(
            items = listOf(
                Triple("Account type", selectedType.toString()) { println("Clicked on Name") },
                Triple("Account name", fieldValues["name"] ?: "") {
                    accountVM.setShowDialog("name", true)
                }
            )
        )
        AccountInfoCard(
            items = listOf(
                Triple("Account balance", "0") { println("Clicked on Name") }
            )
        )
        AccountInfoCard(
            items = listOf(
                Triple("Account icon", "name") { println("Clicked on Name") },
                Triple("Account currency", "viewModel.email") { println("Clicked on Email") },
                Triple("Account note", fieldValues["note"] ?: "") {
                    accountVM.setShowDialog("note", true)
                }
            )
        )
    }

    if (dialogStates["name"] == true) {
        AccountNameDialog(
            onDismissRequest = {
                accountVM.confirmField("name")
            },
            tempName = tempFieldValues["name"] ?: "",
            onTempNameChange = { accountVM.setTempFieldValue("name", it) },
            onConfirm = {
                accountVM.confirmField("name")
            },
            title = "Enter Account Name",
            label = "Account Name"
        )
    }

    if (dialogStates["note"] == true) {
        AccountNameDialog(
            onDismissRequest = {
                accountVM.confirmField("note")
            },
            tempName = tempFieldValues["note"] ?: "",
            onTempNameChange = { accountVM.setTempFieldValue("note", it) },
            onConfirm = {
                accountVM.confirmField("note")
            },
            title = "Enter Note",
            label = "Note"
        )
    }
}



@Composable
fun AccountInfoCard(items: List<Triple<String, String, () -> Unit>>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(colorScheme.surface)
    ) {
        Column {
            items.forEachIndexed { index, (key, value, onClick) ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onClick() }
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = key, color = colorScheme.onPrimary)
                        Text(text = value, color = TextDisabled)
                    }
                }
                if (index < items.size - 1) {
                    Divider(modifier = Modifier.padding(horizontal = 8.dp))
                }
            }
        }
    }
}