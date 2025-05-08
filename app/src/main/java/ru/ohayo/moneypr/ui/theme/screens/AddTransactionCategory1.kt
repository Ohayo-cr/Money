package ru.ohayo.moneypr.ui.theme.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.ohayo.moneypr.data.data_source.navigation.Screen
import ru.ohayo.moneypr.domain.category.Category
import ru.ohayo.moneypr.domain.category.CategoryType
import ru.ohayo.moneypr.ui.theme.screens.components.ChooseCategory
import ru.ohayo.moneypr.viewModel.CategoryViewModel

@Composable
fun AddTransactionCategory(
    navController: NavController,
    viewModel: CategoryViewModel = hiltViewModel()
) {
    var selectedTab by remember { mutableStateOf(CategoryType.EXPENSE) }

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
            listOf(CategoryType.EXPENSE to "Expense",CategoryType.INCOME to "Income" ).forEach { (type, label) ->
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


        // Filtered categories grid
        val filteredCategories = viewModel.filterCategoriesByType(selectedTab)
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(2.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(filteredCategories) { category ->
                ChooseCategory(
                    iconResId = category.iconResId,
                    backgroundColor = Color(category.color),
                    name = category.name,
                    onClick = {
                        navController.navigate(Screen.AddTransaction.routeWithCategoryId(category.id))
                    }
                )
            }
        }
    }
}


// Расширение для удобного формирования маршрута с параметром
fun Screen.routeWithCategoryId(categoryId: Long): String {
    return "${this.route}?categoryId=$categoryId"
}

