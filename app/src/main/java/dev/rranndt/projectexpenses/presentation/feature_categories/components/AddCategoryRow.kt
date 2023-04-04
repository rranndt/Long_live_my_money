package dev.rranndt.projectexpenses.presentation.feature_categories.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.ColorPickerController
import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.presentation.feature_categories.state.CategoryState
import dev.rranndt.projectexpenses.presentation.ui.components.CustomTextField
import dev.rranndt.projectexpenses.presentation.ui.components.TableRow
import dev.rranndt.projectexpenses.presentation.ui.theme.Shapes
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddCategoryRow(
    state: CategoryState,
    onShowColorPicker: () -> Unit,
    onCategoryNameValue: (String) -> Unit,
    onCategoryColorValue: (Color) -> Unit,
    onClearText: () -> Unit,
    onInsertCategory: () -> Unit,
    focusManager: FocusManager = LocalFocusManager.current,
    colorPickerController: ColorPickerController,
    onHideColorPicker: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = MaterialTheme.spacing.medium,
                start = MaterialTheme.spacing.medium,
                end = MaterialTheme.spacing.small
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Surface(
            shape = CircleShape,
            color = state.categoryColor,
            onClick = onShowColorPicker,
            modifier = Modifier
                .size(width = 24.dp, height = 24.dp)
                .align(Alignment.CenterVertically),
        ) {
            ColorPickerItem(
                state = state,
                colorPickerController = colorPickerController,
                onHideColorPicker = onHideColorPicker,
                onCategoryColorValue = onCategoryColorValue
            )
        }
        Surface(
            color = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
            shape = Shapes.small,
            modifier = Modifier
                .weight(1f)
                .padding(start = MaterialTheme.spacing.medium)
        ) {
            TableRow(
                content = {
                    CustomTextField(
                        value = state.categoryName,
                        onValueChange = {
                            if (it.length <= 30) {
                                onCategoryNameValue(it)
                            }
                            onCategoryColorValue(state.categoryColor)
                        },
                        placeholder = {
                            Text(text = stringResource(id = R.string.title_placeholder_category_screen))
                        },
                        singleLine = true,
                        shape = Shapes.small,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Send,
                            capitalization = KeyboardCapitalization.Sentences
                        ),
                        keyboardActions = KeyboardActions(
                            onSend = {
                                onInsertCategory()
                                focusManager.clearFocus()
                            },
                        )
                    )
                },
                trailingIcon = {
                    if (state.categoryName.isNotEmpty()) {
                        IconButton(
                            onClick = onClearText
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_clear),
                                contentDescription = stringResource(id = R.string.content_description_clear_icon_category_screen)
                            )
                        }
                    }
                }
            )
        }
        IconButton(
            onClick = {
                onInsertCategory()
                focusManager.clearFocus()
            },
            enabled = state.categoryName.isNotEmpty(),
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_send),
                contentDescription = stringResource(id = R.string.content_description_send_icon_category_screen),
                modifier = Modifier
                    .defaultMinSize(minHeight = 28.dp, minWidth = 28.dp)
            )
        }
    }
}