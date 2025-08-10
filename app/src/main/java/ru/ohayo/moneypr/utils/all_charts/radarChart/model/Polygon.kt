package ru.ohayo.moneypr.utils.all_charts.radarChart.model

data class Polygon(
    val style: PolygonStyle,
    val values: List<Double>,
    val unit: String,
)