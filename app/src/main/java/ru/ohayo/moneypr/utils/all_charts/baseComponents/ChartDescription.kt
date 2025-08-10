package ru.ohayo.moneypr.utils.all_charts.baseComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
internal fun ChartDescription(
    chartColor: Color,
    chartName: String,
    descriptionStyle: TextStyle,
) {
    val truncatedName = if (chartName.length > 12) {
        chartName.take(12) + "..."
    } else {
        chartName
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(color = chartColor)
                .size(10.dp)
        )
        Text(
            text = truncatedName,
            modifier = Modifier.padding(start = 10.dp),
            style = descriptionStyle,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}