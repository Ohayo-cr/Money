package ru.ohayo.moneypr.viewModel

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.data.data_source.transaction.AppDatabase
import ru.ohayo.moneypr.data.repository.AddCategoryRepository
import ru.ohayo.moneypr.domain.category.Category
import ru.ohayo.moneypr.domain.category.CategoryType
import javax.inject.Inject

@HiltViewModel
class AddCategoryViewModel @Inject constructor(
    private val repository: AddCategoryRepository
) : ViewModel() {

    fun addCategoryAndGenerateOrder(
        type: CategoryType,
        name: String,
        iconResId: Int,
        color: Color
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
        }
    }


    // Альтернатива через Flow (если хочешь использовать в Compose UI с collectAsState())
    fun getNextOrderFlow(type: CategoryType): Flow<Int> = flow {
        val order = repository.getNextOrder(type)
        emit(order)
    }.flowOn(Dispatchers.IO)
}