package com.adaptive.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier

private val interactionSource = MutableInteractionSource()

fun Modifier.noRipple(onClick: () -> Unit) = this.clickable(
    interactionSource = interactionSource,
    indication = null,
    onClick = onClick
)