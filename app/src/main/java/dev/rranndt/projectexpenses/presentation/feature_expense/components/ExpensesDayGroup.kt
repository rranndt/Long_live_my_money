package dev.rranndt.projectexpenses.presentation.feature_expense.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.core.utils.DayExpense
import dev.rranndt.projectexpenses.core.utils.localCurrencyFormat
import dev.rranndt.projectexpenses.presentation.feature_expense.state.ExpenseState
import dev.rranndt.projectexpenses.presentation.ui.components.CustomDivider
import dev.rranndt.projectexpenses.presentation.ui.theme.Shapes
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing

@Composable
fun ExpensesDayGroup(
    formatDay: String,
    dayExpense: DayExpense,
    state: ExpenseState,
) {
    Card(
        modifier = Modifier
            .padding(top = MaterialTheme.spacing.medium),
        shape = Shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(8.dp),
        ),
    ) {
        Text(
            text = formatDay,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold,
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.extraSmall,
                    horizontal = MaterialTheme.spacing.medium
                )
        )
        dayExpense.expenses.forEachIndexed { index, expense ->
            ExpenseItems(
                description = expense.description,
                amount = expense.amount,
                expense = expense
            )
            if (index < state.expenses.size - 1) {
                Row(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
                        .height(1.dp)
                        .clip(Shapes.medium)
                ) {
                    CustomDivider()
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.extraSmall,
                    horizontal = MaterialTheme.spacing.medium
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = stringResource(id = R.string.title_total_item_expense_screen),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Medium
                )
            )
            Text(
                text = "IDR ${dayExpense.total.localCurrencyFormat()}",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.End
                ),
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}