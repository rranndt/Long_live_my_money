package dev.rranndt.projectexpenses.domain.model

import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.core.utils.OutputFlowFilter
import java.time.LocalDateTime

data class Expense(
    var expenseId: Int = 0,
    var amount: Double = 0.0,
    var outputFlow: Int = R.string.name_none,
    var dateValue: String = LocalDateTime.now().toString(),
    var description: String = "",
    var category: Category? = null,
) {
    val date: LocalDateTime
        get() = LocalDateTime.parse(dateValue)

    constructor(
        amount: Double,
        outputFlow: OutputFlowFilter,
        dateValue: LocalDateTime,
        description: String,
        category: Category,
    ) : this() {
        this.amount = amount
        this.outputFlow = outputFlow.name
        this.dateValue = dateValue.toString()
        this.description = description
        this.category = category
    }
}
