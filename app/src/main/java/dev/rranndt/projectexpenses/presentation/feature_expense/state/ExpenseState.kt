package dev.rranndt.projectexpenses.presentation.feature_expense.state

import dev.rranndt.projectexpenses.core.utils.Filter
import dev.rranndt.projectexpenses.domain.model.Expense

data class ExpenseState(
    val filter: Filter = Filter.Daily,
    val isFilterOpened: Boolean = false,
    val sumTotal: Double = 0.0,
    val expenses: List<Expense> = emptyList(),
)
