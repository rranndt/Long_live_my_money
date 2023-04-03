package dev.rranndt.projectexpenses.presentation.feature_add.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.rranndt.projectexpenses.core.utils.OutputFlowFilter
import dev.rranndt.projectexpenses.presentation.ui.components.TableRow
import dev.rranndt.projectexpenses.presentation.ui.theme.Shapes
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing

@Composable
fun SetOutputFlow(
    label: String,
    outputFlow: String,
    onSetOutputFlowFilter: (OutputFlowFilter) -> Unit,
    filterMenuOpened: MutableState<Boolean>,
) {
    val outputFlowFilter = listOf(
        OutputFlowFilter.NONE,
        OutputFlowFilter.DAILY,
        OutputFlowFilter.WEEKLY,
        OutputFlowFilter.MONTHLY,
        OutputFlowFilter.YEARLY,
    )

    TableRow(
        label = label,
        labelStyle = MaterialTheme.typography.bodyMedium,
        labelWeight = FontWeight.Medium,
        minHeight = 50.dp,
        content = {
            TextButton(
                onClick = {
                    filterMenuOpened.value = true
                },
                shape = Shapes.small
            ) {
                Text(
                    text = outputFlow,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp),
                    modifier = Modifier
                        .padding(end = MaterialTheme.spacing.small)
                )
                DropdownMenu(
                    expanded = filterMenuOpened.value,
                    onDismissRequest = {
                        filterMenuOpened.value = false
                    },
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                                .copy(alpha = 0.9f)
                        )
                ) {
                    outputFlowFilter.forEach {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = stringResource(id = it.name),
                                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                                    fontWeight = FontWeight.Normal
                                )
                            },
                            onClick = {
                                onSetOutputFlowFilter(it)
                                filterMenuOpened.value = false
                            }
                        )
                    }
                }
            }
        }
    )
}