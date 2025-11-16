package ru.ohayo.moneypr.ui.screens.charts.components



import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import ru.ohayo.moneypr.utils.all_charts.donutChart.DonutChart
import ru.ohayo.moneypr.utils.all_charts.donutChart.model.PieChartData


@SuppressLint("SuspiciousIndentation")
@Composable
fun DonutChartSample(pieChartData: List<PieChartData>) {

    val totalSum = pieChartData.sumOf { it.data }
    val formattedTotalSum = formatNumberForDisplay(totalSum)
        DonutChart(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(.3f),
            pieChartData = pieChartData,
            centerTitle = formattedTotalSum,
            centerTitleStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimary),
            descriptionStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimary),
            outerCircularColor = MaterialTheme.colorScheme.onPrimary,
            innerCircularColor = MaterialTheme.colorScheme.onPrimary
        )

}
private fun formatNumberForDisplay(number: Double): String {
    return when {
        number >= 10_000_000 -> {  // 10 миллионов и больше
            val millions = number / 1_000_000
            if (millions == millions.toLong().toDouble()) {
                "${millions.toLong()}M"
            } else {
                "%.1fM".format(millions)
            }
        }
        else -> number.toInt().toString()  // Оставляем как есть
    }
}