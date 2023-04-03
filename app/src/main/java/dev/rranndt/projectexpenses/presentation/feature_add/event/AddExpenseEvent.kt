package dev.rranndt.projectexpenses.presentation.feature_add.event

import dev.rranndt.projectexpenses.core.utils.OutputFlowFilter
import dev.rranndt.projectexpenses.domain.model.Category
import java.time.LocalDate

sealed class AddExpenseEvent {
    data class SetAmount(val amount: String) : AddExpenseEvent()
    data class SetOutputFlow(val outputFlow: OutputFlowFilter) : AddExpenseEvent()
    data class SetDate(val date: LocalDate) : AddExpenseEvent()
    data class SetDescription(val description: String) : AddExpenseEvent()
    data class SetCategory(val category: Category) : AddExpenseEvent()
    object InsertExpense : AddExpenseEvent()
}