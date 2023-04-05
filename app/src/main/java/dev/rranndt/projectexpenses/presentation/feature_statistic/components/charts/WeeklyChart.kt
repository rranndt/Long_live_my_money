package dev.rranndt.projectexpenses.presentation.feature_statistic.components.charts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.BarChartData.Bar
import com.github.tehras.charts.bar.renderer.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.bar.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import dev.rranndt.projectexpenses.core.utils.Filter
import dev.rranndt.projectexpenses.core.utils.groupByDayOfWeek
import dev.rranndt.projectexpenses.core.utils.simplifyNumber
import dev.rranndt.projectexpenses.domain.model.Expense
import java.time.DayOfWeek

@Composable
fun WeeklyChart(
    expenses: List<Expense>,
) {
    val groupedExpenses = expenses.groupByDayOfWeek()

    BarChart(
        barChartData = BarChartData(
            bars = listOf(
                Bar(
                    label = DayOfWeek.MONDAY.name.substring(0, 1),
                    value = groupedExpenses[DayOfWeek.MONDAY.name]?.total?.toFloat() ?: 0f,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                Bar(
                    label = DayOfWeek.TUESDAY.name.substring(0, 1),
                    value = groupedExpenses[DayOfWeek.TUESDAY.name]?.total?.toFloat() ?: 0f,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                Bar(
                    label = DayOfWeek.WEDNESDAY.name.substring(0, 1),
                    value = groupedExpenses[DayOfWeek.WEDNESDAY.name]?.total?.toFloat() ?: 0f,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                Bar(
                    label = DayOfWeek.THURSDAY.name.substring(0, 1),
                    value = groupedExpenses[DayOfWeek.THURSDAY.name]?.total?.toFloat() ?: 0f,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                Bar(
                    label = DayOfWeek.FRIDAY.name.substring(0, 1),
                    value = groupedExpenses[DayOfWeek.FRIDAY.name]?.total?.toFloat() ?: 0f,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                Bar(
                    label = DayOfWeek.SATURDAY.name.substring(0, 1),
                    value = groupedExpenses[DayOfWeek.SATURDAY.name]?.total?.toFloat() ?: 0f,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                Bar(
                    label = DayOfWeek.SUNDAY.name.substring(0, 1),
                    value = groupedExpenses[DayOfWeek.SUNDAY.name]?.total?.toFloat() ?: 0f,
                    color = MaterialTheme.colorScheme.onBackground
                ),
            ),
        ),
        labelDrawer = LabelDrawer(filter = Filter.Weekly),
        xAxisDrawer = SimpleXAxisDrawer(
            axisLineColor = Color.Transparent,
        ),
        yAxisDrawer = SimpleYAxisDrawer(
            labelTextColor = MaterialTheme.colorScheme.onBackground,
            labelValueFormatter = ::simplifyNumber,
            labelRatio = 4,
            labelTextSize = 10.sp,
            axisLineColor = MaterialTheme.colorScheme.onBackground,
        ),
        barDrawer = BarDrawer(filter = Filter.Weekly),
        animation = simpleChartAnimation(),
        modifier = Modifier
            .fillMaxWidth()
    )
}