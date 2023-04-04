package dev.rranndt.projectexpenses.presentation.feature_statistic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.rranndt.projectexpenses.R
import dev.rranndt.projectexpenses.presentation.ui.components.TopBarContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticScreen() {
    Scaffold(
        topBar = {
            TopBarContent(title = stringResource(id = R.string.navigation_statistic), hasFilterButton = true)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {

        }
    }
}