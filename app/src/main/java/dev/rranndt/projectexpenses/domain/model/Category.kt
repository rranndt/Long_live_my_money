package dev.rranndt.projectexpenses.domain.model

import androidx.compose.ui.graphics.Color

data class Category(
    var categoryId: Int = 0,
    var name: String = "",
    var colorValue: String = "0,0,0",
) {
    val color: Color
        get() {
            val colorComponents = colorValue.split(",")
            val (red, green, blue) = colorComponents
            return Color(red.toFloat(), green.toFloat(), blue.toFloat())
        }

    constructor(
        name: String,
        color: Color,
    ) : this() {
        this.name = name
        this.colorValue = "${color.red},${color.green},${color.blue},"
    }
}
