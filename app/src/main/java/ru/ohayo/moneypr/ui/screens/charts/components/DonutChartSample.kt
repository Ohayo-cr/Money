package ru.ohayo.moneypr.ui.screens.charts.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import ru.ohayo.moneypr.utils.all_charts.donutChart.DonutChart
import ru.ohayo.moneypr.utils.all_charts.donutChart.model.PieChartData

@Composable
fun DonutChartSample() {

    val testPieChartData: List<PieChartData> = listOf(
        PieChartData(
            partName = "part A",
            data = 500.0,
            color = Color(0xFF0B666A),
        ),
        PieChartData(
            partName = "Part B",
            data = 700.0,
            color = Color(0xFF35A29F),
        ),
        PieChartData(
            partName = "Part C",
            data = 500.0,
            color = Color(0xFF97FEED),
        ),
        PieChartData(
            partName = "Part D",
            data = 100.0,
            color = Color(0xFF071952),
        ),
    )

    DonutChart(
        modifier = Modifier.fillMaxSize(),
        pieChartData = testPieChartData,
        centerTitle = "Orders",
        centerTitleStyle = TextStyle(color = Color(0xFF071952)),
        outerCircularColor = Color.LightGray,
        innerCircularColor = Color.Gray,
        ratioLineColor = Color.LightGray,
    )
}