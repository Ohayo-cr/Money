package ru.ohayo.moneypr.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.data.repository.CategoryRepository
import ru.ohayo.moneypr.domain.category.Category
import ru.ohayo.moneypr.domain.category.CategoryType
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    // Используем StateFlow для управления состоянием
    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories

    private var tempUpdatedList = listOf<Category>()


    init {
        viewModelScope.launch {
            try {
                // Собираем данные из Flow в StateFlow
                categoryRepository.getAllCategories().collect { categories ->
                    _categories.value = categories.sortedBy { it.order } // Сортируем по полю order
                }
            } catch (e: Exception) {
                // Логируем ошибку или показываем сообщение пользователю
                e.printStackTrace()
            }
        }
    }
    fun moveCategory(fromIndex: Int, toIndex: Int) {
        val currentList = _categories.value.toMutableList()
        val item = currentList.removeAt(fromIndex)
        currentList.add(toIndex, item)

        val updatedList = currentList.mapIndexed { index, category ->
            category.copy(order = index)
        }

        _categories.value = updatedList
        tempUpdatedList = updatedList
    }

    fun saveOrderChanges() {
        viewModelScope.launch {
            categoryRepository.updateCategories(tempUpdatedList)
        }
    }

    // Метод для добавления категории
    fun insertCategory(category: Category) {
        viewModelScope.launch {
            try {
                categoryRepository.insertCategory(category)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Метод для добавления списка категорий
    fun insertAll(categories: List<Category>) {
        viewModelScope.launch {
            try {
                categoryRepository.insertAll(categories)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun filterCategoriesByType(type: CategoryType): List<Category> {
        return _categories.value.filter { it.type == type }
    }
    fun updateOrder(updatedCategories: List<Category>) {
        viewModelScope.launch {
            categoryRepository.updateCategories(updatedCategories)
        }
    }

}