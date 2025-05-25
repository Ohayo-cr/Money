package ru.ohayo.moneypr.ui.theme.screens.addTransaction



import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.ohayo.moneypr.domain.allEntity.CategoryType
import ru.ohayo.moneypr.ui.theme.screens.components.componentsCategory.CategoryTabRow
import ru.ohayo.moneypr.ui.theme.screens.components.componentsCategory.ChooseCategory
import ru.ohayo.moneypr.ui.theme.screens.navController.Screen
import ru.ohayo.moneypr.viewModel.CategoryViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddTransaction(
    navController: NavController,
    viewModel: CategoryViewModel = hiltViewModel()
) {
    var selectedTab by remember { mutableStateOf(CategoryType.EXPENSE) }
    var showAddTransactionForm by remember { mutableStateOf(false) }
    var selectedCategoryId by remember { mutableStateOf<Long?>(null) }
    val listState = rememberLazyGridState()


    Column(modifier = Modifier.fillMaxSize()) {

        CategoryTabRow(
            selectedType = selectedTab,
            onTypeSelected = { type -> selectedTab = type }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            val filteredCategories = viewModel.filterCategoriesByType(selectedTab)

            LazyVerticalGrid(
                state = listState,
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(top = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                items(filteredCategories) { category ->
                    ChooseCategory(
                        iconItem = category.iconResId,
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

            val density = LocalDensity.current

            LaunchedEffect(showAddTransactionForm, selectedCategoryId) {
                if (showAddTransactionForm != null) {
                    val index = filteredCategories.indexOfFirst { it.id == selectedCategoryId }
                    if (index != -1) {
                        val itemSizeDp = 100.dp
                        val itemSizePx = with(density) { itemSizeDp.toPx().toInt() }

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
                    navController.navigate(Screen.Records.route)
                }
            )
        }
    }
}




