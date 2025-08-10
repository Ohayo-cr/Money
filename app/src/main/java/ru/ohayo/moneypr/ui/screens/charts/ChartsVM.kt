package ru.ohayo.moneypr.ui.screens.charts


import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.repository.ChartsRepository

import ru.ohayo.moneypr.ui.screens.charts.components.CategorySummaryFromDb
import ru.ohayo.moneypr.utils.all_charts.donutChart.model.PieChartData
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class ChartsVM @Inject constructor(
    private val repository: ChartsRepository,
) : ViewModel() {

    private val calendar = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.DAY_OF_MONTH, 1)
        clear(Calendar.HOUR_OF_DAY)
        clear(Calendar.MINUTE)
        clear(Calendar.SECOND)
        clear(Calendar.MILLISECOND)
    }

    private val _currentMonth = MutableStateFlow(calendar.clone() as Calendar)
    val currentMonth: StateFlow<Calendar> = _currentMonth

    private val _monthLabel = MutableStateFlow("")
    val monthLabel: StateFlow<String> = _monthLabel

    private val _categorySummaries = MutableStateFlow<List<CategorySummaryFromDb>>(emptyList())
    val categorySummaries: StateFlow<List<CategorySummaryFromDb>> = _categorySummaries

    private val _pieChartData = MutableStateFlow<List<PieChartData>>(emptyList())
    val pieChartData: StateFlow<List<PieChartData>> = _pieChartData

    init {
        updateMonthLabel()

        // Собираем Flow из репозитория
        viewModelScope.launch {
            _currentMonth.collectLatest { cal ->
                loadCategoryData(cal)
            }
        }

        // Обновляем pie chart когда приходят новые данные
        viewModelScope.launch {
            _categorySummaries.collect { summaries ->
                updatePieChartData(summaries)
            }
        }
    }



    private fun loadCategoryData(calendar: Calendar) {
        viewModelScope.launch {
            try {
                val (start, end) = getMonthRange(calendar)
                // Собираем Flow из репозитория
                repository.getCategoriesForPeriod(start, end)
                    .catch { e ->
                        // Обработка ошибок
                        Log.e("ChartsVM", "Error loading categories", e)
                    }
                    .collectLatest { categories ->
                        _categorySummaries.value = categories
                    }
            } catch (e: Exception) {
                Log.e("ChartsVM", "Error loading categories", e)
            }
        }
    }
    private fun updatePieChartData(summaries: List<CategorySummaryFromDb>) {
        val chartData = summaries.map { summary ->
            PieChartData(
                data = toPositive(summary.totalAmount),
                color = Color(summary.color),
                partName = summary.categoryName
            )
        }
        _pieChartData.value = chartData
    }
    fun nextMonth() {
        _currentMonth.value = _currentMonth.value.apply { add(Calendar.MONTH, 1) }.clone() as Calendar
        updateMonthLabel()
        // loadTopCategories() - теперь не нужен, так как collectLatest в init сам обновит данные
    }

    fun prevMonth() {
        _currentMonth.value = _currentMonth.value.apply { add(Calendar.MONTH, -1) }.clone() as Calendar
        updateMonthLabel()
        // loadTopCategories() - теперь не нужен, так как collectLatest в init сам обновит данные
    }




    private fun updateMonthLabel() {
        val cal = _currentMonth.value
        val current = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_MONTH, 1)
            clear(Calendar.HOUR_OF_DAY)
            clear(Calendar.MINUTE)
            clear(Calendar.SECOND)
            clear(Calendar.MILLISECOND)
        }

        // Сравниваем год и месяц (по первому числу)
        if (cal.get(Calendar.YEAR) == current.get(Calendar.YEAR) &&
            cal.get(Calendar.MONTH) == current.get(Calendar.MONTH)
        ) {
            _monthLabel.value = "Current month"
        } else {
            val month = cal.get(Calendar.MONTH) + 1
            val year = cal.get(Calendar.YEAR)
            _monthLabel.value = "%02d.%d".format(month, year)
        }
    }


    private fun getMonthRange(calendar: Calendar): Pair<Long, Long> {
        val cal = calendar.clone() as Calendar
        cal.set(Calendar.DAY_OF_MONTH, 1)
        val start = cal.timeInMillis

        cal.add(Calendar.MONTH, 1)
        cal.add(Calendar.DAY_OF_MONTH, -1)
        val end = cal.timeInMillis

        return Pair(start, end)
    }
    private fun toPositive(value: Double) = kotlin.math.abs(value)
}