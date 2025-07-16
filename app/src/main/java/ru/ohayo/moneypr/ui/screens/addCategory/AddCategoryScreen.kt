package ru.ohayo.moneypr.ui.screens.addCategory

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ru.ohayo.moneypr.R
import ru.ohayo.moneypr.ui.theme.ErorColor
import ru.ohayo.moneypr.ui.component.categoryIcon.IconGridSection
import ru.ohayo.moneypr.ui.component.others.FullWidthButton
import ru.ohayo.moneypr.ui.component.others.TextSizeButton
import ru.ohayo.moneypr.ui.component.categoryIcon.CategoryTabRow
import ru.ohayo.moneypr.ui.navController.Screen
import ru.ohayo.moneypr.ui.screens.addCategory.components.CategoryHeader
import ru.ohayo.moneypr.ui.screens.addCategory.components.ColorPickerSection
import ru.ohayo.moneypr.ui.screens.categoryList.CategoryViewModel

@Composable
fun AddCategoryScreen(
    navController: NavHostController,
    addCategoryVM: AddCategoryViewModel,
    categoryVM: CategoryViewModel = hiltViewModel(),
    isEditMode: Boolean = false,
    categoryId: Long? = null
) {

    val selectedTab by categoryVM.selectedCategoryType.collectAsState()

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    // Состояния экрана
    var categoryName by remember { mutableStateOf("") }
    var selectedIconResId by remember { mutableIntStateOf(R.drawable.cat__ic_power) }
    var selectedColor by remember { mutableStateOf(Color(0xFF67676B)) }
    val showColorPickerDialog = remember { mutableStateOf(false) }

    if (isEditMode && categoryId != null) {
        LaunchedEffect(categoryId) {
            val category = addCategoryVM.getCategoryByIdUpdate(categoryId)
            if (category != null) {
                categoryName = category.categoryName
                selectedIconResId = category.iconResId
                selectedColor = Color(category.color)
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(1f)

        ) {
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
                onColorSelected = { color -> selectedColor = color },
                selectedIconResId = selectedIconResId,
            )
            Spacer(modifier = Modifier.height(14.dp))


            // Иконки
            IconGridSection(
                selectedIconResId = selectedIconResId,
                onIconSelected = { id -> selectedIconResId = id }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            if (isEditMode) {
                TextSizeButton(
                    text = "Удалить",
                    onClick = {},
                    containerColor = ErorColor,
                )
            }
            FullWidthButton(text = "Сохранить",
                onClick = {
                    if (categoryName.isNotBlank()) {
                        if (isEditMode && categoryId != null) {
                            // Обновление категории
                            addCategoryVM.updateCategory(
                                id = categoryId,
                                name = categoryName,
                                iconResId = selectedIconResId,
                                color = selectedColor.toArgb().toLong(),
                                type = selectedTab,
                            )
                            navController.popBackStack()
                        } else {

                            addCategoryVM.addCategoryAndGenerateOrder(
                                type = selectedTab,
                                name = categoryName,
                                iconResId = selectedIconResId,
                                color = selectedColor,
                                onComplete = {
                                    categoryVM.setShouldScrollToTop(true)
                                    navController.navigate(Screen.Categories.route) {
                                        popUpTo(Screen.Categories.route) {
                                            inclusive = true
                                        }
                                    }
                                }
                            )

                        }
                    }
                }
            )
        }
    }

    BackHandler {
        navController.popBackStack()
    }
}
