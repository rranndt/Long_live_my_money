package dev.rranndt.projectexpenses.presentation.ui.base

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.core.utils.formatDay
import dev.rranndt.projectexpenses.presentation.feature_add.AddExpenseViewModel
import dev.rranndt.projectexpenses.presentation.feature_add.event.AddExpenseEvent
import dev.rranndt.projectexpenses.presentation.feature_categories.CategoryScreen
import dev.rranndt.projectexpenses.presentation.feature_categories.CategoryViewModel
import dev.rranndt.projectexpenses.presentation.feature_expense.ExpenseScreen
import dev.rranndt.projectexpenses.presentation.feature_expense.ExpenseViewModel
import dev.rranndt.projectexpenses.presentation.feature_setting.SettingScreen
import dev.rranndt.projectexpenses.presentation.feature_statistic.StatisticScreen
import dev.rranndt.projectexpenses.presentation.ui.navigation.Screen
import dev.rranndt.projectexpenses.presentation.ui.navigation.bottom_bar.BottomBar
import dev.rranndt.projectexpenses.presentation.ui.navigation.top_bar_pager.TopBarPager
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val expenseViewModel: ExpenseViewModel = hiltViewModel()
    val addExpenseViewModel: AddExpenseViewModel = hiltViewModel()
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val expenseState by expenseViewModel.uiState.collectAsState()
    val addExpenseState by addExpenseViewModel.uiState.collectAsState()
    val filterMenuOpened = remember { mutableStateOf(false) }
    val datePickerOpened = remember { mutableStateOf(false) }
    val categoryMenuOpened = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.currentPage }.collect()
    }

    Scaffold(
        topBar = {
            if (currentRoute == Screen.Expense.route) {
                TopBarPager(
                    pagerState = pagerState,
                    coroutineScope = coroutineScope,
                    amount = addExpenseState.amount,
                    outputFlow = stringResource(id = addExpenseState.outputFlow.name),
                    date = addExpenseState.date.formatDay(context),
                    initialDate = addExpenseState.date,
                    categoryName = addExpenseState.category?.name
                        ?: stringResource(id = R.string.title_select_category_add_screen),
                    categoryColor = addExpenseState.category?.color
                        ?: MaterialTheme.colorScheme.onTertiaryContainer,
                    description = addExpenseState.description,
                    onAmountValueChange = { addExpenseViewModel.onEvent(AddExpenseEvent.SetAmount(it)) },
                    onFilterValueChange = {
                        addExpenseViewModel.onEvent(AddExpenseEvent.SetOutputFlow(it))
                    },
                    filterMenuOpened = filterMenuOpened,
                    onSetDate = { addExpenseViewModel.onEvent(AddExpenseEvent.SetDate(it)) },
                    onSetCategory = { addExpenseViewModel.onEvent(AddExpenseEvent.SetCategory(it)) },
                    categoryMenuOpened = categoryMenuOpened,
                    datePickerOpened = datePickerOpened,
                    onDescriptionValueChange = {
                        addExpenseViewModel.onEvent(AddExpenseEvent.SetDescription(it))
                    },
                    categoryViewModel = categoryViewModel,
                    addExpenseState = addExpenseState,
                    onInsertExpense = { addExpenseViewModel.onEvent(AddExpenseEvent.InsertExpense) },
                    expenses = expenseState.expenses,
                    sumTotal = expenseState.sumTotal,
                    filterName = stringResource(id = expenseState.outputFlow.target),
                    onEvent = expenseViewModel::onEvent,
                    expenseState = expenseState
                )
            }
        },
        bottomBar = {
            if (currentRoute != Screen.Categories.route) {
                BottomBar(navController)
            }
        }
    ) { innerPadding ->
        Surface {
            NavHost(
                navController = navController,
                startDestination = Screen.Expense.route,
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                expenseRoute()
                composable(Screen.Statistic.route) { StatisticScreen() }
                composable(Screen.Setting.route) { SettingScreen(navController) }
                categoryRoute(navigateBack = navController::popBackStack)
            }
        }
    }
}

fun NavGraphBuilder.expenseRoute() {
    composable(route = Screen.Expense.route) {
        val viewModel: ExpenseViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsState()

        ExpenseScreen(
            expenses = uiState.expenses,
            filterName = stringResource(id = uiState.outputFlow.target),
            sumTotal = uiState.sumTotal,
            onEvent = viewModel::onEvent,
            state = uiState
        )
    }
}

fun NavGraphBuilder.categoryRoute(
    navigateBack: () -> Unit,
) {
    composable(route = Screen.Categories.route) {
        val viewModel: CategoryViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val colorPickerController = rememberColorPickerController()

        CategoryScreen(
            navigateBack = navigateBack,
            uiState = uiState,
            colorPickerController = colorPickerController,
            viewModel = viewModel,
            onEvent = viewModel::onEvent,
        )
    }
}