package dev.rranndt.projectexpenses.presentation.feature_expense.state

import dev.rranndt.projectexpenses.core.utils.OutputFlowFilter
import dev.rranndt.projectexpenses.domain.model.Expense

data class ExpenseState(
    val outputFlow: OutputFlowFilter = OutputFlowFilter.DAILY,
    val sumTotal: Double = 0.0,
    val expenses: List<Expense> = emptyList(),
)
