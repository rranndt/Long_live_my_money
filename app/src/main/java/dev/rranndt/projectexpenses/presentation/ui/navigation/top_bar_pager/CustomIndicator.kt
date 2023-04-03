package dev.rranndt.projectexpenses.presentation.ui.navigation.top_bar_pager

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CustomIndicator(
    tabPosition: List<TabPosition>,
    pagerState: PagerState,
) {
    val transition = updateTransition(pagerState.currentPage, label = "")
    val indicatorStart by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 400f)
            } else {
                spring(dampingRatio = 1f, stiffness = 1500f)
            }
        },
        label = ""
    ) {
        tabPosition[it].left
    }

    val indicatorEnd by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 1500f)
            } else {
                spring(dampingRatio = 1f, stiffness = 400f)
            }
        },
        label = ""
    ) {
        tabPosition[it].right
    }

    Box(
        modifier = Modifier
            .offset(x = indicatorStart)
            .wrapContentSize(align = Alignment.BottomStart)
            .width(indicatorEnd - indicatorStart)
            .padding(2.dp)
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                RoundedCornerShape(MaterialTheme.spacing.large)
            )
            .zIndex(1f)
    )
}