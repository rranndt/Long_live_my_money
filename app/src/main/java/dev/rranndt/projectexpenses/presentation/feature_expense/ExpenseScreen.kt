package dev.rranndt.projectexpenses.presentation.feature_expense

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.core.utils.OutputFlowFilter
import dev.rranndt.projectexpenses.domain.model.Expense
import dev.rranndt.projectexpenses.presentation.feature_expense.components.FilterTrigger
import dev.rranndt.projectexpenses.presentation.feature_expense.components.ListingExpense
import dev.rranndt.projectexpenses.presentation.feature_expense.event.ExpenseEvent
import dev.rranndt.projectexpenses.presentation.feature_expense.state.ExpenseState
import dev.rranndt.projectexpenses.presentation.ui.components.CustomDivider
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing
import java.text.NumberFormat
import java.util.*

@Composable
fun ExpenseScreen(
    expenses: List<Expense>,
    filterName: String,
    sumTotal: Double,
    onEvent: (ExpenseEvent) -> Unit,
    state: ExpenseState,
) {
    var filterMenuOpened by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    val expenseFilter = listOf(
        OutputFlowFilter.DAILY,
        OutputFlowFilter.WEEKLY,
        OutputFlowFilter.MONTHLY,
        OutputFlowFilter.YEARLY,
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
                text = NumberFormat.getNumberInstance(Locale.getDefault()).format(sumTotal),
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                FilterTrigger(
                    filterName = filterName,
                    onClick = { filterMenuOpened = !filterMenuOpened },
                    interactionSource = interactionSource
                )
                DropdownMenu(
                    expanded = filterMenuOpened,
                    onDismissRequest = { filterMenuOpened = false },
                ) {
                    expenseFilter.forEach { outputFlow ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = stringResource(id = outputFlow.target),
                                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                                    fontWeight = FontWeight.Normal
                                )
                            },
                            onClick = {
                                onEvent(ExpenseEvent.SetOutputFlow(outputFlow))
                                filterMenuOpened = false
                            }
                        )
                    }
                }
            }
        }
        CustomDivider(MaterialTheme.spacing.default)
        LazyColumn {
            item {
                ListingExpense(
                    expenses = expenses,
                    state = state,
                )
            }
        }
    }
}