package ru.ohayo.moneypr.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.viewModel.CategoryViewModel

@Composable
fun CategoryList(viewModel: CategoryViewModel) {
    val categories = viewModel.categories.collectAsState(initial = emptyList()).value

    Column(modifier = Modifier.fillMaxSize().padding(5.dp)) {
        categories.forEach { category ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .background(color = Color.LightGray),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Круглая иконка с фоном и обводкой
                Box(
                    modifier = Modifier
                        .size(35.dp) // Размер круга
                        .background(
                            color = Color(category.color), // Цвет фона круга
                            shape = androidx.compose.foundation.shape.CircleShape // Форма круга
                        )
                        .border(
                            width = 1.dp, // Ширина обводки
                            color = Color.DarkGray, // Цвет обводки
                            shape = androidx.compose.foundation.shape.CircleShape // Форма обводки
                        )
                        .padding(5.dp) // Отступ внутри круга для иконки
                ) {
                    Icon(
                        painter = painterResource(id = category.iconResId),
                        contentDescription = "Category icon",
                        tint = Color.DarkGray // Цвет иконки
                    )
                }
                Text(text = category.name)
                Text(text = category.type.toString())
            }
        }
    }
}