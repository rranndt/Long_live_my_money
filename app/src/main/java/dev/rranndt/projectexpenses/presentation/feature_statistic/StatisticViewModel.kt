package dev.rranndt.projectexpenses.presentation.feature_statistic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rranndt.projectexpenses.core.utils.Filter
import dev.rranndt.projectexpenses.core.utils.calculateDateRange
import dev.rranndt.projectexpenses.domain.usecase.ExpenseUseCase
import dev.rranndt.projectexpenses.presentation.feature_statistic.event.StatisticEvent
import dev.rranndt.projectexpenses.presentation.feature_statistic.state.StatisticState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class StatisticViewModel @Inject constructor(
    private val useCase: ExpenseUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(StatisticState())
    val uiState: StateFlow<StatisticState> = _uiState.asStateFlow()


    init {
        onEvent(StatisticEvent.SetStatistic(Filter.Weekly))
        onEvent(StatisticEvent.GetExpenses)
    }

    fun onEvent(event: StatisticEvent) {
        when (event) {
            StatisticEvent.CloseFilterMenu ->
                _uiState.update { currentState ->
                    currentState.copy(
                        isFilterOpened = false
                    )
                }
            StatisticEvent.OpenFilterMenu -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        isFilterOpened = true
                    )
                }
            }
            StatisticEvent.GetExpenses -> {
                useCase.getExpenses().onEach {
                    _uiState.value = uiState.value.copy(
                        expenses = it
                    )
                }
            }
            is StatisticEvent.SetStatistic -> {
                viewModelScope.launch {
                    useCase.getExpenses().collect {
                        val (startDate, endDate, daysInRange) = calculateDateRange(
                            filter = event.filter,
                            page = 0
                        )

                        val filterExpenses = it.filter { expense ->
                            (expense.date.toLocalDate()
                                .isAfter(startDate) && expense.date.toLocalDate()
                                .isBefore(endDate)) || expense.date.toLocalDate()
                                .isEqual(startDate) || expense.date.toLocalDate().isEqual(endDate)
                        }

                        val totalExpenseAmount = filterExpenses.sumOf { expense -> expense.amount }
                        val avgPerDay: Double = totalExpenseAmount / daysInRange

                        _uiState.update { currentState ->
                            currentState.copy(
                                filter = event.filter,
                                expenses = filterExpenses,
                                startDate = LocalDateTime.of(startDate, LocalTime.MIN),
                                endDate = LocalDateTime.of(endDate, LocalTime.MAX),
                                avgPerDay = avgPerDay,
                                totalInRange = totalExpenseAmount
                            )
                        }
                    }
                }
            }
        }
    }
}