package dev.rranndt.projectexpenses.presentation.feature_expense.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.rranndt.projectexpenses.domain.model.Category
import dev.rranndt.projectexpenses.domain.model.Expense
import dev.rranndt.projectexpenses.presentation.ui.theme.Shapes
import java.text.NumberFormat
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Created by rranndt on 28/03/2023
 */
@Composable
fun ExpenseRowItem(
    expense: Expense,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = expense.description,
                style = MaterialTheme.typography.bodySmall,
            )
            Text(
                text = "Rp ${
                    NumberFormat.getNumberInstance(Locale.getDefault()).format(expense.amount)
                }",
                style = MaterialTheme.typography.bodySmall,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            expense.category?.let {
                CategoryBadgeItem(
                    category = it
                )
            }
            Text(
                text = expense.date.format(DateTimeFormatter.ofPattern("HH:mm")),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Red
            )
        }
    }
}

@Composable
fun CategoryBadgeItem(
    category: Category,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = Shapes.large,
        color = category.color.copy(alpha = 0.25f),
        modifier = modifier
    ) {
        Text(
            text = category.name,
            style = MaterialTheme.typography.bodySmall,
            color = category.color,
            modifier = Modifier
                .padding(horizontal = 6.dp, vertical = 2.dp)
        )
    }
}