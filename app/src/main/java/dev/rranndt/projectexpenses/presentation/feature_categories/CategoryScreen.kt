package dev.rranndt.projectexpenses.presentation.feature_categories

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.skydoves.colorpicker.compose.ColorPickerController
import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.presentation.feature_categories.components.AddCategoryRow
import dev.rranndt.projectexpenses.presentation.feature_categories.components.ListingCategory
import dev.rranndt.projectexpenses.presentation.feature_categories.event.CategoryEvent
import dev.rranndt.projectexpenses.presentation.feature_categories.state.CategoryState
import dev.rranndt.projectexpenses.presentation.ui.components.NoDataView
import dev.rranndt.projectexpenses.presentation.ui.components.TopBarContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    navigateBack: () -> Unit,
    uiState: CategoryState,
    colorPickerController: ColorPickerController,
    onEvent: (CategoryEvent) -> Unit,
    viewModel: CategoryViewModel,
) {
    val categories = viewModel.getCategories().collectAsState(initial = listOf()).value

    Scaffold(
        topBar = {
            TopBarContent(
                title = stringResource(id = R.string.title_categories_setting_screen),
                hasNavigationButton = true,
                navigateBack = navigateBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                categories.let { data ->
                    if (data.isNotEmpty()) {
                        ListingCategory(
                            state = uiState,
                            data = uiState.categories,
                            onCategoryNameClick = { onEvent(CategoryEvent.CategoryName(it)) },
                            onCategoryColorClick = { onEvent(CategoryEvent.CategoryColor(it)) },
                            onDeleteItem = { onEvent(CategoryEvent.DeleteCategoryBy(it.categoryId)) },
                        )
                    } else {
                        NoDataView(text = stringResource(id = R.string.title_no_data_category_screen))
                    }
                }
            }
            AddCategoryRow(
                state = uiState,
                onShowColorPicker = { onEvent(CategoryEvent.ShowColorPicker) },
                onCategoryNameValue = { onEvent(CategoryEvent.CategoryName(it)) },
                onCategoryColorValue = { onEvent(CategoryEvent.CategoryColor(it)) },
                onClearText = { onEvent(CategoryEvent.ClearText) },
                onInsertCategory = { onEvent(CategoryEvent.InsertCategory) },
                colorPickerController = colorPickerController,
                onHideColorPicker = { onEvent(CategoryEvent.HideColorPicker) }
            )
        }
    }
}