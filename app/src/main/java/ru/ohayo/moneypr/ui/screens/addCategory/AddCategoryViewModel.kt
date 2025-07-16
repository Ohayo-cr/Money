package ru.ohayo.moneypr.ui.screens.addCategory

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.repository.AddCategoryRepository
import ru.ohayo.moneypr.data.room.category.CategoryDbo
import ru.ohayo.moneypr.data.room.category.CategoryType
import javax.inject.Inject

@HiltViewModel
class AddCategoryViewModel @Inject constructor(
    private val repository: AddCategoryRepository
) : ViewModel() {
    suspend fun getCategoryByIdUpdate(id: Long): CategoryDbo? {
        return repository.getCategoryByIdUpdate(id)
    }


    fun addCategoryAndGenerateOrder(
        type: CategoryType,
        name: String,
        iconResId: Int,
        color: Color,
        onComplete: () -> Unit
    ) {
        viewModelScope.launch {
            val order = repository.getNextOrder(type)
            val newCategoryDbo = CategoryDbo(
                id = 0,
                type = type,
                categoryName = name,
                iconResId = iconResId,
                color = color.toArgb().toLong(),
                order = order
            )
            repository.addCategory(newCategoryDbo)
            onComplete()
        }
    }



    fun updateCategory(
        id: Long,
        name: String,
        iconResId: Int,
        color: Long,
        type: CategoryType
    ) {
        viewModelScope.launch {
            val existingCategory = getCategoryByIdUpdate(id)
            if (existingCategory != null) {
                repository.updateCategory(
                    CategoryDbo(
                        id = id,
                        categoryName = name,
                        iconResId = iconResId,
                        color = color,
                        type = type,
                        order = existingCategory.order // сохраняем старое значение order
                    )
                )
            }
        }
    }
}