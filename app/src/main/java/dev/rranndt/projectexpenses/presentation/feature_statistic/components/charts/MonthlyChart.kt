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
import dev.rranndt.projectexpenses.core.utils.groupByDayOfMonth
import dev.rranndt.projectexpenses.core.utils.simplifyNumber
import dev.rranndt.projectexpenses.domain.model.Expense
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun MonthlyChart(
    expense: List<Expense>,
    month: LocalDate,
) {
    val groupedExpenses = expense.groupByDayOfMonth()
    val numberOfDays = YearMonth.of(month.year, month.month).lengthOfMonth()

    BarChart(
        barChartData = BarChartData(
            bars = buildList {
                for (i in 1..numberOfDays) {
                    add(
                        BarChartData.Bar(
                            label = "$i",
                            value = groupedExpenses[i]?.total?.toFloat() ?: 0f,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )
                }
            }
        ),
        labelDrawer = LabelDrawer(filter = Filter.Monthly),
        xAxisDrawer = SimpleXAxisDrawer(
            axisLineColor = Color.Transparent,
        ),
        yAxisDrawer = SimpleYAxisDrawer(
            labelTextColor = MaterialTheme.colorScheme.onBackground,
            labelValueFormatter = ::simplifyNumber,
            labelRatio = 4,
            labelTextSize = 10.sp,
            axisLineColor = MaterialTheme.colorScheme.onBackground
        ),
        barDrawer = BarDrawer(filter = Filter.Monthly),
        animation = simpleChartAnimation(),
        modifier = Modifier
            .fillMaxSize()
    )
}