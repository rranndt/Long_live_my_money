package dev.rranndt.projectexpenses.presentation.feature_categories.event

import androidx.compose.ui.graphics.Color

sealed class CategoryEvent {
    data class CategoryName(val name: String) : CategoryEvent()
    data class CategoryColor(val color: Color) : CategoryEvent()
    object ShowColorPicker : CategoryEvent()
    object HideColorPicker : CategoryEvent()
    object ShowDeleteConfirmation : CategoryEvent()
    object HideDeleteConfirmation : CategoryEvent()
    object ClearText : CategoryEvent()
    object InsertCategory : CategoryEvent()
    data class DeleteCategoryBy(val id: Int) : CategoryEvent()
}
