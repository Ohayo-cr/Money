package ru.ohayo.moneypr.ui.theme.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable
import ru.ohayo.moneypr.data.data_source.navigation.Screen
import ru.ohayo.moneypr.ui.theme.screens.components.CategoryIcon
import ru.ohayo.moneypr.ui.theme.screens.components.CategoryTabRow
import ru.ohayo.moneypr.ui.theme.screens.components.FullWidthButton
import ru.ohayo.moneypr.viewModel.CategoryViewModel


@Composable
fun CategoryList(categoryVM: CategoryViewModel, navController: NavHostController) {

    val categories = categoryVM.categories.collectAsState(initial = emptyList()).value
    val selectedTab by categoryVM.selectedCategoryType.collectAsState()
    val filteredCategories = categories.filter { it.type == selectedTab }
    val state = rememberReorderableLazyListState(
        onMove = { from, to ->
            categoryVM.moveCategory(from.index, to.index, selectedTab)
        }
    )

    Column(modifier = Modifier.fillMaxSize()) {

        CategoryTabRow(
            selectedType = selectedTab,
            onTypeSelected = { newType ->
                categoryVM.setSelectedCategoryType(newType) // Обновляем тип в ViewModel
                CategoryViewModel.CategoryTypeHolder.currentType = newType
            }
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
                val scale = animateFloatAsState(if (dragging) 1.05f else 1.0f, label = "")
                val elevation = animateDpAsState(if (dragging) 16.dp else 0.dp, label = "")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .scale(scale.value)
                        .shadow(elevation.value)
                        .detectReorderAfterLongPress(state)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 10.dp, vertical = 2.dp)
                            .background(color = colorScheme.background),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            CategoryIcon(
                                iconResId = category.iconResId,
                                backgroundColor = Color(category.color),
                                onClick = {}
                            )
                            Text(
                                text = category.name,
                                modifier = Modifier.padding(start = 8.dp) // Небольшой отступ от иконки
                            )
                        }
                        Text(
                            text = category.type.toString(),
                            modifier = Modifier.padding(end = 4.dp)
                        )
                    }
                }
            }
        }
    }
        FullWidthButton(
        text = "Создать категорию",
        onClick = {
            navController.navigate(Screen.AddCategory.route)
        }
    )
}
    BackHandler {
        categoryVM.saveOrderChanges()
        navController.popBackStack()
    }

        DisposableEffect(Unit) {
            onDispose {
                categoryVM.saveOrderChanges()
            }
        }
    }


