package dev.rranndt.projectexpenses.presentation.feature_expense.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.presentation.ui.theme.Shapes
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterPickerItem(
    label: String,
    onClick: () -> Unit,
) {
    Surface(
        shape = Shapes.small,
        color = Color.Red,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.extraSmall
                )
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_unfold),
                contentDescription = "label"
            )
        }
    }
}