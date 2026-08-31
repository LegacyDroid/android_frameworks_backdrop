package com.kyant.backdrop.internal

import androidx.compose.runtime.CompositionLocal
import androidx.compose.ui.graphics.GraphicsContext
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.currentValueOf

/**
 * Compat shim for requireGraphicsContext() which doesn't exist in Compose UI 1.7.0.
 * LocalGraphicsContext exists in bytecode but isn't importable with compose-compiler 1.5.9.
 * Obtains it via reflection and reads the current value through the node's composition context.
 */
internal fun CompositionLocalConsumerModifierNode.requireGraphicsContext(): GraphicsContext {
    @Suppress("UNCHECKED_CAST")
    val local = Class.forName("androidx.compose.ui.platform.CompositionLocalsKt")
        .getDeclaredMethod("getLocalGraphicsContext")
        .invoke(null) as CompositionLocal<GraphicsContext>
    return currentValueOf(local)
}
