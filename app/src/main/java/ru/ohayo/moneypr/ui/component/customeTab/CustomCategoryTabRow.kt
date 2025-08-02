package ru.ohayo.moneypr.ui.component.customeTab

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.data.room.category.CategoryType
import ru.ohayo.moneypr.ui.component.others.NoRippleInteractionSource


@Composable
fun CategoryTabRow(
    selectedType: CategoryType,
    onTypeSelected: (CategoryType) -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(colorScheme.surface)
            .padding(2.dp)
    ) {
        // Внутренний Row для выравнивания Tab'ов по низу
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxSize()
        ) {
            TabRow(
                selectedTabIndex = selectedType.ordinal,
                modifier = Modifier.fillMaxWidth(),
                containerColor = colorScheme.surface,
                indicator = {},
                divider = {}
            ) {
                CategoryType.entries.forEach { type ->
                    Tab(
                        selected = selectedType == type,
                        onClick = { onTypeSelected(type) },
                        modifier = Modifier
                            .padding(4.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .background(
                                color = if (selectedType == type) colorScheme.inversePrimary else Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        interactionSource = remember { NoRippleInteractionSource }
                    ) {
                        Text(
                            text = type.name,
                            color = if (selectedType == type) colorScheme.onPrimary else colorScheme.onSurface,
                            style = MaterialTheme.typography.titleSmall,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                        )
                    }
                }
            }
        }
    }
}
