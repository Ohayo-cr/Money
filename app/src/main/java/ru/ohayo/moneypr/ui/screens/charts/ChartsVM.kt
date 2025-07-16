package ru.ohayo.moneypr.ui.screens.charts


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.data.repository.TransactionRepository
import ru.ohayo.moneypr.ui.screens.charts.components.CategorySummaryFromDb
import javax.inject.Inject

@HiltViewModel
class ChartsVM @Inject constructor(
    private val repository: TransactionRepository,
) : ViewModel() {

    private val _categorySummaries = MutableStateFlow<List<CategorySummaryFromDb>>(emptyList())
    val categorySummaries: StateFlow<List<CategorySummaryFromDb>> = _categorySummaries

    private fun loadTopCategories() {
        viewModelScope.launch {
            try {
                val result = repository.getTopCategoriesForCurrentMonth()
                _categorySummaries.value = result
            } catch (e: Exception) {
            }
        }
    }
    init {
        loadTopCategories()
    }
    fun refreshData() {
        loadTopCategories()
    }
}