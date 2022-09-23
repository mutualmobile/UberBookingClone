package com.example.uberbookingexperience.ui.util // ktlint-disable filename

import android.annotation.SuppressLint
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration

@SuppressLint("ComposableModifierFactory")
@Composable
fun Modifier.clickableWithRipple(
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    indication: Indication = rememberRipple(),
    onClick: () -> Unit
) = clickable(
    interactionSource = interactionSource,
    indication = indication,
    onClick = onClick
)

@Composable
fun rememberIsMobileDevice(): Boolean {
    val config = LocalConfiguration.current
    return remember { derivedStateOf { config.screenWidthDp < 600 } }.value
}
