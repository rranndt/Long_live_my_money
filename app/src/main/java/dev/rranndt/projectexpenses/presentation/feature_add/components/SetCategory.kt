package dev.rranndt.projectexpenses.presentation.feature_add.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.rranndt.projectexpenses.domain.model.Category
import dev.rranndt.projectexpenses.presentation.feature_categories.CategoryViewModel
import dev.rranndt.projectexpenses.presentation.ui.components.TableRow
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing

@Composable
fun SetCategory(
    label: String,
    categoryName: String,
    categoryColor: Color,
    onSetCategory: (Category) -> Unit,
    categoryMenuOpened: MutableState<Boolean>,
    categoryViewModel: CategoryViewModel,
) {
    val categories = categoryViewModel.getCategories().collectAsState(initial = listOf()).value

    TableRow(
        label = label,
        labelStyle = MaterialTheme.typography.bodyMedium,
        labelWeight = FontWeight.Medium,
        minHeight = 50.dp,
        content = {
            TextButton(
                onClick = {
                    categoryMenuOpened.value = true
                }
            ) {
                Text(
                    text = categoryName,
                    color = categoryColor,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp),
                    modifier = Modifier
                        .padding(end = MaterialTheme.spacing.small)
                )
                DropdownMenu(
                    expanded = categoryMenuOpened.value,
                    onDismissRequest = {
                        categoryMenuOpened.value = false
                    }
                ) {
                    categories.forEach { category ->
                        DropdownMenuItem(
                            text = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Surface(
                                        modifier = Modifier
                                            .size(MaterialTheme.spacing.small),
                                        shape = CircleShape,
                                        color = category.color
                                    ) {}
                                    Text(
                                        text = category.name,
                                        color = MaterialTheme.colorScheme.onBackground,
                                        fontWeight = FontWeight.Normal,
                                        modifier = Modifier
                                            .padding(start = MaterialTheme.spacing.small)
                                    )
                                }
                            },
                            onClick = {
                                onSetCategory(category)
                                categoryMenuOpened.value = false
                            }
                        )
                    }
                }
            }
        }
    )
}