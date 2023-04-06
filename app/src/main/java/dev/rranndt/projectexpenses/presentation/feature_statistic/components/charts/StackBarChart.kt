package dev.rranndt.projectexpenses.presentation.feature_statistic.components.charts

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay

@Composable
fun StackBarChart(
    modifier: Modifier = Modifier,
    slices: List<Slice>,
) {
    var state by remember { mutableStateOf(.0f) }
    val width = animateFloatAsState(targetValue = state, animationSpec = tween(300))

    LaunchedEffect(key1 = Unit, block = {
        delay(1000L)
        state = 1f
    })

    Row(modifier = modifier) {
        slices.forEach {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(it.value),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(it.color),
                    contentAlignment = Alignment.Center
                ) {}
            }
        }
    }
}

data class Slice(
    val value: Float,
    val color: Color,
)