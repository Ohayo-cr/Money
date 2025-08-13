package ru.ohayo.moneypr.utils.all_charts.donutChart.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.utils.all_charts.baseComponents.ChartDescription
import ru.ohayo.moneypr.utils.all_charts.donutChart.model.PieChartData

@Composable
internal fun PieChartDescriptionRight(
    pieChartData: List<PieChartData>,
    descriptionStyle: TextStyle = TextStyle.Default,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {

        items(pieChartData) { details ->
            ChartDescription(
                chartColor = details.color,
                chartName = details.partName,
                descriptionStyle = descriptionStyle,
            )
        }
    }
}