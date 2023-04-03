package dev.rranndt.projectexpenses.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import dev.rranndt.projectexpenses.presentation.ui.theme.spacing

@Composable
fun NoDataView(
    text: String,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("animation/no_data.json"))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = horizontalAlignment,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp)
                .padding(horizontal = MaterialTheme.spacing.extraLarge),
        ) {
            LottieAnimation(
                composition = composition,
                progress = { progress },
            )
        }
        TextViewHelper(
            text = text,
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium)
                .padding(top = MaterialTheme.spacing.small)
        )
    }
}