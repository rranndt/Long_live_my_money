package dev.rranndt.projectexpenses.presentation.feature_add.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import dev.rranndt.projectexpenses.core.utils.NumberCommaTransformation
import dev.rranndt.projectexpenses.presentation.ui.components.CustomTextField
import dev.rranndt.projectexpenses.presentation.ui.components.TableRow

@Composable
fun SetAmount(
    label: String,
    amount: String,
    onAmountValueChange: (String) -> Unit,
    focusManager: FocusManager = LocalFocusManager.current,
) {
    TableRow(
        label = label,
        labelStyle = MaterialTheme.typography.bodyMedium,
        labelWeight = FontWeight.Medium,
        minHeight = 50.dp,
        content = {
            TableRow(
                modifier = Modifier.weight(1f),
                content = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        CustomTextField(
                            value = amount,
                            onValueChange = {
                                if (it.length <= 12 && it.isDigitsOnly()) {
                                    onAmountValueChange(it)
                                }
                            },
                            textStyle = MaterialTheme.typography.bodySmall.copy(
                                textAlign = TextAlign.End,
                                fontSize = 13.sp
                            ),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done,
                                capitalization = KeyboardCapitalization.Sentences
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    focusManager.clearFocus()
                                }
                            ),
                            arrangement = Arrangement.End,
                            visualTransformation = NumberCommaTransformation()
                        )
                    }
                }
            )
        }
    )
}