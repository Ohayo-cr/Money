package ru.ohayo.moneypr.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.ui.theme.screens.components.CategoryIcons
import ru.ohayo.moneypr.ui.theme.screens.components.ChooseCategory
import ru.ohayo.moneypr.ui.theme.screens.components.DeleteButton
import ru.ohayo.moneypr.viewModel.KeyboardViewModel

@Composable
fun RecordsScreen()
{
    val colorScheme = MaterialTheme.colorScheme
    val colors = remember(colorScheme) {
        // Создаем список с цветом inversePrimary для каждой иконки
        List(CategoryIcons.icons.size) { colorScheme.inversePrimary }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(CategoryIcons.icons) { index, iconResId ->
            ChooseCategory(
                iconResId = iconResId,
                backgroundColor = colors[index],
                onClick = { }
            )
        }
    }
}