package ru.ohayo.moneypr.domain.use_case

import java.util.Calendar
import javax.inject.Inject

class CalendarUseCase @Inject constructor() {
    fun getMonthRange(calendar: Calendar): Pair<Long, Long> {
        val cal = calendar.clone() as Calendar
        cal.set(Calendar.DAY_OF_MONTH, 1)
        val start = cal.timeInMillis

        cal.add(Calendar.MONTH, 1)
        cal.add(Calendar.DAY_OF_MONTH, -1)
        val end = cal.timeInMillis

        return Pair(start, end)
    }

    fun formatMonthLabel(calendar: Calendar): String {
        val current = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_MONTH, 1)
            clear(Calendar.HOUR_OF_DAY)
            clear(Calendar.MINUTE)
            clear(Calendar.SECOND)
            clear(Calendar.MILLISECOND)
        }

        return if (calendar.get(Calendar.YEAR) == current.get(Calendar.YEAR) &&
            calendar.get(Calendar.MONTH) == current.get(Calendar.MONTH)
        ) {
            "Current month"
        } else {
            val month = calendar.get(Calendar.MONTH) + 1
            val year = calendar.get(Calendar.YEAR)
            "%02d.%d".format(month, year)
        }
    }
}