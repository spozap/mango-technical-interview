package dev.spozap.core.ui.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun LoadingWheel(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier.testTag("loadingWheel")
    )
}