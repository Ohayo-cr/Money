package ru.ohayo.moneypr.ui.screens.categoryList


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.repository.CategoryRepository
import ru.ohayo.moneypr.data.room.category.CategoryDbo
import ru.ohayo.moneypr.data.room.category.CategoryType
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    // Используем StateFlow для управления состоянием
    private val _categories = MutableStateFlow<List<CategoryDbo>>(emptyList())
    val categories: StateFlow<List<CategoryDbo>> = _categories
    // Текущая категория при редактировании
    private val _categoryDbo = MutableStateFlow<CategoryDbo?>(null)
    val categoryDbo: StateFlow<CategoryDbo?> = _categoryDbo
    object CategoryTypeHolder {
        var currentType: CategoryType = CategoryType.Expense
    }
    private var tempUpdatedList = listOf<CategoryDbo>()
    private val changedTypes = mutableSetOf<CategoryType>()
    // Храним выбранный тип
    private val _selectedCategoryType = MutableStateFlow(CategoryTypeHolder.currentType)
    val selectedCategoryType: StateFlow<CategoryType> = _selectedCategoryType
    // Список категорий, отфильтрованный по выбранному типу
    val filteredCategories: StateFlow<List<CategoryDbo>> = selectedCategoryType
        .combine(categories) { type, all ->
            all.filter { it.type == type }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _scrollPosition = MutableStateFlow(0)
    val scrollPosition: StateFlow<Int> = _scrollPosition.asStateFlow()

    fun saveScrollPosition(index: Int) {
        _scrollPosition.value = index
    }
    private val _shouldScrollToTop = MutableStateFlow(false)
    val shouldScrollToTop: StateFlow<Boolean> = _shouldScrollToTop

    fun setShouldScrollToTop(value: Boolean) {
        _shouldScrollToTop.value = value
    }

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
                categoryRepository.updateOrderByType(categoriesToUpdate)
            }
            // Сброс после сохранения
            changedTypes.clear()
            tempUpdatedList = emptyList()
        }
    }
    fun loadCategory(id: Long) {
        viewModelScope.launch {
            _categoryDbo.value = categoryRepository.getCategoryById(id).first()
        }
    }


    fun filterCategoriesByType(type: CategoryType): List<CategoryDbo> {
        return _categories.value.filter { it.type == type }
    }


}