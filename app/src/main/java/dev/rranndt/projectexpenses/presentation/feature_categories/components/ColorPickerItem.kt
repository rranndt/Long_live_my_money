package dev.rranndt.projectexpenses.presentation.feature_categories.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.ColorPickerController
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.presentation.feature_categories.state.CategoryState
import dev.rranndt.projectexpenses.presentation.ui.theme.Shapes
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing

@Composable
fun ColorPickerItem(
    state: CategoryState,
    colorPickerController: ColorPickerController,
    onHideColorPicker: () -> Unit,
    onCategoryColorValue: (Color) -> Unit,
) {
    if (state.isColorPickerShowing) {
        Dialog(
            onDismissRequest = onHideColorPicker
        ) {
            Surface(
                color = MaterialTheme.colorScheme.background,
                shape = Shapes.small
            ) {
                Column(
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.large)
                ) {
                    Text(
                        text = stringResource(id = R.string.title_select_color_category_screen),
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontSize = 18.sp
                        ),
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                    HsvColorPicker(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        controller = colorPickerController,
                        onColorChanged = { colorEnvelope ->
                            onCategoryColorValue(colorEnvelope.color)
                        }
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BrightnessSlider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(MaterialTheme.spacing.large)
                                .weight(1f),
                            controller = colorPickerController
                        )
                        AlphaTile(
                            modifier = Modifier
                                .padding(start = MaterialTheme.spacing.small)
                                .size(MaterialTheme.spacing.large)
                                .clip(RoundedCornerShape(MaterialTheme.spacing.small)),
                            controller = colorPickerController,
                        )
                    }
                    TextButton(
                        onClick = onHideColorPicker,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(MaterialTheme.spacing.medium)
                    ) {
                        Text(text = stringResource(id = R.string.title_done_category_screen))
                    }
                }
            }
        }
    }
}