package dev.rranndt.projectexpenses.presentation.feature_statistic.components.charts

import android.graphics.Paint
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.sp
import com.github.tehras.charts.bar.renderer.label.LabelDrawer
import com.github.tehras.charts.piechart.utils.toLegacyInt
import dev.rranndt.projectexpenses.core.utils.Filter

class LabelDrawer(
    private val lastDay: Int? = -1,
    val filter: Filter,
) : LabelDrawer {

    /*private val leftOffset = when (filter) {
        Filter.Weekly -> 50f
        Filter.Monthly -> 14f
        Filter.Yearly -> 32f
        else -> 0f
    }*/

    private fun paint(drawScope: DrawScope) = with(drawScope) {
        Paint().apply {
            this.textSize = 10.sp.toPx()
            this.color = Color.White.toLegacyInt()
        }
    }

    override fun drawLabel(
        drawScope: DrawScope,
        canvas: Canvas,
        label: String,
        barArea: Rect,
        xAxisArea: Rect,
    ) = with(drawScope) {
        val monthlyCondition =
            filter == Filter.Monthly &&
                    (Integer.parseInt(label) % 5 == 0 ||
                            Integer.parseInt(label) == 1 ||
                            Integer.parseInt(label) == lastDay)
        if (monthlyCondition || filter != Filter.Monthly) {
            canvas.nativeCanvas.drawText(
                label,
                barArea.left + (barArea.width / 2),
                barArea.bottom - 20f,
                paint(drawScope)
            )
        }
    }
}