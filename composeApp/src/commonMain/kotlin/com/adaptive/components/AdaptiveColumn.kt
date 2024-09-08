package com.adaptive.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest

data class FocusedArea(
    val id: String = "",
    val spaceFromBottom: Float? = null
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AdaptiveColumn(
    scrollable: Boolean = true,
    modifier: Modifier = Modifier,
    horizontalPadding: Dp = 16.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    val screenHeight = LocalScreenSize.height
    val scrollState = rememberScrollState()
    val imeHeight by rememberUpdatedState(imeHeight())

    var focusedArea by remember { mutableStateOf(FocusedArea()) }
    LaunchedEffect(focusedArea) {
        if (focusedArea.id.isNotEmpty()
            && focusedArea.spaceFromBottom != null
        ) {
            val capturedBottom = focusedArea.spaceFromBottom ?: return@LaunchedEffect
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
            .onFocusedBoundsChanged { coordinates ->
                val bounds = coordinates?.boundsInWindow()
                bounds?.let {
                    focusedArea = focusedArea.copy(
                        spaceFromBottom = screenHeight - it.bottom
                    )
                }
            }
            .pointerInput(Unit) {
                awaitEachGesture {
                    val event = awaitPointerEvent(PointerEventPass.Main)
                    if (event.type == PointerEventType.Press) {
                        focusedArea = focusedArea.copy(
                            id = uuid()
                        )
                    }
                }
            }
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = horizontalPadding)
            .then(scrollModifier),
        content = content
    )
}

@Composable
fun imeHeight() = WindowInsets.ime.getBottom(LocalDensity.current)
