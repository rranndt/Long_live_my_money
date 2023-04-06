package dev.rranndt.projectexpenses.presentation.feature_expense.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.core.utils.formatDay
import dev.rranndt.projectexpenses.core.utils.groupByDay
import dev.rranndt.projectexpenses.domain.model.Expense
import dev.rranndt.projectexpenses.presentation.feature_expense.state.ExpenseState
import dev.rranndt.projectexpenses.presentation.ui.components.TextViewHelper
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing

@Composable
fun ListingExpense(
    expenses: List<Expense>,
    modifier: Modifier = Modifier,
    state: ExpenseState,
    paddingBottom: Dp = 74.dp,
) {
    val groupedExpenses = expenses.groupByDay()
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(PaddingValues(bottom = paddingBottom))
    ) {
        if (groupedExpenses.isEmpty()) {
            TextViewHelper(
                text = stringResource(id = R.string.title_no_data_expense_screen),
                style = MaterialTheme.typography.titleSmall.copy(fontSize = 14.sp),
                modifier = Modifier
                    .padding(vertical = MaterialTheme.spacing.extraLarge)
            )
        } else {
            groupedExpenses.keys.forEach {
                if (groupedExpenses[it] != null) {
                    ExpensesDayGroup(
                        formatDay = it.formatDay(context),
                        dayExpense = groupedExpenses[it]!!,
                        state = state
                    )
                }
            }
        }
    }
}