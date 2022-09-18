package com.example.uberbookingexperience.ui.util // ktlint-disable filename

import android.annotation.SuppressLint
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

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

/**
 * Constrain the width of the content to be between mindp and maxdp as permitted by the incoming
 * measurement [Constraints].
 * */
fun Modifier.limitWidth(min: Dp = 200.dp, max: Dp = 400.dp) = widthIn(min = min, max = max)

@Composable
fun rememberIsMobileDevice(): Boolean {
    val config = LocalConfiguration.current
    return remember { derivedStateOf { config.screenWidthDp < 600 } }.value
}
