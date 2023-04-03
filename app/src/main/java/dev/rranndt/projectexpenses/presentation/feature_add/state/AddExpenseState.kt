package dev.rranndt.projectexpenses.presentation.feature_add.state

import dev.rranndt.projectexpenses.core.utils.OutputFlowFilter
import dev.rranndt.projectexpenses.domain.model.Category
import java.time.LocalDate

data class AddExpenseState(
    val amount: String = "",
    val outputFlow: OutputFlowFilter = OutputFlowFilter.NONE,
    val date: LocalDate = LocalDate.now(),
    val description: String = "",
    val category: Category? = null,
    val categories: List<Category>? = null,
)