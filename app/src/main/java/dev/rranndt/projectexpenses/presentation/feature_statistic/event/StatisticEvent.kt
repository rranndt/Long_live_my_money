package dev.rranndt.projectexpenses.presentation.feature_statistic.event

import dev.rranndt.projectexpenses.core.utils.Filter

sealed class StatisticEvent {
    object OpenFilterMenu : StatisticEvent()
    object CloseFilterMenu : StatisticEvent()
    object GetExpenses: StatisticEvent()
    data class SetStatistic(val filter: Filter) : StatisticEvent()
}
