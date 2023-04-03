package dev.rranndt.projectexpenses.core.utils

import dev.rranndt.projectexpenses.domain.model.Expense
import java.time.LocalDate

data class DayExpense(
    val expenses: MutableList<Expense>,
    var total: Double,
)

fun List<Expense>.groupedByDay(): Map<LocalDate, DayExpense> {
    val dataMap: MutableMap<LocalDate, DayExpense> = mutableMapOf()

    this.forEach { expense ->
        val date = expense.date.toLocalDate()

        if (dataMap[date] == null) {
            dataMap[date] = DayExpense(
                expenses = mutableListOf(),
                total = 0.0
            )
        }

        dataMap[date]?.expenses?.add(expense)
        dataMap[date]?.total = dataMap[date]?.total?.plus(expense.amount) ?: 0.0
    }

    dataMap.values.forEach { dayExpense ->
        dayExpense.expenses.sortBy { expense ->
            expense.date
        }
    }
    return dataMap.toSortedMap(compareByDescending { it })
}