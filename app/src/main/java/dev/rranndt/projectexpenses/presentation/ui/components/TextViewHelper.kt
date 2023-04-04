package dev.rranndt.projectexpenses.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import dev.rranndt.projectexpenses.presentation.ui.theme.Typography

@Composable
fun TextViewHelper(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    style: TextStyle = Typography.titleSmall.copy(fontSize = 14.sp),
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    color: Color = MaterialTheme.colorScheme.onSurface,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = horizontalAlignment
    ) {
        Text(
            text = text,
            textAlign = textAlign,
            style = style,
            color = color
        )
    }
}