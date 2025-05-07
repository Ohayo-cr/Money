package ru.ohayo.moneypr.ui.theme.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.ohayo.moneypr.R
import ru.ohayo.moneypr.domain.category.CategoryType
import ru.ohayo.moneypr.ui.theme.screens.components.CategoryIcons
import ru.ohayo.moneypr.ui.theme.screens.components.ChooseCategory
import ru.ohayo.moneypr.viewModel.AddCategoryViewModel

@Composable
fun AddCategoryScreen(
    navController: NavHostController,
    viewModel: AddCategoryViewModel
) {
    val colorScheme = MaterialTheme.colorScheme

    // Состояния экрана
    var selectedType by remember { mutableStateOf(CategoryType.EXPENSE) }
    var categoryName by remember { mutableStateOf("") }
    var selectedIconResId by remember { mutableStateOf(R.drawable.cat__ic_power) } // Или ваша иконка по умолчанию

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        // Переключатель доход/расход
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CategoryType.entries.forEach { type ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .clickable { selectedType = type }
                        .padding(8.dp)
                ) {
                    RadioButton(
                        selected = selectedType == type,
                        onClick = { selectedType = type }
                    )
                    Text(text = type.name, style = MaterialTheme.typography.titleSmall)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Показ выбранной иконки + текстовое поле
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .background(colorScheme.primary, CircleShape)
                    .border(1.dp, Color.Black, CircleShape)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = selectedIconResId),
                    contentDescription = "Selected category icon",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            OutlinedTextField(
                value = categoryName,
                onValueChange = { categoryName = it },
                label = { Text("Введите название") },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Сетка иконок
        Text(text = "Выберите иконку", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(CategoryIcons.icons) { index, iconResId ->
                ChooseCategory(
                    iconResId = iconResId,
                    backgroundColor = colorScheme.inversePrimary,
                    onClick = {
                        selectedIconResId = iconResId
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

// Кнопка сохранения
        Button(
            onClick = {
                if (categoryName.isNotBlank()) {
                    viewModel.addCategoryAndGenerateOrder(
                        type = selectedType,
                        name = categoryName,
                        iconResId = selectedIconResId
                    )
                    navController.popBackStack()
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Сохранить")
        }

        // Обработчик кнопки "Назад" с сохранением
        BackHandler {
            navController.popBackStack()
        }
    }
}