package com.adaptive.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar({ Text("Test title") })
            }
        ) {
            AdaptiveColumn(
                horizontalPadding = 32.dp
            ) {
                Spacer(Modifier.height(it.calculateTopPadding()))
                Box(
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                )
                24.dp.VerticalSpacer()
                var text by rememberSaveable { mutableStateOf("") }
                OutlinedTextField(text, { text = it })
                24.dp.VerticalSpacer()
                Box(
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(MaterialTheme.colorScheme.onBackground)
                )
                24.dp.VerticalSpacer()
                var text2 by rememberSaveable { mutableStateOf("") }
                OutlinedTextField(
                    value = text2,
                    onValueChange = { text2 = it }
                )
                24.dp.VerticalSpacer()
                Box(
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(Color.Magenta)
                )
                24.dp.VerticalSpacer()
                Box(
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(Color.Green)
                )
            }
        }
    }
}

@Composable
fun Dp.VerticalSpacer() = Spacer(Modifier.height(this))