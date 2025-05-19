package ru.ohayo.moneypr.ui.theme.screens.addCategory.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ColorPickerSection(
    selectedColor: Color,
    onColorClick: () -> Unit,
    showColorPickerDialog: MutableState<Boolean>,
    onColorSelected: (Color) -> Unit,
    selectedIconResId: Int?
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column {
            Text(text = "Текущий цвет:", style = MaterialTheme.typography.bodyLarge)
            Text(
                text = "${ColorNames[selectedColor] ?: "Неизвестный"}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(selectedColor)
                .border(1.5.dp, Color.Black, RoundedCornerShape(8.dp))
                .clickable { onColorClick() }
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Нажми чтобы изменить", fontSize = 16.sp)
        }
    }

    if (showColorPickerDialog.value) {
        FullScreenCustomDialog(
            onDismissRequest = { showColorPickerDialog.value = false },
            title = "Выберите цвет категории",
            message = "Нажмите на нужный цвет",
            confirmText = "Сохранить",
            onConfirmClick = {},
            onColorSelected = { color ->
                onColorSelected(color)
                showColorPickerDialog.value = false
            },
            selectedIconResId = selectedIconResId
        )
    }
}