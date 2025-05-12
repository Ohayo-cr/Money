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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.ohayo.moneypr.R
import ru.ohayo.moneypr.data.data_source.navigation.Screen
import ru.ohayo.moneypr.ui.theme.screens.components.CategoryIcons
import ru.ohayo.moneypr.ui.theme.screens.components.CategoryTabRow
import ru.ohayo.moneypr.ui.theme.screens.components.ChooseCategory
import ru.ohayo.moneypr.ui.theme.screens.components.colorcategory.ColorNames
import ru.ohayo.moneypr.ui.theme.screens.components.colorcategory.FullScreenCustomDialog
import ru.ohayo.moneypr.viewModel.AddCategoryViewModel
import ru.ohayo.moneypr.viewModel.CategoryViewModel

@Composable
fun AddCategoryScreen(
    navController: NavHostController,
    addCategoryVM: AddCategoryViewModel,
    categoryVM: CategoryViewModel,
    ) {
    val selectedTab by categoryVM.selectedCategoryType.collectAsState()

    // Состояния экрана
    var categoryName by remember { mutableStateOf("") }
    var selectedIconResId by remember { mutableStateOf(R.drawable.cat__ic_power) } // Или ваша иконка по умолчанию
    var selectedColor by remember { mutableStateOf(Color(0xFFA9A9A9)) }
    val showColorPickerDialog = remember { mutableStateOf(false) }


    if (showColorPickerDialog.value) {
        FullScreenCustomDialog(
            onDismissRequest = { showColorPickerDialog.value = false },
            title = "Выберите цвет категории",
            message = "Нажмите на нужный цвет",
            confirmText = "Сохранить",
            onConfirmClick = {},
            onColorSelected = { color ->
                selectedColor = color
                showColorPickerDialog.value = false
            }
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {

        CategoryTabRow(
            selectedType = selectedTab,
            onTypeSelected = { newType ->
                categoryVM.setSelectedCategoryType(newType) // Обновляем тип в ViewModel
                CategoryViewModel.CategoryTypeHolder.currentType = newType
            }
        )
        Divider()
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .background(
                        color = selectedColor.takeIf { it != Color.Transparent }
                            ?: colorScheme.primary,
                        shape = CircleShape
                    )
                    .border(1.dp, Color.Black, CircleShape)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = selectedIconResId),
                    contentDescription = "Selected category icon",
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            OutlinedTextField(
                value = categoryName,
                onValueChange = { newText ->
                    if (newText.length <= 22) {
                        categoryName = newText
                    }
                },
                label = { Text("Название") },
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        // Выбор цвета
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column {

             Text (text = "Текущий цвет:", style = MaterialTheme.typography.bodyLarge)
                Text(
                    text = "${ColorNames[selectedColor] ?: "Неизвестный"}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding( top = 8.dp)
                )
        }
            Spacer(modifier = Modifier.width(16.dp))

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(selectedColor)
                    .border(1.5.dp, Color.Black, RoundedCornerShape(8.dp))
                    .clickable {
                        showColorPickerDialog.value = true
                    }
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {

                    Text(
                        text = "Нажми чтобы изменить",
                        fontSize = 16.sp
                    )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Сетка иконок
        Text(text = "Выберите иконку", style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp))

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
                    backgroundColor = Color(0xFFA9A9A9),
                    onClick = {
                        selectedIconResId = iconResId
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (categoryName.isNotBlank()) {
                    addCategoryVM.addCategoryAndGenerateOrder(
                        type = selectedTab,
                        name = categoryName,
                        iconResId = selectedIconResId,
                        selectedColor
                    )
                    navController.navigate(Screen.Categories.route) {
                        popUpTo(Screen.Categories.route) {
                            inclusive = true
                        }
                    }
                }
            },
            modifier = Modifier.align(Alignment.End).padding(bottom = 16.dp)
        ) {
            Text(text = "Сохранить")
        }
        BackHandler {
            navController.navigate(Screen.Categories.route) {
                popUpTo(Screen.Categories.route) {
                    inclusive = true
                }
            }
        }
    }
}