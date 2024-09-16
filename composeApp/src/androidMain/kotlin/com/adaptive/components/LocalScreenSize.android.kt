package com.adaptive.components

import android.app.Activity
import android.graphics.Rect
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import androidx.window.layout.WindowMetricsCalculator

actual val LocalScreenSize: IntSize
    @Composable
    @NonRestartableComposable
    get() = with(LocalContext.current as Activity) {
        remember {
            WindowMetricsCalculator.getOrCreate()
                .computeCurrentWindowMetrics(activity = this)
                .bounds.toSize()
        }
    }

private fun Rect.toSize() = IntSize(width(), height())
