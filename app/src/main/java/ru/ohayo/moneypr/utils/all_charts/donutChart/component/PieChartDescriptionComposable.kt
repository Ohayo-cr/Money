package ru.ohayo.moneypr.utils.all_charts.donutChart.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.utils.all_charts.baseComponents.ChartDescription
import ru.ohayo.moneypr.utils.all_charts.donutChart.model.PieChartData

@Composable
internal fun PieChartDescriptionComposable(
    pieChartData: List<PieChartData>,
    descriptionStyle: TextStyle = TextStyle.Default,
    modifier: Modifier = Modifier
) {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 16.dp),
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