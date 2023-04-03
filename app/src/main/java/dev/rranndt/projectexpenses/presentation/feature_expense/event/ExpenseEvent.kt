package dev.rranndt.projectexpenses.presentation.feature_expense.event

import dev.rranndt.projectexpenses.core.utils.OutputFlowFilter

sealed class ExpenseEvent {
    data class SetOutputFlow(val outputFlow: OutputFlowFilter) : ExpenseEvent()
    object GetExpenses : ExpenseEvent()
}
