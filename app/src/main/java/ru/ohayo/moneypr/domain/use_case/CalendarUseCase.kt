package ru.ohayo.moneypr.domain.use_case

import java.util.Calendar
import javax.inject.Inject

class CalendarUseCase @Inject constructor() {


    fun getYearRange(calendar: Calendar): Pair<Long, Long> {
        val cal = calendar.clone() as Calendar
        cal.set(Calendar.DAY_OF_YEAR, 1)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        val start = cal.timeInMillis

        cal.add(Calendar.YEAR, 1)
        cal.add(Calendar.MILLISECOND, -1) // конец последнего дня года
        val end = cal.timeInMillis

        return Pair(start, end)
    }

    fun getMonthRange(calendar: Calendar): Pair<Long, Long> {
        val cal = calendar.clone() as Calendar
        cal.set(Calendar.DAY_OF_MONTH, 1)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        val start = cal.timeInMillis

        cal.add(Calendar.MONTH, 1)
        cal.add(Calendar.MILLISECOND, -1) // конец последнего дня месяца
        val end = cal.timeInMillis

        return Pair(start, end)
    }

    fun getWeekRange(calendar: Calendar): Pair<Long, Long> {
        val cal = calendar.clone() as Calendar
        cal.set(Calendar.DAY_OF_WEEK, cal.firstDayOfWeek)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        val start = cal.timeInMillis

        cal.add(Calendar.WEEK_OF_YEAR, 1)
        cal.add(Calendar.MILLISECOND, -1) // конец последнего дня недели
        val end = cal.timeInMillis

        return Pair(start, end)
    }

fun formatYearLabel(calendar: Calendar): String {
    return "${calendar.get(Calendar.YEAR)}"
}

fun formatMonthLabel(calendar: Calendar): String {
    val month = calendar.get(Calendar.MONTH) + 1
    val year = calendar.get(Calendar.YEAR)
    return "%02d.%d".format(month, year)
}

fun formatWeekLabel(calendar: Calendar): String {
    val start = calendar.clone() as Calendar
    start.set(Calendar.DAY_OF_WEEK, start.firstDayOfWeek)

    val end = start.clone() as Calendar
    end.add(Calendar.DAY_OF_MONTH, 6)

    return "${formatDate(start)} - ${formatDate(end)}"
}

fun formatCustomLabel(start: Long, end: Long): String {
    val startDate = Calendar.getInstance().apply { timeInMillis = start }
    val endDate = Calendar.getInstance().apply { timeInMillis = end }
    return "${formatDate(startDate)} - ${formatDate(endDate)}"
}

private fun formatDate(calendar: Calendar): String {
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.get(Calendar.MONTH) + 1
    return "%02d.%02d".format(day, month)
}
}