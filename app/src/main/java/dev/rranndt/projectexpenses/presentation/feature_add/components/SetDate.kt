package dev.rranndt.projectexpenses.presentation.feature_add.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marosseleng.compose.material3.datetimepickers.date.ui.dialog.DatePickerDialog
import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.presentation.ui.components.TableRow
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing
import java.time.LocalDate

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SetDate(
    label: String,
    date: String,
    initialDate: LocalDate,
    onSetDate: (LocalDate) -> Unit,
    datePickerOpened: MutableState<Boolean>,
) {
    TableRow(
        label = label,
        labelStyle = MaterialTheme.typography.bodyMedium,
        labelWeight = FontWeight.Medium,
        minHeight = 50.dp,
        content = {
            TextButton(
                onClick = {
                    datePickerOpened.value = true
                }
            ) {
                Text(
                    text = date,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp),
                    modifier = Modifier
                        .padding(end = MaterialTheme.spacing.small)
                )
            }
            if (datePickerOpened.value) {
                DatePickerDialog(
                    onDismissRequest = {
                        datePickerOpened.value = false
                    },
                    onDateChange = {
                        onSetDate(it)
                        datePickerOpened.value = false
                    },
                    initialDate = initialDate,
                    title = {
                        Text(
                            text = stringResource(id = R.string.title_select_date_add_screen),
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                            fontWeight = FontWeight.Medium
                        )
                    }
                )
            }
        }
    )
}