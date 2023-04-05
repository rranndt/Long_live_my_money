package dev.rranndt.projectexpenses.presentation.feature_statistic.state

import dev.rranndt.projectexpenses.core.utils.Filter
import dev.rranndt.projectexpenses.domain.model.Expense
import java.time.LocalDateTime

data class StatisticState(
    val filter: Filter = Filter.Weekly,
    val isFilterOpened: Boolean = false,
    val expenses: List<Expense> = listOf(),
    val startDate: LocalDateTime = LocalDateTime.now(),
    val endDate: LocalDateTime = LocalDateTime.now(),
    val avgPerDay: Double = 0.0,
    val totalInRange: Double = 0.0,
)
