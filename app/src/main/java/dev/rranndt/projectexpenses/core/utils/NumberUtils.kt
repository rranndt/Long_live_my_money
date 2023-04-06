package dev.rranndt.projectexpenses.core.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

fun Long?.formatWithComma(): String =
    NumberFormat.getNumberInstance(Locale.getDefault()).format(this ?: 0)

class NumberCommaTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            text = AnnotatedString(text.text.toLongOrNull().formatWithComma()),
            offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return text.text.toLongOrNull().formatWithComma().length
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return text.length
                }
            }
        )
    }
}

fun Double.localCurrencyFormat(): String {
    return NumberFormat.getNumberInstance(Locale.getDefault())
        .format(this)
}

fun Double.simplifyNumber(): String {
    return when {
        this >= 1000 -> DecimalFormat("0k").format(this / 1000)
        else -> DecimalFormat("0").format(this)
    }
}

fun simplifyNumber(value: Float): String {
    return when {
        value >= 1000 -> DecimalFormat("0k").format(value / 1000)
        else -> DecimalFormat("0").format(value)
    }
}