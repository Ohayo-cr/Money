package ru.ohayo.moneypr.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.data.repository.CategoryRepository
import ru.ohayo.moneypr.domain.allEntity.Category
import ru.ohayo.moneypr.domain.allEntity.CategoryType
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    // Используем StateFlow для управления состоянием
    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories
    object CategoryTypeHolder {
        var currentType: CategoryType = CategoryType.EXPENSE
    }
    private var tempUpdatedList = listOf<Category>()
    private val changedTypes = mutableSetOf<CategoryType>()
    // Храним выбранный тип
    private val _selectedCategoryType = MutableStateFlow(CategoryTypeHolder.currentType)
    val selectedCategoryType: StateFlow<CategoryType> = _selectedCategoryType
    init {
        viewModelScope.launch {
            try {
                categoryRepository.getAllCategories().collect { categories ->
                    _categories.value = categories
                    tempUpdatedList = _categories.value

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun moveCategory(fromIndex: Int, toIndex: Int, type: CategoryType) {
        val currentList = _categories.value
        val (currentTypeCategories, otherCategories) = currentList.partition { it.type == type }

        val updatedCurrent = currentTypeCategories.toMutableList().apply {
            val item = removeAt(fromIndex)
            add(toIndex, item)
        }.mapIndexed { index, category ->
            category.copy(order = index)
        }

        _categories.value = updatedCurrent + otherCategories
        tempUpdatedList = _categories.value
        changedTypes.add(type)
    }
    fun setSelectedCategoryType(type: CategoryType) {
        _selectedCategoryType.value = type
    }

    fun saveOrderChanges() {
        viewModelScope.launch {
            changedTypes.forEach { type ->
                val categoriesToUpdate = tempUpdatedList.filter { it.type == type }
                categoryRepository.updateCategories(categoriesToUpdate)
            }
            // Сброс после сохранения
            changedTypes.clear()
            tempUpdatedList = emptyList()
        }
    }


    fun filterCategoriesByType(type: CategoryType): List<Category> {
        return _categories.value.filter { it.type == type }
    }
}