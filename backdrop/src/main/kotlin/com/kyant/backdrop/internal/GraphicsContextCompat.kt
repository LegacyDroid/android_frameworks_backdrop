package com.kyant.backdrop.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.GraphicsContext
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.platform.LocalGraphicsContext

/**
 * Compat shim for [requireGraphicsContext] which doesn't exist in Compose UI 1.7.0.
 * In newer versions, this is a [DelegatableNode] extension that provides access to
 * [GraphicsContext] for creating/releasing [GraphicsLayer][androidx.compose.ui.graphics.layer.GraphicsLayer] instances.
 */
internal fun DelegatableNode.requireGraphicsContext(): GraphicsContext {
    return LocalGraphicsContext.current
}
