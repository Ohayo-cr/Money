package ru.ohayo.moneypr.ui.component.spacers

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StandardDivider() {
    Divider(
        modifier = Modifier
            .fillMaxWidth().padding(horizontal = 8.dp),
        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
    )
}