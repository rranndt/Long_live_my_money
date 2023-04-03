package dev.rranndt.projectexpenses.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.rranndt.projectexpenses.R

@Composable
fun TopBarContent(
    title: String,
    hasNavigationButton: Boolean = false,
    hasActionButton: Boolean = false,
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
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                if (hasActionButton) {
                    IconButton(
                        onClick = {
                            // TODO:
                        },
                        modifier = Modifier
                            .align(alignment = Alignment.CenterEnd)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_calendar),
                            contentDescription = stringResource(id = R.string.content_description_calendar_icon_statistic_screen)
                        )
                    }
                }
            }
        },
        backgroundColor = MaterialTheme.colorScheme.background,
        elevation = 0.dp
    )

}