package dev.rranndt.projectexpenses.presentation.feature_expense.event

import dev.rranndt.projectexpenses.core.utils.Filter

sealed class ExpenseEvent {
    object OpenFilterMenu: ExpenseEvent()
    object CloseFilterMenu: ExpenseEvent()
    object GetExpenses : ExpenseEvent()
    data class SetExpenses(val outputFlow: Filter) : ExpenseEvent()
}
