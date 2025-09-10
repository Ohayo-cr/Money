package ru.ohayo.moneypr.ui.component.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ConfirmDialog(
    onConfirm: () -> Unit,
    onDismissRequest: (() -> Unit)? = null,
    title: String? = null,
    text: String? = null,
    confirmButtonText: String = "ОК",
    dismissButtonText: String = "Отмена",
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = {
            onDismissRequest?.invoke()
        },
        title = title?.let { t ->
            {
                Text(
                    text = t,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        text = text?.let { txt ->
            {
                Text(
                    text = txt,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(
                    text = confirmButtonText,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismissRequest?.invoke()
            }) {
                Text(
                    text = dismissButtonText,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        },
        modifier = modifier
    )
}