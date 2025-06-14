package ru.ohayo.moneypr.ui.theme.screens.addTransaction



import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import ru.ohayo.moneypr.domain.allEntity.CategoryType
import ru.ohayo.moneypr.ui.theme.RedOrange
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

            if (showAddTransactionForm && selectedCategoryId != null) {
                val density = LocalDensity.current

                LaunchedEffect(key1 = selectedCategoryId, key2 = isFirstSelection) {
                    if (isFirstSelection) {
                        delay(500)
                        val index = filteredCategories.indexOfFirst { it.id == selectedCategoryId }
                        if (index != -1) {
                            // Переводим dp в px
                            val itemSizePx = with(density) { 100.dp.toPx().toInt() }
                            val visibleHeight = listState.layoutInfo.viewportSize.height
                            val offset = visibleHeight - itemSizePx

                            // Плавный скролл
                            listState.animateScrollToItem(
                                index = index,
                                scrollOffset = -offset
                            )

                            isFirstSelection = false
                        }
                    }
                }
            }
        }
        if (showAddTransactionForm && selectedCategoryId != null) {
            AddTransactionForm(
                categoryId = selectedCategoryId!!.toInt(),
                onTransactionAdded = {
                    navController.navigate(Screen.Records.route) {
                        popUpTo(0)
                    }
                }
            )
        }
    }
}





