package ru.ohayo.moneypr.ui.screens.charts


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.ohayo.moneypr.ui.screens.charts.components.CategorySummaryFromDb
import ru.ohayo.moneypr.ui.component.categoryIcon.CategoryIcon
import ru.ohayo.moneypr.models.formate.NumberFormatter


@Composable
fun ChartsScreen(viewModel: ChartsVM = hiltViewModel()) {

    val categorySummaries  by viewModel.categorySummaries.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.refreshData()
    }

    Column(modifier = Modifier
        .padding(start = 8.dp)
        .fillMaxSize(), verticalArrangement = Arrangement.Top ) {
        Row {
            Text(text = "Период: this mount", fontSize = 18.sp,  color = MaterialTheme.colorScheme.onPrimary)
            Spacer(modifier = Modifier.width(4.dp))
            // Здесь можно добавить выпадающий список выбора месяца
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(categorySummaries) { category ->
                CategoryItem(category = category)
            }
        }
    }

}
@Composable
fun CategoryItem(category: CategorySummaryFromDb) {
val color =  MaterialTheme.colorScheme.onPrimary
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(category.color).copy(alpha = 0.01f),
                        Color(category.color).copy(alpha = 0.1f),
                        Color(category.color).copy(alpha = 0.4f),
                        Color(category.color).copy(alpha = 0.5f),
                        Color(category.color).copy(alpha = 0.5f),
                        Color(category.color).copy(alpha = 0.3f),
                        Color(category.color).copy(alpha = 0.1f)
                    ),

                ),
                shape = RoundedCornerShape(
                    topStart = 10.dp,
                    bottomStart = 10.dp
                )
            ),
                verticalAlignment = Alignment.CenterVertically
    ) {
        CategoryIcon(iconResId = category.iconResId,
            backgroundColor = Color(category.color),
            modifier = Modifier.size(48.dp) )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = category.categoryName,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                color = color
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = category.percentage.let {
                    "%.2f%%".format(it)
                },
                color = color
            )
        }


        Text(
            text = NumberFormatter.format(category.totalAmount),
            modifier = Modifier.padding(end = 8.dp),  color = color)

    }
}



