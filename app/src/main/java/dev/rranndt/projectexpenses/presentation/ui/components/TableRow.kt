package dev.rranndt.projectexpenses.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.presentation.ui.theme.Destructive
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing

@Composable
fun TableRow(
    modifier: Modifier = Modifier,
    minHeight: Dp = 45.dp,
    iconLabel: Painter? = null,
    label: String? = null,
    labelStyle: TextStyle = MaterialTheme.typography.bodySmall,
    labelWeight: FontWeight = FontWeight.Normal,
    hasArrow: Boolean = false,
    isDestructive: Boolean = false,
    content: (@Composable RowScope.() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    val textColor =
        if (isDestructive) {
            Destructive
        } else {
            MaterialTheme.colorScheme.onBackground
        }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = minHeight),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (iconLabel != null) {
            Icon(
                painter = iconLabel,
                contentDescription = label,
                modifier = Modifier
                    .padding(start = MaterialTheme.spacing.medium)
                    .size(20.dp)
            )
        }
        if (label != null) {
            Text(
                text = label,
                style = labelStyle,
                color = textColor,
                fontWeight = labelWeight,
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.spacing.medium,
                        vertical = MaterialTheme.spacing.small
                    )
                    .weight(1f),
            )
        }

        if (content != null) {
            content()
        }

        if (hasArrow) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = label,
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.spacing.medium,
                        vertical = MaterialTheme.spacing.small
                    )
            )
        }

        if (trailingIcon != null) {
            trailingIcon()
        }
    }
}