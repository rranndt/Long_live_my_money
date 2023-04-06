package dev.rranndt.projectexpenses.core.utils

sealed class Filter(
    val name: String,
    val target: String,
) {
    object None : Filter("None", "None")

    object Daily : Filter("Daily", "This day")

    object Weekly : Filter("Weekly", "This week")

    object Monthly : Filter("Monthly", "This month")

    object Yearly : Filter("Yearly", "This year")
}