package com.adaptive.components.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AdaptiveComponentsTheme(
    content: @Composable () -> Unit
) = MaterialTheme(
    colorScheme = AdaptiveComponentsColorScheme(),
    content = content
)

@Composable
private fun AdaptiveComponentsColorScheme(
    isDark: Boolean = isSystemInDarkTheme()
) = if (isDark) darkColorScheme(
    primary = Color(0, 177, 64),
    secondary = Color(250, 250, 250),
    primaryContainer = Color.Black,
    secondaryContainer = Color(111, 111, 111),
    onSecondary = Color(136, 136, 136),
    tertiary = Color(189, 189, 189)
) else lightColorScheme(
    primary = Color(0, 177, 64),
    secondary = Color(250, 250, 250),
    primaryContainer = Color.Black,
    secondaryContainer = Color(111, 111, 111),
    onSecondary = Color(136, 136, 136),
    tertiary = Color(189, 189, 189)
)