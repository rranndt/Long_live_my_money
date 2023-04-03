package dev.rranndt.projectexpenses.core.utils

import android.content.Context
import dev.rranndt.projectexpenses.R
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

fun LocalDate.formatDay(
    context: Context,
): String {
    val today = LocalDate.now()
    val yesterday = LocalDate.now().minusDays(1)

    return when {
        this.isEqual(today) -> UniversalText.Resource(R.string.today).asString(context)
        this.isEqual(yesterday) -> UniversalText.Resource(R.string.yesterday).asString(context)
        this.year == today.year -> this.format(DateTimeFormatter.ofPattern("EEE, d MMM"))
        else -> this.format(DateTimeFormatter.ofPattern("dd LLL yyyy"))
    }
}

data class DateRangeData(
    val start: LocalDate,
    val end: LocalDate,
    val daysInRange: Int,
)

fun calculateDateRange(
    outputFlow: OutputFlowFilter,
    page: Int,
): DateRangeData {
    val today = LocalDate.now()
    lateinit var startDate: LocalDate
    lateinit var endDate: LocalDate
    var daysInRange = 7

    when (outputFlow) {
        OutputFlowFilter.DAILY -> {
            startDate = LocalDate.now().minusDays(page.toLong())
            endDate = startDate
        }
        OutputFlowFilter.WEEKLY -> {
            startDate = LocalDate.now().minusDays(today.dayOfWeek.value.toLong() - 1)
                .minusDays((page * 7).toLong())
            endDate = startDate.plusDays(6)
            daysInRange = 7
        }
        OutputFlowFilter.MONTHLY -> {
            startDate = LocalDate.of(today.year, today.month, 1).minusMonths(page.toLong())
            val numberOfDays = YearMonth.of(startDate.year, startDate.month).lengthOfMonth()
            endDate = startDate.plusDays(numberOfDays.toLong())
            daysInRange = numberOfDays
        }
        OutputFlowFilter.YEARLY -> {
            startDate = LocalDate.of(today.year, 1, 1)
            endDate = LocalDate.of(today.year, 12, 31)
            daysInRange = 365
        }
        else -> Unit
    }

    return DateRangeData(
        start = startDate,
        end = endDate,
        daysInRange = daysInRange
    )
}