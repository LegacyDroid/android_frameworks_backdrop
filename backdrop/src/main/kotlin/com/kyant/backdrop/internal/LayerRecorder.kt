package com.kyant.backdrop.internal

import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.requireDensity
import androidx.compose.ui.unit.IntSize

internal fun DrawScope.recordLayer(
    node: DelegatableNode,
    layer: GraphicsLayer,
    size: IntSize = IntSize(this.size.width.toInt(), this.size.height.toInt()),
    block: DrawScope.() -> Unit
) {
    val density = node.requireDensity()
    layer.record(density, layoutDirection, size) {
        val outerDrawContext = this@recordLayer.drawContext
        val layerDrawContext = this.drawContext

        val prevCanvas = outerDrawContext.canvas
        val prevDensity = outerDrawContext.density
        val prevLayoutDirection = outerDrawContext.layoutDirection
        val prevSize = outerDrawContext.size
        val prevGraphicsLayer = outerDrawContext.graphicsLayer

        outerDrawContext.canvas = layerDrawContext.canvas
        outerDrawContext.density = layerDrawContext.density
        outerDrawContext.layoutDirection = layerDrawContext.layoutDirection
        outerDrawContext.size = layerDrawContext.size
        outerDrawContext.graphicsLayer = layerDrawContext.graphicsLayer

        try {
            this.block()
        } finally {
            outerDrawContext.canvas = prevCanvas
            outerDrawContext.density = prevDensity
            outerDrawContext.layoutDirection = prevLayoutDirection
            outerDrawContext.size = prevSize
            outerDrawContext.graphicsLayer = prevGraphicsLayer
        }
    }
}
