package ru.ohayo.moneypr.ui.theme.screens.addCategory

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.ohayo.moneypr.R
import ru.ohayo.moneypr.ui.theme.screens.navController.Screen
import ru.ohayo.moneypr.ui.theme.screens.addCategory.components.CategoryHeader
import ru.ohayo.moneypr.ui.theme.screens.addCategory.components.ColorPickerSection
import ru.ohayo.moneypr.ui.theme.screens.addCategory.components.IconGridSection
import ru.ohayo.moneypr.ui.theme.screens.components.componentsCategory.CategoryTabRow
import ru.ohayo.moneypr.viewModel.AddCategoryViewModel
import ru.ohayo.moneypr.viewModel.CategoryViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddCategoryScreen(
    navController: NavHostController,
    addCategoryVM: AddCategoryViewModel,
    categoryVM: CategoryViewModel,
    ) {
    val selectedTab by categoryVM.selectedCategoryType.collectAsState()

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    // Состояния экрана
    var categoryName by remember { mutableStateOf("") }
    var selectedIconResId by remember { mutableIntStateOf(R.drawable.cat__ic_power) }
    var selectedColor by remember { mutableStateOf(Color(0xFF67676B)) }
    val showColorPickerDialog = remember { mutableStateOf(false) }

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
        // Верхняя часть - заголовок и имя
        CategoryHeader(
            selectedIconResId = selectedIconResId,
            categoryName = categoryName,
            onNameChange = { newName ->
                if (newName.length <= 22) categoryName = newName
            },
            selectedColor = selectedColor,
            keyboardController = keyboardController,
            focusManager = focusManager
        )
        Spacer(modifier = Modifier.height(24.dp))
        // Цвет
            ColorPickerSection(
                selectedColor = selectedColor,
                onColorClick = { showColorPickerDialog.value = true },
                showColorPickerDialog = showColorPickerDialog,
                onColorSelected = { color -> selectedColor = color }
            )

        Spacer(modifier = Modifier.height(24.dp))
        // Иконки
        IconGridSection(
            selectedIconResId = selectedIconResId,
            onIconSelected = { id -> selectedIconResId = id }
        )

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