package dev.rranndt.projectexpenses.presentation.feature_categories.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.domain.model.Category
import dev.rranndt.projectexpenses.presentation.feature_categories.state.CategoryState
import dev.rranndt.projectexpenses.presentation.ui.components.CustomDivider
import dev.rranndt.projectexpenses.presentation.ui.components.TableRow
import dev.rranndt.projectexpenses.presentation.ui.theme.Shapes
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun ListingCategory(
    state: CategoryState,
    data: List<Category>,
    onCategoryNameClick: (String) -> Unit,
    onCategoryColorClick: (Color) -> Unit,
    onDeleteItem: (Category) -> Unit,
) {
    Column {
        AnimatedVisibility(
            visible = true
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(Shapes.medium),
                contentPadding = PaddingValues(MaterialTheme.spacing.medium)
            ) {
                itemsIndexed(
                    items = data,
                    key = { _: Int, category: Category -> category.categoryId }
                ) { index: Int, category: Category ->
                    SwipeableActionsBox(
                        swipeThreshold = 200.dp,
                        endActions = listOf(
                            SwipeAction(
                                icon = painterResource(id = R.drawable.ic_delete),
                                background = MaterialTheme.colorScheme.error,
                                onSwipe = {
                                    onDeleteItem(category)
                                }
                            )
                        )
                    ) {
                        TableRow(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
                                .clickable {
                                    onCategoryNameClick(category.name)
                                    onCategoryColorClick(category.color)
                                },
                            minHeight = 40.dp,
                            content = {
                                Row(
                                    modifier = Modifier
                                        .padding(horizontal = MaterialTheme.spacing.medium),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Surface(
                                        color = category.color,
                                        shape = CircleShape,
                                        modifier = Modifier
                                            .size(MaterialTheme.spacing.medium)
                                    ) {}
                                    Text(
                                        text = category.name,
                                        style = MaterialTheme.typography.bodySmall,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        color = MaterialTheme.colorScheme.onBackground,
                                        modifier = Modifier
                                            .padding(
                                                horizontal = MaterialTheme.spacing.medium,
                                                vertical = MaterialTheme.spacing.small
                                            )
                                    )
                                }
                            }
                        )
                    }
                    if (index < state.categories.size - 1) {
                        Row(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
                                .height(1.dp)
                                .clip(Shapes.medium)
                        ) {
                            CustomDivider()
                        }
                    }
                }
            }
        }
    }
}