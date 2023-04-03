package dev.rranndt.projectexpenses.presentation.feature_add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rranndt.projectexpenses.core.utils.OutputFlowFilter
import dev.rranndt.projectexpenses.domain.model.Expense
import dev.rranndt.projectexpenses.domain.usecase.ExpenseUseCase
import dev.rranndt.projectexpenses.presentation.feature_add.event.AddExpenseEvent
import dev.rranndt.projectexpenses.presentation.feature_add.state.AddExpenseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddExpenseViewModel @Inject constructor(
    private val useCase: ExpenseUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddExpenseState())
    val uiState: StateFlow<AddExpenseState> = _uiState

    init {
        getCategories()
    }

    fun getCategories() =
        useCase.getCategories()
            .onEach { data ->
                _uiState.value = uiState.value.copy(
                    categories = data
                )
            }

    fun onEvent(event: AddExpenseEvent) {
        when (event) {
            is AddExpenseEvent.SetAmount -> {
                var parsed = event.amount.toDoubleOrNull()
                if (event.amount.isEmpty()) {
                    parsed = 0.0
                }

                if (parsed != null) {
                    _uiState.update { currentState ->
                        currentState.copy(
                            amount = event.amount
                        )
                    }
                }
            }
            is AddExpenseEvent.SetOutputFlow -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        outputFlow = event.outputFlow
                    )
                }
            }
            is AddExpenseEvent.SetDate -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        date = event.date
                    )
                }
            }
            is AddExpenseEvent.SetDescription -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        description = event.description
                    )
                }
            }
            is AddExpenseEvent.SetCategory -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        category = event.category
                    )
                }
            }
            AddExpenseEvent.InsertExpense -> {
                if (_uiState.value.category != null) {
                    viewModelScope.launch {
                        val now = LocalDateTime.now()
                        useCase.insertExpense(
                            Expense(
                                amount = _uiState.value.amount.toDouble(),
                                outputFlow = _uiState.value.outputFlow.name,
                                dateValue = _uiState.value.date.atTime(
                                    now.hour,
                                    now.minute,
                                    now.hour
                                ).toString(),
                                description = _uiState.value.description,
                                category = _uiState.value.category
                            )
                        )

                        _uiState.update { currentState ->
                            currentState.copy(
                                amount = "",
                                outputFlow = OutputFlowFilter.NONE,
                                date = LocalDate.now(),
                                description = "",
                                category = null,
                                categories = null
                            )
                        }
                    }
                }
            }
        }
    }
}