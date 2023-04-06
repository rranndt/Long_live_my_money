package dev.rranndt.projectexpenses.presentation.feature_categories

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rranndt.projectexpenses.domain.model.Category
import dev.rranndt.projectexpenses.domain.usecase.ExpenseUseCase
import dev.rranndt.projectexpenses.presentation.feature_categories.event.CategoryEvent
import dev.rranndt.projectexpenses.presentation.feature_categories.state.CategoryState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val useCase: ExpenseUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(CategoryState())
    val uiState: StateFlow<CategoryState> = _uiState.asStateFlow()

    init {
        getCategories()
    }

    fun getCategories() =
        useCase.getCategories()
            .onEach { data ->
                _uiState.value = uiState.value.copy(
                    categories = data
                )
            }

    fun onEvent(event: CategoryEvent) {
        when (event) {
            is CategoryEvent.CategoryName -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        categoryName = event.name
                    )
                }
            }
            is CategoryEvent.CategoryColor -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        categoryColor = event.color
                    )
                }
            }
            CategoryEvent.ShowColorPicker -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        isColorPickerShowing = true
                    )
                }
            }
            CategoryEvent.HideColorPicker -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        isColorPickerShowing = false
                    )
                }
            }
            CategoryEvent.ShowDeleteConfirmation -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        isDeleteConfirmationShowing = true
                    )
                }
            }
            CategoryEvent.HideDeleteConfirmation -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        isDeleteConfirmationShowing = false
                    )
                }
            }
            CategoryEvent.ClearText -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        categoryName = "",
                    )
                }
            }
            CategoryEvent.InsertCategory -> {
                viewModelScope.launch(Dispatchers.IO) {
                    useCase.insertCategory(
                        Category(
                            name = _uiState.value.categoryName,
                            color = _uiState.value.categoryColor,
                        )
                    )
                    _uiState.update { currentState ->
                        currentState.copy(
                            categoryName = "",
                            categoryColor = Color.Gray
                        )
                    }
                }
            }
            is CategoryEvent.DeleteCategoryBy -> {
                viewModelScope.launch {
                    useCase.deleteCategoryBy(event.id)
                }
            }
        }
    }
}