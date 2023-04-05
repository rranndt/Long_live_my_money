package dev.rranndt.projectexpenses.presentation.feature_statistic.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.core.utils.Filter
import dev.rranndt.projectexpenses.core.utils.formatDayForRange
import dev.rranndt.projectexpenses.core.utils.simplifyNumber
import dev.rranndt.projectexpenses.domain.model.Expense
import dev.rranndt.projectexpenses.presentation.feature_expense.components.ListingExpense
import dev.rranndt.projectexpenses.presentation.feature_expense.state.ExpenseState
import dev.rranndt.projectexpenses.presentation.feature_statistic.components.charts.MonthlyChart
import dev.rranndt.projectexpenses.presentation.feature_statistic.components.charts.WeeklyChart
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun StatisticPage(
    innerPadding: PaddingValues,
    page: Int,
    filter: Filter,
    startDate: LocalDateTime,
    endDate: LocalDateTime,
    totalInRange: Double,
    avgPerDay: Double,
    expenses: List<Expense>,
    expenseState: ExpenseState,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding)
            .padding(horizontal = MaterialTheme.spacing.medium)
            .padding(top = MaterialTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "${startDate.formatDayForRange()} - ${endDate.formatDayForRange()}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = stringResource(id = R.string.title_avg_day_statistic_screen),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.title_currency_expense_screen),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 12.sp,
                        color = LocalContentColor.current.copy(
                            alpha = 0.7f
                        )
                    )
                )
                Text(
                    text = totalInRange.simplifyNumber(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.title_currency_expense_screen),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 12.sp,
                        color = LocalContentColor.current.copy(
                            alpha = 0.7f
                        )
                    )
                )
                Text(
                    text = avgPerDay.simplifyNumber(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }

        Box(
            modifier = Modifier
                .height(180.dp)
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            when (filter) {
                Filter.Weekly -> WeeklyChart(expenses)
                Filter.Monthly -> MonthlyChart(expense = expenses, month = LocalDate.now())
                Filter.Yearly -> Unit
                else -> Unit
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                ListingExpense(
                    expenses = expenses,
                    state = expenseState,
                    paddingBottom = MaterialTheme.spacing.medium,
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }
    }
}