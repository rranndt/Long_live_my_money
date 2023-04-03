package dev.rranndt.projectexpenses.presentation.feature_expense

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import dev.rranndt.projectexpenses.core.utils.OutputFlowFilter
import dev.rranndt.projectexpenses.presentation.feature_expense.components.FilterPickerItem
import dev.rranndt.projectexpenses.presentation.feature_expense.components.ListingExpense
import dev.rranndt.projectexpenses.presentation.feature_expense.event.ExpenseEvent
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing
import java.text.NumberFormat
import java.util.*

@Composable
fun ExpenseScreen(
    viewModel: ExpenseViewModel = hiltViewModel(),
) {
    val expenseFilter = listOf(
        OutputFlowFilter.DAILY,
        OutputFlowFilter.WEEKLY,
        OutputFlowFilter.MONTHLY,
        OutputFlowFilter.YEARLY,
    )
    val uiState by viewModel.uiState.collectAsState()
    var expenseMenuOpened by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.medium)
            .padding(top = MaterialTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilterPickerItem(
                label = stringResource(id = uiState.outputFlow.target),
                onClick = {
                    expenseMenuOpened = !expenseMenuOpened
                },
            )
            DropdownMenu(
                expanded = expenseMenuOpened,
                onDismissRequest = {
                    expenseMenuOpened = false
                },
                modifier = Modifier
                    .background(Color.Cyan)
            ) {
                expenseFilter.forEach { filter ->
                    DropdownMenuItem(
                        text = {
                            Text(text = stringResource(id = filter.target))
                        },
                        onClick = {
                            viewModel.onEvent(ExpenseEvent.SetOutputFlow(filter))
                            expenseMenuOpened = false
                        }
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(vertical = MaterialTheme.spacing.large)
        ) {
            Text(
                text = "Rp",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Red
            )
            Text(
                text = " ${
                    NumberFormat.getNumberInstance(Locale.getDefault()).format(uiState.sumTotal)
                }"
            )
        }
        ListingExpense(expense = uiState.expenses)
    }
}