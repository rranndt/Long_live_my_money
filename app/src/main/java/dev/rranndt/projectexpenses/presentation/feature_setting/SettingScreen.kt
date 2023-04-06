package dev.rranndt.projectexpenses.presentation.feature_setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.presentation.ui.components.CustomDivider
import dev.rranndt.projectexpenses.presentation.ui.components.TableRow
import dev.rranndt.projectexpenses.presentation.ui.components.TopBarContent
import dev.rranndt.projectexpenses.presentation.ui.navigation.Screen
import dev.rranndt.projectexpenses.presentation.ui.theme.Shapes
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    navController: NavController,
    viewModel: SettingViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopBarContent(title = stringResource(id = R.string.title_setting_screen))
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState) {
                Snackbar(
                    snackbarData = it,
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
                    contentColor = MaterialTheme.colorScheme.onBackground
                )
            }
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
                            // TODO: Just form page to send the message
                            viewModel.showSnackBar()
                        }
                )
                CustomDivider()
                TableRow(
                    iconLabel = painterResource(id = R.drawable.ic_theme),
                    label = stringResource(id = R.string.title_theme_setting_screen),
                    modifier = Modifier
                        .clickable {
                            // TODO: Change theme to dark/light/by system default
                            viewModel.showSnackBar()
                        }
                )
                CustomDivider()
                TableRow(
                    iconLabel = painterResource(id = R.drawable.ic_language),
                    label = stringResource(id = R.string.title_language_setting_screen),
                    modifier = Modifier
                        .clickable {
                            // TODO: Choose language, at least english and indonesia
                            viewModel.showSnackBar()
                        }
                )
                CustomDivider()
                TableRow(
                    iconLabel = painterResource(id = R.drawable.ic_backup_restore),
                    label = stringResource(id = R.string.title_backup_restore_setting_screen),
                    modifier = Modifier
                        .clickable {
                            // TODO: Backup and restore the database
                            viewModel.showSnackBar()
                        }
                )
                CustomDivider()
                TableRow(
                    iconLabel = painterResource(id = R.drawable.ic_export),
                    label = stringResource(id = R.string.title_export_setting_screen),
                    modifier = Modifier
                        .clickable {
                            // TODO: Export file to csv
                            viewModel.showSnackBar()
                        }
                )
                CustomDivider()
                TableRow(
                    iconLabel = painterResource(id = R.drawable.ic_info),
                    label = stringResource(id = R.string.title_app_info_setting_screen),
                    modifier = Modifier
                        .clickable {
                            // TODO: Show the standard app info
                            viewModel.showSnackBar()
                        }
                )
                CustomDivider()
                TableRow(
                    iconLabel = painterResource(id = R.drawable.ic_delete),
                    label = stringResource(id = R.string.title_erase_setting_screen),
                    isDestructive = true,
                    modifier = Modifier
                        .clickable {
                            // TODO: Erase all the data in the app
                            viewModel.showSnackBar()
                        }
                )
            }
        }
    }
}