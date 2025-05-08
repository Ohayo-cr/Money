package ru.ohayo.moneypr.ui.theme.screens.components.colorcategory

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ColorPickerGrid(onColorSelected: (Color) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(CategoryColors.colors.size) { index ->
            val color = CategoryColors.colors[index]
            val name = ColorNames[color] ?: "Unknown"

            ColorTile(
                color = color,
                name = name,
                onClick = { onColorSelected(color) }
            )
        }
    }
}