package dev.rranndt.projectexpenses.presentation.ui.navigation.bottom_bar

import dev.rranndt.projectexpenses.presentation.ui.navigation.Screen

data class BottomBarItem(
    val label: String,
    val icon: Int,
    val iconFocused: Int,
    val screen: Screen,
)