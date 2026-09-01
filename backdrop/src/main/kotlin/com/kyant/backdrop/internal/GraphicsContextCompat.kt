package com.kyant.backdrop.internal

import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.graphics.AndroidGraphicsContext
import androidx.compose.ui.graphics.GraphicsContext
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.platform.LocalView

/**
 * Compat shim for requireGraphicsContext() which doesn't exist in Compose UI 1.7.0.
 * Creates AndroidGraphicsContext directly from the host view hierarchy.
 * Avoids LocalGraphicsContext which is not available at runtime (alpha03 wins classpath).
 */
internal fun CompositionLocalConsumerModifierNode.requireGraphicsContext(): GraphicsContext {
    val view = currentValueOf(LocalView)
    return createGraphicsContextFromView(view)
}

internal fun createGraphicsContextFromView(view: View): GraphicsContext {
    val viewGroup = view.findViewGroup()
    return AndroidGraphicsContext(viewGroup)
}

private fun View.findViewGroup(): ViewGroup {
    var current: View = this
    while (current.parent is View) {
        current = current.parent as View
    }
    return current as? ViewGroup
        ?: throw IllegalStateException("No ViewGroup found in view hierarchy")
}
