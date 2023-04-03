package dev.rranndt.projectexpenses.presentation.ui.navigation

import dev.rranndt.projectexpenses.presentation.ui.utils.Constant.CATEGORIES
import dev.rranndt.projectexpenses.presentation.ui.utils.Constant.EXPENSE
import dev.rranndt.projectexpenses.presentation.ui.utils.Constant.SETTING
import dev.rranndt.projectexpenses.presentation.ui.utils.Constant.STATISTIC

sealed class Screen(
    val route: String,
) {
    object Expense : Screen(route = EXPENSE)

    object Add : Screen(route = EXPENSE)

    object Statistic : Screen(route = STATISTIC)

    object Setting : Screen(route = SETTING)

    object Categories : Screen(route = CATEGORIES)
}