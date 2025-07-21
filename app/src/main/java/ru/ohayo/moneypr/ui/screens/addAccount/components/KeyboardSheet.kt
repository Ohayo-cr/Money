package ru.ohayo.moneypr.ui.screens.addAccount.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.ohayo.moneypr.data.room.account.AccountDbo
import ru.ohayo.moneypr.ui.component.customeKeyboard.UndatedKeyboard
import ru.ohayo.moneypr.ui.screens.addTransaction.KeyboardViewModel
import ru.ohayo.moneypr.utils.formate.NumberFormatter
import ru.ohayo.moneypr.utils.formate.NumberFormatterKeyboard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeyboardSheet(
    keyboardViewModel: KeyboardViewModel = hiltViewModel(),
    onDismiss: () -> Unit,


) {
    val displayText = if (keyboardViewModel.result.isNotEmpty()) {
        NumberFormatterKeyboard.formatWithSpaces(keyboardViewModel.result)
    } else {
        NumberFormatterKeyboard.formatWithSpaces(keyboardViewModel.currentInput.ifEmpty { "0" })
    }
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.primary,
        content = {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)) {
                Text(
                    text = displayText,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 28.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .wrapContentWidth(Alignment.End)
                )

                Spacer(modifier = Modifier.height(12.dp))

                UndatedKeyboard(
                    viewModel = keyboardViewModel,
                    onDateButtonClicked =  onDismiss ,
                    onHideKeyboardClicked =  onDismiss ,
                    onOkClicked =  onDismiss ,
                    dateText = "Hello date",
                )

                Spacer(modifier = Modifier.height(36.dp))
            }
        }
    )
}