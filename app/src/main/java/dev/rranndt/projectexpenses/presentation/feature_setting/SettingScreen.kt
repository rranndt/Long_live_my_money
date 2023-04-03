package dev.rranndt.projectexpenses.presentation.feature_setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.presentation.ui.components.CustomDivider
import dev.rranndt.projectexpenses.presentation.ui.components.TableRow
import dev.rranndt.projectexpenses.presentation.ui.components.TopBarContent
import dev.rranndt.projectexpenses.presentation.ui.navigation.Screen
import dev.rranndt.projectexpenses.presentation.ui.theme.Shapes
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    navController: NavController,
) {
    Scaffold(
        topBar = {
            TopBarContent(title = stringResource(id = R.string.title_setting_screen))
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium)
                    .clip(Shapes.small)
                    .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
            ) {
                TableRow(
                    iconLabel = painterResource(id = R.drawable.ic_category),
                    label = stringResource(id = R.string.title_categories_setting_screen),
                    hasArrow = true,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Screen.Categories.route)
                        }
                )
                CustomDivider()
                TableRow(
                    iconLabel = painterResource(id = R.drawable.ic_bug_report),
                    label = stringResource(id = R.string.title_report_setting_screen),
                    hasArrow = true,
                    modifier = Modifier
                        .clickable {
                            // TODO:
                        }
                )
                CustomDivider()
                TableRow(
                    iconLabel = painterResource(id = R.drawable.ic_theme),
                    label = stringResource(id = R.string.title_theme_setting_screen),
                    modifier = Modifier
                        .clickable {
                            // TODO:
                        }
                )
                CustomDivider()
                TableRow(
                    iconLabel = painterResource(id = R.drawable.ic_language),
                    label = stringResource(id = R.string.title_language_setting_screen),
                    modifier = Modifier
                        .clickable {
                            // TODO:
                        }
                )
                CustomDivider()
                TableRow(
                    iconLabel = painterResource(id = R.drawable.ic_info),
                    label = stringResource(id = R.string.title_app_info_setting_screen),
                    hasArrow = true,
                    modifier = Modifier
                        .clickable {
                            // TODO:
                        }
                )
                CustomDivider()
                TableRow(
                    iconLabel = painterResource(id = R.drawable.ic_delete),
                    label = stringResource(id = R.string.title_erase_setting_screen),
                    isDestructive = true,
                    modifier = Modifier
                        .clickable {
                            // TODO:
                        }
                )
            }
        }
    }
}