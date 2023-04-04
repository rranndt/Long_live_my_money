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
import androidx.compose.ui.unit.dp
import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.core.utils.formatDay
import dev.rranndt.projectexpenses.core.utils.groupedByDay
import dev.rranndt.projectexpenses.domain.model.Expense
import dev.rranndt.projectexpenses.presentation.feature_expense.state.ExpenseState
import dev.rranndt.projectexpenses.presentation.ui.components.TextViewHelper

@Composable
fun ListingExpense(
    expenses: List<Expense>,
    modifier: Modifier = Modifier,
    state: ExpenseState,
) {
    val groupedExpenses = expenses.groupedByDay()
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(PaddingValues(bottom = 74.dp))
    ) {
        if (groupedExpenses.isEmpty()) {
            TextViewHelper(
                text = stringResource(id = R.string.title_no_data_expense_screen),
                style = MaterialTheme.typography.titleSmall,
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