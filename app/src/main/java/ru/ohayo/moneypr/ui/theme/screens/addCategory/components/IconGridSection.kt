package ru.ohayo.moneypr.ui.theme.screens.addCategory.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.ui.theme.screens.components.componentsCategory.AllCategoryIcons
import ru.ohayo.moneypr.ui.theme.screens.components.componentsCategory.ChooseCategory

@Composable
fun IconGridSection(
    selectedIconResId: Int,
    onIconSelected: (Int) -> Unit
) {
    Text(
        text = "Выберите иконку",
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(16.dp)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(AllCategoryIcons.icons) { _, iconResId ->
            ChooseCategory(
                iconResId = iconResId,
                backgroundColor = Color(0xFF67676B),
                onClick = {
                    onIconSelected(iconResId)
                },
                isSelected = selectedIconResId == iconResId // ← Вот тут передаем состояние
            )
        }
    }
}
