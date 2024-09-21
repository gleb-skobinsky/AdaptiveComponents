package com.adaptive.components.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.graphics.Color

@Composable
fun AdaptiveComponentsTheme(
    content: @Composable () -> Unit
) = MaterialTheme(
    colorScheme = AdaptiveComponentsColorScheme(),
    content = content
)

@Composable
@NonRestartableComposable
private fun AdaptiveComponentsColorScheme() = MaterialTheme.colorScheme.copy(
    primary = Color(0, 177, 64),
    secondary = Color(250, 250, 250),
    primaryContainer = Color.Black,
    secondaryContainer = Color(111, 111, 111),
    onSecondary = Color(136, 136, 136),
    tertiary = Color(189, 189, 189)
)