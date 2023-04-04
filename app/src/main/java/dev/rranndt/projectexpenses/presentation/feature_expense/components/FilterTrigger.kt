package dev.rranndt.projectexpenses.presentation.feature_expense.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing

@Composable
fun FilterTrigger(
    filterName: String,
    onClick: () -> Unit,
    interactionSource: MutableInteractionSource,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onClick() }
    ) {
        Text(
            text = filterName,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium
            )
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_unfold),
            contentDescription = stringResource(id = R.string.content_description_icon_statistic_screen),
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(MaterialTheme.spacing.medium)
        )
    }
}