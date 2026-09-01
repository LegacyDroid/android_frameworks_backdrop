package com.kyant.backdrop.internal

import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.graphics.GraphicsContext
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.platform.LocalView
import java.lang.reflect.Constructor

/**
 * Compat shim for requireGraphicsContext() which doesn't exist in Compose UI 1.7.0.
 * Creates AndroidGraphicsContext via reflection from the host view hierarchy.
 * AndroidGraphicsContext is internal in Kotlin metadata so we access it via reflection.
 */
internal fun CompositionLocalConsumerModifierNode.requireGraphicsContext(): GraphicsContext {
    val view = currentValueOf(LocalView)
    return createGraphicsContextFromView(view)
}

internal fun createGraphicsContextFromView(view: View): GraphicsContext {
    val viewGroup = view.findViewGroup()
    val clazz = Class.forName("androidx.compose.ui.graphics.AndroidGraphicsContext")
    val ctor = clazz.getDeclaredConstructor(ViewGroup::class.java)
    ctor.isAccessible = true
    @Suppress("UNCHECKED_CAST")
    return ctor.newInstance(viewGroup) as GraphicsContext
}

private fun View.findViewGroup(): ViewGroup {
    var current: View = this
    while (current.parent is View) {
        current = current.parent as View
    }
    return current as? ViewGroup
        ?: throw IllegalStateException("No ViewGroup found in view hierarchy")
}
