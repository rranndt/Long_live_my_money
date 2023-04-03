package dev.rranndt.projectexpenses.presentation.feature_expense.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.rranndt.projectexpenses.core.utils.groupedByDay
import dev.rranndt.projectexpenses.domain.model.Expense

@Composable
fun ListingExpense(
    expense: List<Expense>,
) {
    val groupedExpenses = expense.groupedByDay()

    Column {
        if (groupedExpenses.isEmpty()) {
            Text(
                text = "No Range",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(top = 32.dp)
                    .padding(horizontal = 16.dp)
            )
        } else {
            groupedExpenses.keys.forEach { date ->
                if (groupedExpenses[date] != null) {
                    ExpenseGrouping(
                        date = date,
                        dayExpenses = groupedExpenses[date]!!,
                    )
                }
            }
        }
    }
}