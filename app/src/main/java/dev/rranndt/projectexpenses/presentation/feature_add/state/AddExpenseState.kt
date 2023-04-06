package dev.rranndt.projectexpenses.presentation.feature_add.state

import dev.rranndt.projectexpenses.core.utils.Filter
import dev.rranndt.projectexpenses.domain.model.Category
import java.time.LocalDate

data class AddExpenseState(
    val amount: String = "",
    val outputFlow: Filter = Filter.None,
    val date: LocalDate = LocalDate.now(),
    val description: String = "",
    val category: Category? = null,
    val categories: List<Category>? = null,
)