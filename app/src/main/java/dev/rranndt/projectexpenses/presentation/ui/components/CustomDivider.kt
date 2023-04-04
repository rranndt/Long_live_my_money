package dev.rranndt.projectexpenses.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing

@Composable
fun CustomDivider(
    padding: Dp = MaterialTheme.spacing.medium,
) {
    Divider(
        modifier = Modifier
            .padding(horizontal = padding),
        thickness = 1.dp,
        color = DividerDefaults.color.copy(alpha = 0.7f)
    )
}