package dev.rranndt.projectexpenses.presentation.feature_expense.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import dev.rranndt.projectexpenses.presentation.ui.theme.Shapes
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing

@Composable
fun CategoryBadge(
    categoryName: String,
    categoryColor: Color,
) {
    Surface(
        shape = Shapes.extraSmall,
        color = categoryColor,
    ) {
        Text(
            text = categoryName,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onBackground
            ),
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.extraSmall)
        )
    }
}