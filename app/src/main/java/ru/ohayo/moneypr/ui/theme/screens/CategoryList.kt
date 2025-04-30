package ru.ohayo.moneypr.ui.theme.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable
import ru.ohayo.moneypr.viewModel.CategoryViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryList(viewModel: CategoryViewModel) {
    val categories = viewModel.categories.collectAsState(initial = emptyList()).value

    var isDragging by remember { mutableStateOf(false) }

    val state = rememberReorderableLazyListState(
        onMove = { from, to ->
            viewModel.moveCategory(from.index, to.index)
        }
    )

    // Обрабатываем конец перетаскивания
    LaunchedEffect(isDragging) {
        if (!isDragging) {
            viewModel.saveOrderChanges()
        }
    }

    LazyColumn(
        state = state.listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .reorderable(state)
            .detectReorderAfterLongPress(state)
    ) {
        items(
            items = categories,
            key = { it.id },

            ) { category ->
            ReorderableItem(
                state, key = category.id,
                modifier = Modifier.animateItemPlacement(),
                defaultDraggingModifier = Modifier.animateItemPlacement()
            ) { dragging ->
                isDragging = dragging
                val scale = animateFloatAsState(if (dragging) 1.1f else 1.0f)
                val elevation = if (dragging) 4.dp else 0.dp
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(vertical = 5.dp)
                        .scale(scale.value)
                        .shadow(elevation)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.LightGray),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Круглая иконка с фоном и обводкой
                        Box(
                            modifier = Modifier
                                .size(35.dp)
                                .background(
                                    color = Color(category.color),
                                    shape = CircleShape
                                )
                                .border(
                                    width = 1.dp,
                                    color = Color.DarkGray,
                                    shape = CircleShape
                                )
                                .padding(5.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = category.iconResId),
                                contentDescription = "Category icon",
                                tint = Color.DarkGray
                            )
                        }
                        Text(text = category.name)
                        Text(text = category.type.toString())
                    }
                }
            }
        }
    }
}