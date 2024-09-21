package com.adaptive.components.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo

@OptIn(ExperimentalComposeUiApi::class)
actual val LocalScreenSize
    @Composable
    @ReadOnlyComposable
    get() = LocalWindowInfo.current.containerSize