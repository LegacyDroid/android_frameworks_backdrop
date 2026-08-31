package com.kyant.backdrop.catalog.utils

import androidx.activity.compose.BackHandler as AndroidBackHandler
import androidx.compose.runtime.Composable

@Composable
fun BackHandler(
    enabled: Boolean,
    onBack: () -> Unit
) {
    AndroidBackHandler(enabled, onBack)
}
