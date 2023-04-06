package dev.rranndt.projectexpenses.presentation.feature_statistic

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.core.utils.Filter
import dev.rranndt.projectexpenses.domain.model.Expense
import dev.rranndt.projectexpenses.presentation.feature_expense.state.ExpenseState
import dev.rranndt.projectexpenses.presentation.feature_statistic.components.StatisticPage
import dev.rranndt.projectexpenses.presentation.feature_statistic.event.StatisticEvent
import dev.rranndt.projectexpenses.presentation.feature_statistic.state.StatisticState
import dev.rranndt.projectexpenses.presentation.ui.components.TopBarContent
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun StatisticScreen(
    state: StatisticState,
    onEvent: (StatisticEvent) -> Unit,
    filterName: String,
    menuOpened: Boolean,
    outputFlow: Filter,
    startDate: LocalDateTime,
    endDate: LocalDateTime,
    totalInRange: Double,
    avgPerDay: Double,
    expenses: List<Expense>,
    expenseState: ExpenseState,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val filter = listOf(
        Filter.Weekly,
        Filter.Monthly,
        Filter.Yearly,
    )

    Scaffold(
        topBar = {
            TopBarContent(
                title = stringResource(id = R.string.navigation_statistic),
                content = {
                    Row(
                        modifier = Modifier
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) {
                                onEvent(StatisticEvent.OpenFilterMenu)
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = filterName,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Medium
                            ),
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_unfold),
                            contentDescription = stringResource(id = R.string.content_description_icon_statistic_screen),
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(MaterialTheme.spacing.medium)
                        )
                    }
                    DropdownMenu(
                        expanded = menuOpened,
                        onDismissRequest = { onEvent(StatisticEvent.CloseFilterMenu) },
                        modifier = Modifier
                            .background(LocalContentColor.current.copy(alpha = 0.1f))
                    ) {
                        filter.forEach {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = it.name,
                                        color = MaterialTheme.colorScheme.onBackground,
                                        fontWeight = FontWeight.Normal
                                    )
                                },
                                onClick = {
                                    onEvent(StatisticEvent.SetStatistic(it))
                                    onEvent(StatisticEvent.CloseFilterMenu)
                                }
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        val numOfPages = when (state.filter) {
            Filter.Weekly -> 53
            Filter.Monthly -> 12
            Filter.Yearly -> 1
            else -> 53
        }

        HorizontalPager(
            pageCount = numOfPages,
            reverseLayout = true,
        ) { page ->
            StatisticPage(
                innerPadding = innerPadding,
                page = page, // TODO: slide page until i between week/month
                filter = outputFlow,
                startDate = startDate,
                endDate = endDate,
                totalInRange = totalInRange,
                avgPerDay = avgPerDay,
                expenses = expenses,
                expenseState = expenseState
            )
        }
    }
}