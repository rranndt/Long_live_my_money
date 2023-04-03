package dev.rranndt.projectexpenses.presentation.ui.navigation.bottom_bar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.presentation.ui.navigation.Screen

@Composable
fun BottomBar(
    navController: NavController,
) {
    NavigationBar {
        val bottomBarItems = listOf(
            BottomBarItem(
                label = stringResource(id = R.string.navigation_expense),
                icon = R.drawable.ic_expense,
                iconFocused = R.drawable.ic_expense_focused,
                screen = Screen.Expense
            ),
            BottomBarItem(
                label = stringResource(id = R.string.navigation_statistic),
                icon = R.drawable.ic_activity,
                iconFocused = R.drawable.ic_activity_focused,
                screen = Screen.Statistic
            ),
            BottomBarItem(
                label = stringResource(id = R.string.navigation_setting),
                icon = R.drawable.ic_setting,
                iconFocused = R.drawable.ic_setting_focused,
                screen = Screen.Setting
            )
        )
        NavigationBar {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            bottomBarItems.map { item ->
                val selected = navBackStackEntry?.destination?.hierarchy?.any {
                    it.route == item.screen.route
                } == true
                NavigationBarItem(
                    selected = currentRoute == item.screen.route,
                    label = {
                        Text(
                            text = item.label,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = if (selected) item.iconFocused else item.icon),
                            contentDescription = item.label
                        )
                    },
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                )
            }
        }
    }
}