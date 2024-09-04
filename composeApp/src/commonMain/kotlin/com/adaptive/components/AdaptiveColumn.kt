package com.adaptive.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.onFocusedBoundsChanged
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AdaptiveColumn(
    scrollable: Boolean = true,
    modifier: Modifier = Modifier,
    horizontalPadding: Dp = 16.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    val screenHeight = LocalScreenSize.height
    var imeHeight by remember { mutableIntStateOf(0) }
    var bottom = remember<Float?> { null }
    var hasFocus by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    imeHeight = imeHeight()
    LaunchedEffect(hasFocus) {
        val capturedBottom = bottom ?: return@LaunchedEffect
        if (hasFocus) {
            var lastHeight = 0
            snapshotFlow { imeHeight }
                .collectLatest { height ->
                    if (height > capturedBottom) {
                        val diff = height - lastHeight
                        lastHeight = height
                        scrollState.scrollBy(diff.toFloat())
                    } else {
                        lastHeight = height
                    }
                }
        }
    }
    val scrollModifier = if (scrollable) Modifier.verticalScroll(scrollState) else Modifier
    Column(
        modifier = modifier
            .onFocusChanged {
                hasFocus = it.hasFocus
            }
            .onFocusedBoundsChanged { coordinates ->
                val bounds = coordinates?.boundsInWindow()
                bottom = bounds?.let { screenHeight - it.bottom }
            }
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = horizontalPadding)
            .then(scrollModifier),
        content = content
    )
}

@Composable
fun imeHeight() = WindowInsets.ime.getBottom(LocalDensity.current)


interface AdaptiveColumnScope : ColumnScope {
    val keyboardOverlay: Dp
    val scroll: ScrollState
}

private data class AdaptiveColumnScopeImpl(
    val column: ColumnScope,
    override val keyboardOverlay: Dp,
    override val scroll: ScrollState
) : AdaptiveColumnScope, ColumnScope by column