package ru.ohayo.moneypr.ui.theme.screens.addCategory.components

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.R
import ru.ohayo.moneypr.ui.theme.screens.components.componentsCategory.CategoryColors
import ru.ohayo.moneypr.ui.theme.screens.components.componentsCategory.ChooseCategory

@Composable
fun ColorPickerGrid(onColorSelected: (Color) -> Unit,
                    selectedIconResId: Int?) {
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

            ChooseCategory(
                iconItem = selectedIconResId ?: R.drawable.cat__ic_power,
                backgroundColor = color,
                name = name,
                onClick = { onColorSelected(color) },
            )
        }
    }
}