package dev.rranndt.projectexpenses.presentation.feature_statistic.components.charts

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.renderer.bar.BarDrawer
import dev.rranndt.projectexpenses.core.utils.Filter
import dev.rranndt.projectexpenses.presentation.ui.theme.BarColor
import dev.rranndt.projectexpenses.presentation.ui.theme.BarColorEmpty

class BarDrawer(filter: Filter) : BarDrawer {

    private val barPaint = Paint().apply {
        this.isAntiAlias = true
    }

    private val rightOffset = when (filter) {
        Filter.Weekly -> 24f
        Filter.Monthly -> 6f
        Filter.Yearly -> 18f
        else -> 0f
    }

    override fun drawBar(
        drawScope: DrawScope,
        canvas: Canvas,
        barArea: Rect,
        bar: BarChartData.Bar,
    ) {
        canvas.drawRoundRect(
            left = barArea.left,
            top = 0f,
            right = barArea.right + rightOffset,
            bottom = barArea.bottom,
            radiusX = 8f,
            radiusY = 8f,
            barPaint.apply {
                color = BarColorEmpty
            }
        )

        canvas.drawRoundRect(
            left = barArea.left,
            top = barArea.top,
            right = barArea.right + rightOffset,
            bottom = barArea.bottom,
            radiusX = 8f,
            radiusY = 8f,
            barPaint.apply {
                color = BarColor
            }
        )
    }
}