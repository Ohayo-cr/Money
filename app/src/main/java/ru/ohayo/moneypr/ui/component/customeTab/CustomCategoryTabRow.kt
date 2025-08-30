package ru.ohayo.moneypr.ui.component.customeTab

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.data.room.category.CategoryType
import ru.ohayo.moneypr.ui.component.others.NoRippleInteractionSource


@Composable
fun CategoryTabRow(
    selectedType: CategoryType,
    onTypeSelected: (CategoryType) -> Unit,
    availableTypes: List<CategoryType> = CategoryType.entries // По умолчанию все
) {
    val colorScheme = MaterialTheme.colorScheme

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 8.dp,
                    bottomEnd = 8.dp
                )
            )
            .background(colorScheme.primary)
            .padding(2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxWidth()
        ) {
            TabRow(
                selectedTabIndex = availableTypes.indexOf(selectedType).coerceAtLeast(0),
                modifier = Modifier.fillMaxWidth(),
                containerColor = colorScheme.primary,
                indicator = {},
                divider = {}
            ) {
                availableTypes.forEach { type ->
                    Tab(
                        selected = selectedType == type,
                        onClick = { onTypeSelected(type) },
                        modifier = Modifier
                            .padding(4.dp)
                            .border(
                                width = 1.dp,
                                color = colorScheme.onPrimary.copy(alpha = .5f),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .background(
                                color = if (selectedType == type) colorScheme.inversePrimary else Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        interactionSource = remember { NoRippleInteractionSource }
                    ) {
                        Text(
                            text = CategoryTypeDisplayNameMap[type] ?: type.name,
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
