package ru.ohayo.moneypr.viewModel


import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow

import ru.ohayo.moneypr.data.repository.TransactionRepository
import ru.ohayo.moneypr.ui.theme.screens.charts.components.CategorySummary

import javax.inject.Inject

@HiltViewModel
class ChartsVM @Inject constructor(
    private val repository: TransactionRepository,
) : ViewModel() {
    val categorySummaries: Flow<List<CategorySummary>> = repository.getCategorySummariesByMonth()
}