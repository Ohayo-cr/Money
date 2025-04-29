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
import ru.ohayo.moneypr.viewModel.CategoryViewModel

@Composable
fun AddTransactionCategory(
    navController: NavController,
    viewModel: CategoryViewModel = hiltViewModel()
) {
    var selectedTab by remember { mutableStateOf(CategoryType.INCOME) }

    Column(modifier = Modifier.fillMaxSize()) {
        // TabRow for INCOME and EXPENSE
        TabRow(
            selectedTabIndex = if (selectedTab == CategoryType.INCOME) 0 else 1,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.tabIndicatorOffset(tabPositions[if (selectedTab == CategoryType.INCOME) 0 else 1])
                )
            }
        ) {
            Tab(
                selected = selectedTab == CategoryType.INCOME,
                onClick = { selectedTab = CategoryType.INCOME },
                text = {
                    Text(
                        text = "Income",
                        color = if (selectedTab == CategoryType.INCOME)
                            MaterialTheme.colorScheme.onSecondary
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = if (selectedTab == CategoryType.INCOME) FontWeight.Bold else FontWeight.Normal
                    )
                }
            )
            Tab(
                selected = selectedTab == CategoryType.EXPENSE,
                onClick = { selectedTab = CategoryType.EXPENSE },
                text = {
                    Text(
                        text = "Expense",
                        color = if (selectedTab == CategoryType.EXPENSE)
                            MaterialTheme.colorScheme.onSecondary
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = if (selectedTab == CategoryType.EXPENSE) FontWeight.Bold else FontWeight.Normal
                    )
                }
            )
        }

        // Filtered categories grid
        val filteredCategories = viewModel.filterCategoriesByType(selectedTab)
        LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filteredCategories) { category ->
                CategoryItem(
                    category = category,
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

@Composable
fun CategoryItem(category: Category, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(64.dp)
            .clickable(onClick = onClick)
            .padding(4.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = Color(category.color),
                    shape = androidx.compose.foundation.shape.CircleShape
                )
                .border(
                    width = 1.dp,
                    color = Color.DarkGray,
                    shape = androidx.compose.foundation.shape.CircleShape
                )
                .padding(8.dp)
        ) {
            Icon(
                painter = painterResource(id = category.iconResId.toInt()),
                contentDescription = "Category icon",
                tint = Color.DarkGray
            )
        }
        Text(
            text = category.name,
            modifier = Modifier.padding(top = 4.dp),
            maxLines = 1
        )
    }
}