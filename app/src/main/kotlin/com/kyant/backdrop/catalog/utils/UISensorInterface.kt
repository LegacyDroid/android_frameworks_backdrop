package com.kyant.backdrop.catalog.utils

import androidx.compose.ui.geometry.Offset

interface UISensor {
    val gravityAngle: Float
    val gravity: Offset
    fun start()
    fun stop()
}
