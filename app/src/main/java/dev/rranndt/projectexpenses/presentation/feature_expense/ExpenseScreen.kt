package dev.rranndt.projectexpenses.presentation.feature_expense

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.core.utils.Filter
import dev.rranndt.projectexpenses.core.utils.localCurrencyFormat
import dev.rranndt.projectexpenses.domain.model.Expense
import dev.rranndt.projectexpenses.presentation.feature_expense.components.FilterTrigger
import dev.rranndt.projectexpenses.presentation.feature_expense.components.ListingExpense
import dev.rranndt.projectexpenses.presentation.feature_expense.event.ExpenseEvent
import dev.rranndt.projectexpenses.presentation.feature_expense.state.ExpenseState
import dev.rranndt.projectexpenses.presentation.ui.components.CustomDivider
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing

@Composable
fun ExpenseScreen(
    expenses: List<Expense>,
    filterName: String,
    menuOpened: Boolean,
    sumTotal: Double,
    onEvent: (ExpenseEvent) -> Unit,
    state: ExpenseState,
) {
    val interactionSource = remember { MutableInteractionSource() }

    val filters = listOf(
        Filter.Daily,
        Filter.Weekly,
        Filter.Monthly,
        Filter.Yearly,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingValues(MaterialTheme.spacing.medium)),
    ) {
        Text(
            text = stringResource(id = R.string.title_total_expense_screen),
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.title_currency_expense_screen),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.outline
                ),
                modifier = Modifier
                    .padding(top = MaterialTheme.spacing.extraSmall)
                    .align(Alignment.Top)
            )
            Text(
                text = sumTotal.localCurrencyFormat(),
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 2.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                FilterTrigger(
                    filterName = filterName,
                    onClick = { onEvent(ExpenseEvent.OpenFilterMenu) },
                    interactionSource = interactionSource
                )
                DropdownMenu(
                    expanded = menuOpened,
                    onDismissRequest = { onEvent(ExpenseEvent.CloseFilterMenu) },
                    modifier = Modifier
                        .background(LocalContentColor.current.copy(alpha = 0.1f))
                ) {
                    filters.forEach { outputFlow ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = outputFlow.target,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    fontWeight = FontWeight.Normal
                                )
                            },
                            onClick = {
                                onEvent(ExpenseEvent.SetExpenses(outputFlow))
                                onEvent(ExpenseEvent.CloseFilterMenu)
                            }
                        )
                    }
                }
            }
        }
        CustomDivider(MaterialTheme.spacing.default)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                ListingExpense(
                    expenses = expenses,
                    state = state,
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }
    }
}