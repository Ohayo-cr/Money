package ru.ohayo.moneypr.ui.screens.addCategory.components

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
import ru.ohayo.moneypr.models.categoryIcon.AllCategoryIcons
import ru.ohayo.moneypr.ui.component.categoryIcon.ChooseCategory


@Composable
fun IconGridSection(
    selectedIconResId: Int,
    onIconSelected: (Int) -> Unit
) {
    Text(
        text = "Выберите иконку",
        color =  MaterialTheme.colorScheme.onPrimary,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(start = 16.dp,top = 16.dp)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        itemsIndexed(AllCategoryIcons.allIcons) { _, item ->
            ChooseCategory(
                iconItem = item,
                backgroundColor = Color(0xFF67676B),
                onClick = {
                    onIconSelected(item)
                },
                size = 80.dp,
                isSelected = selectedIconResId == item
            )
        }
    }
}
