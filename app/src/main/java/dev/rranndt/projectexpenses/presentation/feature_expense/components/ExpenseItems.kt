package dev.rranndt.projectexpenses.presentation.feature_expense.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.rranndt.projectexpenses.core.utils.localCurrencyFormat
import dev.rranndt.projectexpenses.domain.model.Expense
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing
import java.time.format.DateTimeFormatter

@Composable
fun ExpenseItems(
    description: String,
    amount: Double,
    expense: Expense,
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp))
            .padding(PaddingValues(MaterialTheme.spacing.medium))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                ),
                maxLines = 5,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = MaterialTheme.spacing.medium)
            )
            Text(
                text = "IDR ${amount.localCurrencyFormat()}",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            expense.category?.let {
                CategoryBadge(
                    categoryName = it.name,
                    categoryColor = it.color.copy(0.3f)
                )
            }
            Text(
                text = expense.date.format(DateTimeFormatter.ofPattern("HH:mm")),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 12.sp
                ),
            )
        }
    }
}