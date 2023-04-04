package dev.rranndt.projectexpenses.presentation.ui.navigation.top_bar_pager

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.google.accompanist.pager.*
import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.core.utils.OutputFlowFilter
import dev.rranndt.projectexpenses.domain.model.Category
import dev.rranndt.projectexpenses.domain.model.Expense
import dev.rranndt.projectexpenses.presentation.feature_add.AddExpenseScreen
import dev.rranndt.projectexpenses.presentation.feature_add.state.AddExpenseState
import dev.rranndt.projectexpenses.presentation.feature_categories.CategoryViewModel
import dev.rranndt.projectexpenses.presentation.feature_expense.ExpenseScreen
import dev.rranndt.projectexpenses.presentation.feature_expense.event.ExpenseEvent
import dev.rranndt.projectexpenses.presentation.feature_expense.state.ExpenseState
import dev.rranndt.projectexpenses.presentation.ui.navigation.Screen
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TopBarPager(
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    amount: String,
    outputFlow: String,
    date: String,
    initialDate: LocalDate,
    categoryName: String,
    categoryColor: Color,
    description: String,
    onAmountValueChange: (String) -> Unit,
    onFilterValueChange: (OutputFlowFilter) -> Unit,
    filterMenuOpened: MutableState<Boolean>,
    onSetDate: (LocalDate) -> Unit,
    datePickerOpened: MutableState<Boolean>,
    onSetCategory: (Category) -> Unit,
    categoryMenuOpened: MutableState<Boolean>,
    onDescriptionValueChange: (String) -> Unit,
    categoryViewModel: CategoryViewModel,
    addExpenseState: AddExpenseState,
    onInsertExpense: () -> Unit,
    expenses: List<Expense>,
    filterName: String,
    sumTotal: Double,
    onEvent: (ExpenseEvent) -> Unit,
    expenseState: ExpenseState,
) {
    val tabRowItems = listOf(
        TopBarPagerItem(
            label = stringResource(id = R.string.top_bar_expense),
            screen = Screen.Expense
        ),
        TopBarPagerItem(
            label = stringResource(id = R.string.top_bar_add),
            screen = Screen.Add
        ),
    )
    val indicator = @Composable { tabPosition: List<TabPosition> ->
        CustomIndicator(tabPosition = tabPosition, pagerState = pagerState)
    }

    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = indicator,
            backgroundColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
            modifier = Modifier
                .padding(
                    vertical = MaterialTheme.spacing.medium,
                    horizontal = MaterialTheme.spacing.extraLarge
                )
                .clip(RoundedCornerShape(MaterialTheme.spacing.large)),
            divider = {
                TabRowDefaults.Divider(color = Color.Transparent)
            }
        ) {
            tabRowItems.forEachIndexed { index, item ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = item.label,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.titleSmall.copy(fontSize = 16.sp),
                            fontWeight = FontWeight.Medium
                        )
                    },
                    modifier = Modifier
                        .zIndex(6f)
                )
            }
        }
        HorizontalPager(
            count = tabRowItems.size,
            state = pagerState,
        ) { page ->
            when (page) {
                0 -> ExpenseScreen(
                    expenses = expenses,
                    sumTotal = sumTotal,
                    filterName = filterName,
                    onEvent = onEvent,
                    state = expenseState
                )
                1 -> AddExpenseScreen(
                    amount = amount,
                    outputFlow = outputFlow,
                    date = date,
                    initialDate = initialDate,
                    categoryName = categoryName,
                    categoryColor = categoryColor,
                    description = description,
                    onAmountValueChange = onAmountValueChange,
                    onFilterValueChange = onFilterValueChange,
                    filterMenuOpened = filterMenuOpened,
                    onSetDate = onSetDate,
                    datePickerOpened = datePickerOpened,
                    onSetCategory = onSetCategory,
                    categoryMenuOpened = categoryMenuOpened,
                    onDescriptionValueChange = onDescriptionValueChange,
                    categoryViewModel = categoryViewModel,
                    state = addExpenseState,
                    onInsertExpense = onInsertExpense,
                )
            }
        }
    }
}

