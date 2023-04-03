package dev.rranndt.projectexpenses.presentation.feature_expense.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import dev.rranndt.projectexpenses.core.utils.DayExpense
import dev.rranndt.projectexpenses.core.utils.formatDay
import java.text.NumberFormat
import java.time.LocalDate
import java.util.*

@Composable
fun ExpenseGrouping(
    date: LocalDate,
    dayExpenses: DayExpense,
) {
    val context = LocalContext.current

    Column {
        Text(
            text = date.formatDay(context),
            style = MaterialTheme.typography.bodySmall,
            color = Color.Yellow
        )
        Divider(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 4.dp)
        )
        dayExpenses.expenses.forEach { expense ->
            ExpenseRowItem(
                expense = expense,
                modifier = Modifier
                    .padding(top = 12.dp)
            )
        }
        Divider(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Total",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Yellow
            )
            Text(
                text = NumberFormat.getNumberInstance(Locale.getDefault())
                    .format(dayExpenses.total),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Cyan
            )
        }
    }
}