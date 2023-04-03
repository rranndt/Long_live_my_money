package dev.rranndt.projectexpenses.presentation.feature_expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rranndt.projectexpenses.core.utils.OutputFlowFilter
import dev.rranndt.projectexpenses.core.utils.calculateDateRange
import dev.rranndt.projectexpenses.domain.usecase.ExpenseUseCase
import dev.rranndt.projectexpenses.presentation.feature_expense.event.ExpenseEvent
import dev.rranndt.projectexpenses.presentation.feature_expense.state.ExpenseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val useCase: ExpenseUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ExpenseState())
    val uiState: StateFlow<ExpenseState> = _uiState

    init {
        onEvent(ExpenseEvent.SetOutputFlow(OutputFlowFilter.DAILY))
        onEvent(ExpenseEvent.GetExpenses)
    }

    fun onEvent(event: ExpenseEvent) {
        when (event) {
            is ExpenseEvent.SetOutputFlow -> {
                viewModelScope.launch {
                    useCase.getExpenses().collect {
                        val (startDate, endDate) = calculateDateRange(event.outputFlow, 0)

                        val filterExpenses = it.filter { expense ->
                            (expense.date.toLocalDate()
                                .isAfter(startDate) && expense.date.toLocalDate()
                                .isBefore(endDate)) || expense.date.toLocalDate()
                                .isEqual(startDate) || expense.date.toLocalDate().isEqual(endDate)
                        }

                        val sumTotal = filterExpenses.sumOf { amount -> amount.amount }

                        _uiState.update { currentState ->
                            currentState.copy(
                                outputFlow = event.outputFlow,
                                sumTotal = sumTotal,
                                expenses = filterExpenses
                            )
                        }
                    }

                }
            }
            ExpenseEvent.GetExpenses -> {
                useCase.getExpenses().onEach {
                    _uiState.value = uiState.value.copy(
                        expenses = it
                    )
                }
            }
        }
    }
}