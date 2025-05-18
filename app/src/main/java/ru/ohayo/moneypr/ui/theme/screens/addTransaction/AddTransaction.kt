package ru.ohayo.moneypr.ui.theme.screens.addTransaction


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.ohayo.moneypr.domain.allEntity.CategoryType
import ru.ohayo.moneypr.ui.theme.screens.components.componentsCategory.ChooseCategory
import ru.ohayo.moneypr.viewModel.CategoryViewModel

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun AddTransaction(
    navController: NavController,
    viewModel: CategoryViewModel = hiltViewModel()
) {
    var selectedTab by remember { mutableStateOf(CategoryType.EXPENSE) }
    var showAddTransactionForm by remember { mutableStateOf(false) }
    var selectedCategoryId by remember { mutableStateOf<Long?>(null) }
    // 1. Добавьте LazyListState для управления прокруткой
    val listState = rememberLazyGridState()

    Column(modifier = Modifier.fillMaxSize()) {
        // TabRow for INCOME and EXPENSE
        TabRow(
            selectedTabIndex = selectedTab.ordinal,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab.ordinal])
                )
            }
        ) {
            listOf(
                CategoryType.EXPENSE to "Expense",
                CategoryType.INCOME to "Income"
            ).forEach { (type, label) ->
                Tab(
                    selected = selectedTab == type,
                    onClick = { selectedTab = type },
                    text = {
                        Text(
                            text = label,
                            color = if (selectedTab == type)
                                MaterialTheme.colorScheme.onPrimary
                            else
                                MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = if (selectedTab == type) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            val filteredCategories = viewModel.filterCategoriesByType(selectedTab)

            LazyVerticalGrid(
                state = listState,
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(filteredCategories) { category ->
                    ChooseCategory(
                        iconResId = category.iconResId,
                        backgroundColor = Color(category.color),
                        name = category.name,
                        onClick = {
                            selectedCategoryId = category.id
                            showAddTransactionForm = true
                        },
                        isSelected = selectedCategoryId == category.id
                    )
                }
            }


            val density = LocalDensity.current // <-- Получаем плотность вне LaunchedEffect

            LaunchedEffect(showAddTransactionForm, selectedCategoryId) {
                if (showAddTransactionForm != null) {
                    val index = filteredCategories.indexOfFirst { it.id == selectedCategoryId }
                    if (index != -1) {
                        val itemSizeDp = 100.dp
                        val itemSizePx = with(density) { itemSizeDp.toPx().toInt() } // <-- используем её здесь

                        val visibleHeight = listState.layoutInfo.viewportSize.height
                        val offset = visibleHeight - itemSizePx

                        listState.animateScrollToItem(
                            index = index,
                            scrollOffset = -offset
                        )
                    }
                }
            }
        }
        if (showAddTransactionForm && selectedCategoryId != null) {
            AddTransactionForm(
                categoryId = selectedCategoryId!!.toInt(),
                onBack = {
                    showAddTransactionForm = false
                    selectedCategoryId = null
                },
                onTransactionAdded = {
                }
            )
        }
    }
}



