package com.adaptive.components

import adaptivecomponents.composeapp.generated.resources.Res
import adaptivecomponents.composeapp.generated.resources.confirm_password_placeholder
import adaptivecomponents.composeapp.generated.resources.confirm_password_title
import adaptivecomponents.composeapp.generated.resources.create_your_account
import adaptivecomponents.composeapp.generated.resources.email_placeholder
import adaptivecomponents.composeapp.generated.resources.email_title
import adaptivecomponents.composeapp.generated.resources.first_name_placeholder
import adaptivecomponents.composeapp.generated.resources.first_name_title
import adaptivecomponents.composeapp.generated.resources.last_name_placeholder
import adaptivecomponents.composeapp.generated.resources.last_name_title
import adaptivecomponents.composeapp.generated.resources.log_in
import adaptivecomponents.composeapp.generated.resources.password_placeholder
import adaptivecomponents.composeapp.generated.resources.password_title
import adaptivecomponents.composeapp.generated.resources.phone_placeholder
import adaptivecomponents.composeapp.generated.resources.phone_title
import adaptivecomponents.composeapp.generated.resources.sign_up
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adaptive.components.theme.AdaptiveComponentsTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    AdaptiveComponentsTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        IconButton({}) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    title = {}
                )
            }
        ) {
            ImeAdaptiveColumn(horizontalPadding = 32.dp) {
                Spacer(Modifier.height(it.calculateTopPadding()))
                32.dp.VerticalSpacer()
                AccountIcon()
                32.dp.VerticalSpacer()
                Text(
                    text = stringResource(Res.string.create_your_account),
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 32.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                32.dp.VerticalSpacer()
                SignupFormTextField(
                    label = stringResource(Res.string.first_name_title),
                    placeholder = stringResource(Res.string.first_name_placeholder)
                )
                24.dp.VerticalSpacer()
                SignupFormTextField(
                    label = stringResource(Res.string.last_name_title),
                    placeholder = stringResource(Res.string.last_name_placeholder)
                )
                24.dp.VerticalSpacer()
                SignupFormTextField(
                    label = stringResource(Res.string.email_title),
                    placeholder = stringResource(Res.string.email_placeholder)
                )
                24.dp.VerticalSpacer()
                SignupFormTextField(
                    label = stringResource(Res.string.phone_title),
                    placeholder = stringResource(Res.string.phone_placeholder)
                )
                24.dp.VerticalSpacer()
                SignupFormTextField(
                    label = stringResource(Res.string.password_title),
                    placeholder = stringResource(Res.string.password_placeholder)
                )
                24.dp.VerticalSpacer()
                SignupFormTextField(
                    label = stringResource(Res.string.confirm_password_title),
                    placeholder = stringResource(Res.string.confirm_password_placeholder)
                )
                24.dp.VerticalSpacer()
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {}
                ) {
                    Text(stringResource(Res.string.sign_up))
                }
                24.dp.VerticalSpacer()
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {}
                ) {
                    Text(stringResource(Res.string.log_in))
                }
                Box(Modifier.fillMaxWidth().aspectRatio(1f))
                Spacer(Modifier.height(it.calculateBottomPadding()))
            }
        }
    }
}

@Composable
private fun ColumnScope.AccountIcon() {
    Box(
        Modifier.Companion
            .align(Alignment.CenterHorizontally)
            .shadow(12.dp, CircleShape)
            .clip(CircleShape)
            .background(Color.White)
            .fillMaxWidth(0.6f)
            .aspectRatio(1f)
    ) {
        Icon(
            imageVector = Icons.Outlined.AccountCircle,
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun SignupFormTextField(
    label: String,
    placeholder: String,
    isPassword: Boolean = false
) {
    var textState by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = textState,
        onValueChange = { textState = it },
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        visualTransformation = if (isPassword) {
            PasswordVisualTransformation('*')
        } else {
            VisualTransformation.None
        }
    )
}

@Composable
fun Dp.VerticalSpacer() = Spacer(Modifier.height(this))