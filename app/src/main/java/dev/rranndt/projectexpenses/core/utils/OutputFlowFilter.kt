package dev.rranndt.projectexpenses.core.utils

import androidx.annotation.StringRes
import dev.rranndt.projectexpenses.R

sealed class OutputFlowFilter(
    @StringRes val name: Int,
    @StringRes val target: Int,
) {
    object NONE : OutputFlowFilter(R.string.name_none, R.string.target_none)
    object DAILY : OutputFlowFilter(R.string.name_daily, R.string.target_daily)
    object WEEKLY : OutputFlowFilter(R.string.name_weekly, R.string.target_weekly)
    object MONTHLY : OutputFlowFilter(R.string.name_monthly, R.string.target_monthly)
    object YEARLY : OutputFlowFilter(R.string.name_yearly, R.string.target_yearly)
}
