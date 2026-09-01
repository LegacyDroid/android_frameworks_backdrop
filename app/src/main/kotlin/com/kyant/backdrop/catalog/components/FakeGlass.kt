package com.kyant.backdrop.catalog.components

import android.graphics.Paint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kyant.shapes.RoundedRectangle

/**
 * Lightweight glass-like surface for battery saver / low-end devices.
 *
 * Draws a semi-transparent tinted background + thin white highlight border.
 * No backdrop system, no blur, no lens, no vibrancy — just Canvas draws.
 * Works on all API levels with minimal GPU cost.
 */
@Composable
fun FakeGlass(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedRectangle(24.dp),
    surfaceColor: Color = Color.White.copy(alpha = 0.12f),
    highlightColor: Color = Color.White.copy(alpha = 0.38f),
    highlightWidth: Dp = 0.5f.dp,
    cornerRadius: Dp = 24.dp,
    content: @Composable BoxScope.() -> Unit
) {
    val strokePaint = Paint().apply {
        style = Paint.Style.STROKE
        color = highlightColor.toArgb()
        isAntiAlias = true
    }

    Box(
        modifier
            .clip(shape)
            .drawBehind {
                drawRect(surfaceColor)

                val strokeWidth = highlightWidth.toPx()
                strokePaint.strokeWidth = strokeWidth

                val halfStroke = strokeWidth / 2f
                val cornerRadiusPx = cornerRadius.toPx()

                drawIntoCanvas { canvas ->
                    canvas.nativeCanvas.drawRoundRect(
                        halfStroke,
                        halfStroke,
                        size.width - halfStroke,
                        size.height - halfStroke,
                        cornerRadiusPx,
                        cornerRadiusPx,
                        strokePaint
                    )
                }
            },
        content = content
    )
}
