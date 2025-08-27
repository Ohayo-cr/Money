package ru.ohayo.moneypr.ui.screens.categoryList

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ru.ohayo.moneypr.ui.navController.Screen
import ru.ohayo.moneypr.ui.component.categoryIcon.CategoryIcon
import ru.ohayo.moneypr.ui.component.customeTab.CategoryTabRow
import ru.ohayo.moneypr.ui.component.customeButton.FullWidthButton
import ru.ohayo.moneypr.ui.component.spacers.Spacers
import ru.ohayo.moneypr.ui.theme.TextDisabled
import ru.ohayo.moneypr.utils.formate.NumberFormatter
import sh.calvin.reorderable.ReorderableItem
import sh.calvin.reorderable.rememberReorderableLazyListState


@Composable
fun CategoryList(categoryVM: CategoryViewModel = hiltViewModel(),
                 navController: NavHostController) {
    val color =  colorScheme.onPrimary
    val selectedTab by categoryVM.selectedCategoryType.collectAsState()
    val filteredCategories by categoryVM.filteredCategories.collectAsState()
    val categoryStatsMap by categoryVM.categoryStatsMap.collectAsState() // Добавляем статистику

    val savedScrollPosition by categoryVM.scrollPosition.collectAsState()
    val lazyListState = rememberLazyListState(savedScrollPosition)

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemIndex }
            .collect { index -> categoryVM.saveScrollPosition(index) }
    }

    val reorderableLazyListState = rememberReorderableLazyListState(lazyListState)
    { from, to ->
        categoryVM.moveCategory(from.index, to.index, selectedTab)
    }
    val shouldScroll by categoryVM.shouldScrollToTop.collectAsState()

    if (shouldScroll) {
        LaunchedEffect(Unit) {
            lazyListState.scrollToItem(0)
            categoryVM.setShouldScrollToTop(false) // Сбрасываем флаг
        }
    }


    Column(modifier = Modifier.fillMaxSize()) {

        CategoryTabRow(
            selectedType = selectedTab,
            onTypeSelected = { newType ->
                categoryVM.setSelectedCategoryType(newType) // Обновляем тип в ViewModel
                CategoryViewModel.CategoryTypeHolder.currentType = newType
            }
        )



       Spacers.Micro2()

        LazyColumn(state = lazyListState,
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
    ) {
        items(
            items = filteredCategories,
            key = { it.id }
        ) { category ->
            val stats = categoryStatsMap[category.categoryName]
            val transactionCount = stats?.transactionCount ?: 0
            val totalAmount = stats?.totalAmount ?: 0.0
            ReorderableItem(
                reorderableLazyListState, key = category.id,
                ) { dragging ->
                val scale = animateFloatAsState(if (dragging) 1.05f else 1.0f, label = "")
                val elevation = animateDpAsState(if (dragging) 16.dp else 0.dp, label = "")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .scale(scale.value)
                        .shadow(elevation.value)
                        .longPressDraggableHandle()

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
                            Column {
                                Text(
                                    text = category.categoryName,
                                    modifier = Modifier.padding(start = 8.dp),
                                    color = color
                                )
                                // Добавляем информацию о транзакциях
                                if (transactionCount > 0) {
                                    Text(
                                        text = "$transactionCount transactions, sum ${NumberFormatter.format(totalAmount)}",
                                        modifier = Modifier.padding(start = 8.dp),
                                        color = TextDisabled,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                        Text(
                            text = "Edit",
                            color = color,
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .clickable {
                                    // Переход к экрану редактирования с ID категории
                                    navController.navigate("${Screen.AddCategoryWithId}/${category.id}")
                                }
                        )
                    }
                }
            }
        }
    }
        FullWidthButton(
        text = "Create a new category",
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


