package dev.rranndt.projectexpenses.presentation.feature_add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.core.utils.Filter
import dev.rranndt.projectexpenses.domain.model.Category
import dev.rranndt.projectexpenses.presentation.feature_add.components.*
import dev.rranndt.projectexpenses.presentation.feature_add.state.AddExpenseState
import dev.rranndt.projectexpenses.presentation.feature_categories.CategoryViewModel
import dev.rranndt.projectexpenses.presentation.ui.components.CustomDivider
import dev.rranndt.projectexpenses.presentation.ui.components.TextViewHelper
import dev.rranndt.projectexpenses.presentation.ui.theme.Shapes
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing
import java.time.LocalDate

@Composable
fun AddExpenseScreen(
    amount: String,
    outputFlow: String,
    date: String,
    initialDate: LocalDate,
    categoryName: String,
    categoryColor: Color,
    description: String,
    onAmountValueChange: (String) -> Unit,
    onFilterValueChange: (Filter) -> Unit,
    filterMenuOpened: MutableState<Boolean>,
    onSetDate: (LocalDate) -> Unit,
    datePickerOpened: MutableState<Boolean>,
    onSetCategory: (Category) -> Unit,
    categoryMenuOpened: MutableState<Boolean>,
    onDescriptionValueChange: (String) -> Unit,
    state: AddExpenseState,
    onInsertExpense: () -> Unit,
    focusManager: FocusManager = LocalFocusManager.current,
    enabled: Boolean = false,
    categoryViewModel: CategoryViewModel,
) {
    val categories = categoryViewModel.getCategories().collectAsState(initial = listOf()).value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = MaterialTheme.spacing.medium)
            .padding(top = MaterialTheme.spacing.medium, bottom = 74.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(Shapes.small)
                .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
        ) {
            SetAmount(
                label = stringResource(id = R.string.title_amount_add_screen),
                amount = amount,
                onAmountValueChange = onAmountValueChange
            )
            CustomDivider(MaterialTheme.spacing.default)
            SetOutputFlow(
                label = stringResource(id = R.string.title_output_flow_filter_add_screen),
                outputFlow = outputFlow,
                onSetOutputFlowFilter = onFilterValueChange,
                filterMenuOpened = filterMenuOpened,
            )
            CustomDivider(MaterialTheme.spacing.default)
            SetDate(
                label = stringResource(id = R.string.title_date_add_screen),
                date = date,
                initialDate = initialDate,
                onSetDate = onSetDate,
                datePickerOpened = datePickerOpened
            )
            CustomDivider(MaterialTheme.spacing.default)
            SetCategory(
                label = stringResource(id = R.string.title_category_add_screen),
                categoryName = categoryName,
                categoryColor = categoryColor,
                onSetCategory = onSetCategory,
                categoryMenuOpened = categoryMenuOpened,
                categoryViewModel = categoryViewModel,
            )
            CustomDivider(MaterialTheme.spacing.default)
            SetDescription(
                label = stringResource(id = R.string.title_description_add_screen),
                value = description,
                onDescriptionValueChange = onDescriptionValueChange
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    onInsertExpense()
                    focusManager.clearFocus()
                },
                enabled = state.category != null && state.amount.isNotEmpty(),
                shape = Shapes.extraSmall,
                modifier = Modifier
                    .width(150.dp)
            ) {
                val alpha = if (!enabled) 1f else 0.5f
                Text(
                    text = stringResource(id = R.string.title_submit_add_screen),
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.background
                    ),
                    modifier = Modifier
                        .alpha(alpha = alpha)
                        .align(alignment = Alignment.CenterVertically),
                )
            }
        }
        categories.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                if (it.isEmpty()) {
                    TextViewHelper(
                        text = stringResource(id = R.string.title_no_category_found_category_screen),
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontSize = 12.sp,
                            lineHeight = 16.sp
                        ),
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .padding(top = MaterialTheme.spacing.large)
                    )
                }
            }
        }
    }
}