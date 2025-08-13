package ru.ohayo.moneypr.ui.screens.charts.components

import androidx.compose.ui.graphics.Color
import ru.ohayo.moneypr.utils.all_charts.donutChart.model.PieChartData

class ChartDataMapper {
    fun toPieChartData(summaries: List<CategorySummaryFromDb>): List<PieChartData> {
        return summaries.map { summary ->
            PieChartData(
                data = kotlin.math.abs(summary.totalAmount),
                color = Color(summary.color),
                partName = summary.categoryName
            )
        }
    }
}