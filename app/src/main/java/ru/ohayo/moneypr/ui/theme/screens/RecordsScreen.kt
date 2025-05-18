package ru.ohayo.moneypr.ui.theme.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordsScreen(){
val coroutineScope = rememberCoroutineScope()
var sheetState by remember { mutableStateOf(false) }

Column(
modifier = Modifier
.fillMaxSize()
.padding(16.dp)
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items((1..6).toList()) { index ->
            Button(
                onClick = { sheetState = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Кнопка $index")
            }
        }
    }
}

if (sheetState) {
    ModalBottomSheet(
        onDismissRequest = { sheetState = false },
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.medium
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            Text(
                text = repeatText("бокс", 10),
                style = TextStyle(fontSize = MaterialTheme.typography.titleMedium.fontSize)
            )
        }
    }
}
}

fun repeatText(text: String, count: Int): String {
    return List(count) { text }.joinToString(" ")
}



