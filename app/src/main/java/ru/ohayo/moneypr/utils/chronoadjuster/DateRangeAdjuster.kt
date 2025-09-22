package ru.ohayo.moneypr.utils.chronoadjuster

import androidx.compose.runtime.Composable
import java.time.DayOfWeek
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjuster
import java.time.temporal.TemporalAdjusters


enum class DateRangeAdjuster(
    val display: @Composable () -> String,
    val chronoUnit: ChronoUnit,
    val temporalAdjusterToStart: TemporalAdjuster,
    val temporalAdjusterToEnd: TemporalAdjuster,
) {
    WEEK(
        { "This Week" },
        ChronoUnit.WEEKS,
        TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY),
        TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY),
    ),
    MONTH(
        { "This Month" },
        ChronoUnit.MONTHS,
        TemporalAdjusters.firstDayOfMonth(),
        TemporalAdjusters.lastDayOfMonth(),
    ),
    YEAR(
        { "This Year" },
        ChronoUnit.YEARS,
        TemporalAdjusters.firstDayOfYear(),
        TemporalAdjusters.lastDayOfYear(),
    ),
}
