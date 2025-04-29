package ru.ohayo.moneypr.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.ui.theme.screens.components.DeleteButton
import ru.ohayo.moneypr.viewModel.KeyboardViewModel

@Composable
fun RecordsScreen(viewModel: KeyboardViewModel) {
    var textFieldValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = textFieldValue,
            onValueChange = { newValue ->
                textFieldValue = newValue
            },
            label = { Text("Введите текст") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка "стереть"
        DeleteButton(
            modifier = Modifier.weight(1f),
            onDeleteLast = { viewModel.deleteLast() }
        )
    }
}