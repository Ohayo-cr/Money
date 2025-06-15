package ru.ohayo.moneypr.ui.theme.screens.addTransaction



import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
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

    var selectedTab by rememberSaveable { mutableStateOf(CategoryType.EXPENSE) }
    var showAddTransactionForm by rememberSaveable { mutableStateOf(false) }
    var selectedCategoryId by rememberSaveable { mutableStateOf<Long?>(null) }
    val listState = rememberLazyGridState()
    var isFirstSelection by rememberSaveable { mutableStateOf(true) }
    val filteredCategories = viewModel.filterCategoriesByType(selectedTab)

    Column(modifier = Modifier.fillMaxSize()) {

        CategoryTabRow(
            selectedType = selectedTab,
            onTypeSelected = { type -> selectedTab = type }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)

        ) {
            val bottomPadding by remember(showAddTransactionForm) {
                derivedStateOf {
                    if (showAddTransactionForm) 500.dp // Высота формы или чуть больше
                    else 0.dp
                }
            }
            LazyVerticalGrid(
                state = listState,
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(
                    top = 20.dp,
                    bottom = bottomPadding
                ),
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
            LaunchedEffect(key1 = selectedCategoryId) {
                if (isFirstSelection && showAddTransactionForm) {
                    val index = filteredCategories.indexOfFirst { it.id == selectedCategoryId }
                    if (index != -1) {

                        val bottomPaddingPx = density.run { bottomPadding.roundToPx() }

                        listState.layoutInfo.visibleItemsInfo.getOrNull(0)?.let { firstItem ->
                            val itemSizePx = firstItem.size.height
                            val visibleHeight = listState.layoutInfo.viewportSize.height

                            val scrollOffset = visibleHeight - bottomPaddingPx + itemSizePx - 20

                            listState.animateScrollToItem(
                                index = index,
                                scrollOffset = -scrollOffset
                            )
                            isFirstSelection = false
                        }
                    }
                }
            }



            if (showAddTransactionForm && selectedCategoryId != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                ) {
                    AddTransactionForm(
                        categoryId = selectedCategoryId!!.toInt(),
                        onTransactionAdded = {
                            navController.navigate(Screen.Records.route) {
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .wrapContentHeight() // Чтобы не занимала всё пространство
                    )
                }
            }
        }
    }
}







