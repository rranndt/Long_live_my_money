package dev.rranndt.projectexpenses.presentation.feature_categories.state

import androidx.compose.ui.graphics.Color
import dev.rranndt.projectexpenses.domain.model.Category

data class CategoryState(
    val categoryName: String = "",
    val categoryColor: Color = Color.Gray,
    val isColorPickerShowing: Boolean = false,
    val categories: List<Category> = emptyList(),
    var isDeleteConfirmationShowing: Boolean = false,
)
