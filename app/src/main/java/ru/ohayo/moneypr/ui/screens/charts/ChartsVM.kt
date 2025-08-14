package ru.ohayo.moneypr.ui.screens.charts


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.domain.use_case.CalendarUseCase
import ru.ohayo.moneypr.repository.ChartsRepository

import ru.ohayo.moneypr.ui.screens.charts.components.data.CategorySummaryFromDb
import ru.ohayo.moneypr.ui.screens.charts.components.data.ChartDataMapper
import ru.ohayo.moneypr.ui.screens.charts.components.data.PeriodType
import ru.ohayo.moneypr.utils.all_charts.donutChart.model.PieChartData
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class ChartsVM @Inject constructor(
    private val repository: ChartsRepository,
    private val calendarUseCase: CalendarUseCase,
    private val chartDataMapper: ChartDataMapper
) : ViewModel() {


    private val calendar = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.DAY_OF_MONTH, 1)
        clear(Calendar.HOUR_OF_DAY)
        clear(Calendar.MINUTE)
        clear(Calendar.SECOND)
        clear(Calendar.MILLISECOND)
    }

    // Убираем дублирование - используем одно состояние
    private val _currentCalendar = MutableStateFlow(calendar.clone() as Calendar)
    val currentCalendar: StateFlow<Calendar> = _currentCalendar

    private val _periodType = MutableStateFlow(PeriodType.MONTH)
    val periodType: StateFlow<PeriodType> = _periodType

    private val _customRange = MutableStateFlow<Pair<Long, Long>?>(null)
    val customRange: StateFlow<Pair<Long, Long>?> = _customRange

    private val _periodLabel = MutableStateFlow("")
    val periodLabel: StateFlow<String> = _periodLabel

    private val _categorySummaries = MutableStateFlow<List<CategorySummaryFromDb>>(emptyList())
    val categorySummaries: StateFlow<List<CategorySummaryFromDb>> = _categorySummaries

    private val _pieChartData = MutableStateFlow<List<PieChartData>>(emptyList())
    val pieChartData: StateFlow<List<PieChartData>> = _pieChartData

    init {
        updatePeriodLabel()

        // Собираем комбинированный Flow для загрузки данных
        viewModelScope.launch {
            combine(_currentCalendar, _periodType, _customRange) { calendar, type, custom ->
                Triple(calendar, type, custom)
            }.collectLatest { (cal, type, custom) ->
                loadCategoryData(cal, type, custom)
            }
        }

        // Обновляем pie chart когда приходят новые данные
        viewModelScope.launch {
            _categorySummaries.collect { summaries ->
                updatePieChartData(summaries)
            }
        }
    }

    private fun loadCategoryData(
        calendar: Calendar,
        type: PeriodType,
        customRange: Pair<Long, Long>?
    ) {
        viewModelScope.launch {
            try {
                val (start, end) = when (type) {
                    PeriodType.YEAR -> calendarUseCase.getYearRange(calendar)
                    PeriodType.MONTH -> calendarUseCase.getMonthRange(calendar)
                    PeriodType.WEEK -> calendarUseCase.getWeekRange(calendar)
                    PeriodType.CUSTOM -> customRange ?: return@launch
                }

                repository.getCategoriesForPeriod(start, end)
                    .catch { e ->
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
        _pieChartData.value = chartDataMapper.toPieChartData(summaries)
    }

    fun setPeriodType(type: PeriodType) {
        _periodType.value = type
        updatePeriodLabel()
    }

    fun setCustomRange(start: Long, end: Long) {
        _customRange.value = Pair(start, end)
        _periodType.value = PeriodType.CUSTOM
        updatePeriodLabel()
    }

    fun nextPeriod() = shiftPeriod(1)
    fun prevPeriod() = shiftPeriod(-1)

    private fun shiftPeriod(direction: Int) {
        when (_periodType.value) {
            PeriodType.YEAR -> shiftCalendar(Calendar.YEAR, direction)
            PeriodType.MONTH -> shiftCalendar(Calendar.MONTH, direction)
            PeriodType.WEEK -> shiftCalendar(Calendar.WEEK_OF_YEAR, direction)
            PeriodType.CUSTOM -> shiftCustomRange(direction)
        }
        updatePeriodLabel()
    }

    private fun shiftCalendar(field: Int, direction: Int) {
        val newCalendar = _currentCalendar.value.clone() as Calendar
        newCalendar.add(field, direction)
        _currentCalendar.value = newCalendar
    }

    private fun shiftCustomRange(direction: Int) {
        _customRange.value?.let { (start, end) ->
            val diff = end - start
            val newStart = start + (diff + 1) * direction
            val newEnd = end + (diff + 1) * direction

            if (newStart > 0) {
                _customRange.value = Pair(newStart, newEnd)
            }
        }
    }

    private fun updatePeriodLabel() {
        _periodLabel.value = when (_periodType.value) {
            PeriodType.YEAR -> calendarUseCase.formatYearLabel(_currentCalendar.value)
            PeriodType.MONTH -> calendarUseCase.formatMonthLabel(_currentCalendar.value)
            PeriodType.WEEK -> calendarUseCase.formatWeekLabel(_currentCalendar.value)
            PeriodType.CUSTOM -> _customRange.value?.let {
                calendarUseCase.formatCustomLabel(it.first, it.second)
            } ?: "Custom"
        }
    }

    private fun toPositive(value: Double) = kotlin.math.abs(value)
}