package com.adaptive.components

import android.content.Context
import android.graphics.Point
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize

actual val LocalScreenSize: IntSize
    @Composable
    @NonRestartableComposable
    get() = with(LocalContext.current) {
        val point = Point()
        val display = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        display.getRealSize(point)
        val (width, height) = if (point.y > point.x) point.x to point.y else point.y to point.x
        IntSize(width, height)
    }
