package ru.ohayo.moneypr.ui.screens.charts.components


import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
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
            data = 300.0,
            color = Color(0xFF0B666A),
        ),
        PieChartData(
            partName = "Part B",
            data = 5000.0,
            color = Color(0xFF35A29F),
        ),
        PieChartData(
            partName = "Part C",
            data = 150.0,
            color = Color(0xFF97FEED),
        ),
        PieChartData(
            partName = "Part DPart DPart DPart D",
            data = 250.0,
            color = Color(0xFF071952),
        ),
    )



        val totalSum = testPieChartData.sumOf { it.data }
        DonutChart(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(.3f),
            pieChartData = testPieChartData,
            centerTitle = totalSum.toString(),
            centerTitleStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimary),
            descriptionStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimary),
            outerCircularColor = Color.LightGray,
            innerCircularColor = Color.Gray,
        )

}