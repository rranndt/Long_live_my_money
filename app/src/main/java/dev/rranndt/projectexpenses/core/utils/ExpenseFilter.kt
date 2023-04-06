package dev.rranndt.projectexpenses.core.utils

import dev.rranndt.projectexpenses.domain.model.Expense
import java.time.LocalDate

data class DayExpense(
    val expenses: MutableList<Expense>,
    var total: Double,
)

fun List<Expense>.groupByDay(): Map<LocalDate, DayExpense> {
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

fun List<Expense>.groupByDayOfWeek(): Map<String, DayExpense> {
    val dataMap: MutableMap<String, DayExpense> = mutableMapOf()

    this.forEach { expense ->
        val dayOfWeek = expense.date.toLocalDate().dayOfWeek

        if (dataMap[dayOfWeek.name] == null) {
            dataMap[dayOfWeek.name] = DayExpense(
                expenses = mutableListOf(),
                total = 0.0
            )
        }

        dataMap[dayOfWeek.name]?.expenses?.add(expense)
        dataMap[dayOfWeek.name]?.total = dataMap[dayOfWeek.name]?.total?.plus(expense.amount) ?: 0.0
    }

    return dataMap.toSortedMap(compareByDescending { it })
}

fun List<Expense>.groupByDayOfMonth(): Map<Int, DayExpense> {
    val dataMap: MutableMap<Int, DayExpense> = mutableMapOf()

    this.forEach { expense ->
        val dayOfMonth = expense.date.toLocalDate().dayOfMonth

        if (dataMap[dayOfMonth] == null) {
            dataMap[dayOfMonth] = DayExpense(
                expenses = mutableListOf(),
                total = 0.0
            )
        }

        dataMap[dayOfMonth]?.expenses?.add(expense)
        dataMap[dayOfMonth]?.total = dataMap[dayOfMonth]?.total?.plus(expense.amount) ?: 0.0
    }

    return dataMap.toSortedMap(compareByDescending { it })
}

fun List<Expense>.groupByMonthOfYear(): Map<String, DayExpense> {
    val dataMap: MutableMap<String, DayExpense> = mutableMapOf()

    this.forEach { expense ->
        val month = expense.date.toLocalDate().month

        if (dataMap[month.name] == null) {
            dataMap[month.name] = DayExpense(
                expenses = mutableListOf(),
                total = 0.0
            )
        }

        dataMap[month.name]?.expenses?.add(expense)
        dataMap[month.name]?.total = dataMap[month.name]?.total?.plus(expense.amount) ?: 0.0
    }

    return dataMap.toSortedMap(compareByDescending { it })
}