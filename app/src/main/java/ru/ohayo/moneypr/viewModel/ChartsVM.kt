package ru.ohayo.moneypr.viewModel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

import ru.ohayo.moneypr.data.repository.TransactionRepository
import ru.ohayo.moneypr.ui.theme.screens.charts.components.CategorySummary

import javax.inject.Inject

@HiltViewModel
class ChartsVM @Inject constructor(
    private val repository: TransactionRepository,
) : ViewModel() {

    private val _categorySummaries = MutableStateFlow<List<CategorySummary>>(emptyList())
    val categorySummaries: StateFlow<List<CategorySummary>> = _categorySummaries

    private fun loadTopCategories() {
        viewModelScope.launch {
            try {
                Log.d("ChartsVM", "Запрашиваем категории за текущий месяц...")
                val result = repository.getTopCategoriesForCurrentMonth()
                Log.d("ChartsVM", "Получены данные: $result")
                _categorySummaries.value = result
            } catch (e: Exception) {
                Log.e("ChartsVM", "Ошибка при загрузке категорий за текущий месяц", e)
            }
        }
    }

    // Используем её в init и в refreshData
    init {
        loadTopCategories()
    }

    fun refreshData() {
        loadTopCategories()
    }
}