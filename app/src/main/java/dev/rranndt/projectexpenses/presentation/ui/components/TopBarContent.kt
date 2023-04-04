package dev.rranndt.projectexpenses.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing

@Composable
fun TopBarContent(
    title: String,
    hasNavigationButton: Boolean = false,
    hasFilterButton: Boolean = false,
    navigateBack: (() -> Unit)? = null,
) {
    TopAppBar(
        title = {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (hasNavigationButton) {
                    IconButton(
                        onClick = {
                            if (navigateBack != null) {
                                navigateBack()
                            }
                        },
                        modifier = Modifier
                            .align(alignment = Alignment.CenterStart)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_left),
                            contentDescription = stringResource(id = R.string.title_setting_screen),
                        )
                    }
                }
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall.copy(fontSize = 20.sp),
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
                if (hasFilterButton) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(end = MaterialTheme.spacing.small)
                            .align(alignment = Alignment.CenterEnd)
                            .clickable {
                                // TODO:
                            }
                    ) {
                        Text(
                            text = stringResource(id = R.string.title_filter_statistic_screen),
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
            }
        },
        backgroundColor = MaterialTheme.colorScheme.background,
        elevation = 0.dp
    )
}