package ru.ohayo.moneypr.viewModel

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.data.repository.AddCategoryRepository
import ru.ohayo.moneypr.domain.allEntity.Category
import ru.ohayo.moneypr.domain.allEntity.CategoryType
import javax.inject.Inject

@HiltViewModel
class AddCategoryViewModel @Inject constructor(
    private val repository: AddCategoryRepository
) : ViewModel() {
    suspend fun getCategoryByIdUpdate(id: Long): Category? {
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
            val newCategory = Category(
                id = 0,
                type = type,
                name = name,
                iconResId = iconResId,
                color = color.toArgb().toLong(),
                order = order
            )
            repository.addCategory(newCategory)
            onComplete()
        }
    }
    //


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
                    Category(
                        id = id,
                        name = name,
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