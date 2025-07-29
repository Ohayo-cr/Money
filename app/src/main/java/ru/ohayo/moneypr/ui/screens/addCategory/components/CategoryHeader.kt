package ru.ohayo.moneypr.ui.screens.addCategory.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.ui.component.categoryIcon.CategoryIcon


@Composable
fun CategoryHeader(
    selectedIconResId: Int,
    categoryName: String,
    onNameChange: (String) -> Unit,
    selectedColor: Color,
    keyboardController: SoftwareKeyboardController?,
    focusManager: FocusManager
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        CategoryIcon(
            iconResId = selectedIconResId,
            backgroundColor = selectedColor.takeIf { it != Color.Transparent }
                ?: MaterialTheme.colorScheme.primary,
            onClick = {}
        )

        Spacer(modifier = Modifier.width(16.dp))

        OutlinedTextField(
            value = categoryName,
            onValueChange = onNameChange,
            label = { Text("Name category") },
            modifier = Modifier.weight(1f),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            )
        )
    }
}