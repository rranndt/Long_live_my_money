package dev.rranndt.projectexpenses.domain.model

import dev.rranndt.projectexpenses.core.utils.Filter
import java.time.LocalDateTime

data class Expense(
    var expenseId: Int = 0,
    var amount: Double = 0.0,
    var filter: String = "None",
    var dateValue: String = LocalDateTime.now().toString(),
    var description: String = "",
    var category: Category? = null,
) {
    val date: LocalDateTime
        get() = LocalDateTime.parse(dateValue)

    constructor(
        amount: Double,
        filter: Filter,
        dateValue: LocalDateTime,
        description: String,
        category: Category,
    ) : this() {
        this.amount = amount
        this.filter = filter.name
        this.dateValue = dateValue.toString()
        this.description = description
        this.category = category
    }
}
