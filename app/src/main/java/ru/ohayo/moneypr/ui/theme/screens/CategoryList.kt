package ru.ohayo.moneypr.ui.theme.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable
import ru.ohayo.moneypr.data.data_source.navigation.Screen
import ru.ohayo.moneypr.domain.category.CategoryType
import ru.ohayo.moneypr.ui.theme.screens.components.ButtonCategory
import ru.ohayo.moneypr.ui.theme.screens.components.CategoryTabRow
import ru.ohayo.moneypr.viewModel.CategoryViewModel


@Composable
fun CategoryList(viewModel: CategoryViewModel, navController: NavHostController) {


    val categories = viewModel.categories.collectAsState(initial = emptyList()).value
    var selectedTab by remember { mutableStateOf(CategoryType.EXPENSE) }
    val filteredCategories = categories.filter { it.type == selectedTab }

    val state = rememberReorderableLazyListState(
        onMove = { from, to ->
            viewModel.moveCategory(from.index, to.index, selectedTab)
        }
    )

    Column(modifier = Modifier.fillMaxSize()) {

        // 🔄 Используем наш кастомный таб
        CategoryTabRow(
            selectedType = selectedTab,
            onTypeSelected = { selectedTab = it }
        )

        Divider()
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
        state = state.listState,
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .reorderable(state)

    ) {
        items(
            items = filteredCategories,
            key = { it.id }

        ) { category ->
            ReorderableItem(
                state, key = category.id,

                ) { dragging ->
                val scale = animateFloatAsState(if (dragging) 1.1f else 1.0f, label = "")
                val elevation = animateDpAsState(if (dragging) 16.dp else 0.dp, label = "")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .scale(scale.value)
                        .height(60.dp)
                        .shadow(elevation.value)
                        .detectReorderAfterLongPress(state)

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 17.dp)
                            .background(color = colorScheme.background),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(58.dp)
                                .background(
                                    color = Color(category.color),
                                    shape = CircleShape
                                )
                                .border(
                                    width = 1.dp,
                                    color = Color.Black,
                                    shape = CircleShape
                                )
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = category.iconResId),
                                contentDescription = "Category icon",
                                tint = Color.Black
                            )
                        }
                        Text(text = category.name)
                        Text(text = category.type.toString())
                    }
                }
            }
        }
    }
    ButtonCategory(
        text = "Создать категорию",
        onClick = {
            navController.navigate("${Screen.AddCategory.route}/${selectedTab}")
        }
    )
}
    BackHandler {
        viewModel.saveOrderChanges()
        navController.popBackStack()
    }

        DisposableEffect(Unit) {
            onDispose {
                viewModel.saveOrderChanges()
            }
        }
    }


