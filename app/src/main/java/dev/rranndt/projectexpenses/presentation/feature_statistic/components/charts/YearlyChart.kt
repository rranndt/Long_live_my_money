package dev.rranndt.projectexpenses.presentation.feature_statistic.components.charts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.renderer.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.bar.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import dev.rranndt.projectexpenses.core.utils.Filter
import dev.rranndt.projectexpenses.core.utils.groupByMonthOfYear
import dev.rranndt.projectexpenses.core.utils.simplifyNumber
import dev.rranndt.projectexpenses.domain.model.Expense
import java.time.Month

@Composable
fun YearlyChart(
    expense: List<Expense>,
) {
    val groupedExpenses = expense.groupByMonthOfYear()

    BarChart(
        barChartData = BarChartData(
            bars = listOf(
                BarChartData.Bar(
                    label = Month.JANUARY.name.substring(0, 1),
                    value = groupedExpenses[Month.JANUARY.name]?.total?.toFloat() ?: 0f,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                BarChartData.Bar(
                    label = Month.FEBRUARY.name.substring(0, 1),
                    value = groupedExpenses[Month.FEBRUARY.name]?.total?.toFloat() ?: 0f,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                BarChartData.Bar(
                    label = Month.MARCH.name.substring(0, 1),
                    value = groupedExpenses[Month.MARCH.name]?.total?.toFloat() ?: 0f,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                BarChartData.Bar(
                    label = Month.APRIL.name.substring(0, 1),
                    value = groupedExpenses[Month.APRIL.name]?.total?.toFloat() ?: 0f,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                BarChartData.Bar(
                    label = Month.MAY.name.substring(0, 1),
                    value = groupedExpenses[Month.MAY.name]?.total?.toFloat() ?: 0f,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                BarChartData.Bar(
                    label = Month.JUNE.name.substring(0, 1),
                    value = groupedExpenses[Month.JUNE.name]?.total?.toFloat() ?: 0f,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                BarChartData.Bar(
                    label = Month.JULY.name.substring(0, 1),
                    value = groupedExpenses[Month.JULY.name]?.total?.toFloat() ?: 0f,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                BarChartData.Bar(
                    label = Month.AUGUST.name.substring(0, 1),
                    value = groupedExpenses[Month.AUGUST.name]?.total?.toFloat() ?: 0f,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                BarChartData.Bar(
                    label = Month.SEPTEMBER.name.substring(0, 1),
                    value = groupedExpenses[Month.SEPTEMBER.name]?.total?.toFloat() ?: 0f,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                BarChartData.Bar(
                    label = Month.OCTOBER.name.substring(0, 1),
                    value = groupedExpenses[Month.OCTOBER.name]?.total?.toFloat() ?: 0f,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                BarChartData.Bar(
                    label = Month.NOVEMBER.name.substring(0, 1),
                    value = groupedExpenses[Month.NOVEMBER.name]?.total?.toFloat() ?: 0f,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                BarChartData.Bar(
                    label = Month.DECEMBER.name.substring(0, 1),
                    value = groupedExpenses[Month.DECEMBER.name]?.total?.toFloat() ?: 0f,
                    color = MaterialTheme.colorScheme.onBackground
                ),
            )
        ),
        labelDrawer = LabelDrawer(filter = Filter.Yearly),
        xAxisDrawer = SimpleXAxisDrawer(
            axisLineColor = Color.Transparent
        ),
        yAxisDrawer = SimpleYAxisDrawer(
            labelTextColor = MaterialTheme.colorScheme.onBackground,
            labelValueFormatter = ::simplifyNumber,
            labelRatio = 4,
            labelTextSize = 10.sp,
            axisLineColor = MaterialTheme.colorScheme.onBackground
        ),
        barDrawer = BarDrawer(filter = Filter.Yearly),
        animation = simpleChartAnimation(),
        modifier = Modifier
            .fillMaxSize()
    )
}