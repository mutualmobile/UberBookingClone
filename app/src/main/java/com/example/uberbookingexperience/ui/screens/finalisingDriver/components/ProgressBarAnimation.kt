package com.example.uberbookingexperience.ui.screens.finalisingDriver.components

import androidx.annotation.IntRange
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.ui.theme.UberBookingExperienceTheme
import com.example.uberbookingexperience.ui.theme.spacing
import com.example.uberbookingexperience.ui.util.systemTween
import kotlinx.coroutines.delay

private const val ProgressStateChangeDelay: Long = 2000
private const val ProgressAnimationDuration: Int = 1000
private const val StateSwitchDuration: Int = 0

private enum class ProgressAnimationState {
    Loading, Loaded, Uninitialised
}

@Composable
fun ProgressBarAnimation(
    modifier: Modifier = Modifier,
    @IntRange(from = 1, to = 6)
    parts: Int = 4,
    inactiveColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.15f),
    progressColor: Color = MaterialTheme.colorScheme.secondary,
    animationDurationMillis: Int = ProgressAnimationDuration,
    spaceBetweenParts: Dp = MaterialTheme.spacing.extraSmall,
    trackHeight: Dp = 4.dp,
    onAnimationFinished: () -> Unit = {}
) {
    var selectedPart by rememberSaveable { mutableStateOf(1) }

    LaunchedEffect(Unit) {
        repeat(parts) { partIndex ->
            selectedPart = partIndex + 1
            delay(ProgressStateChangeDelay)
            if (partIndex == parts) {
                onAnimationFinished()
            }
        }
    }

    Row(modifier = modifier) {
        repeat(parts) { partIndex ->
            ProgressAnimation(
                modifier = Modifier
                    .weight(1f)
                    .padding(spaceBetweenParts),
                animationState = when {
                    selectedPart == partIndex + 1 -> ProgressAnimationState.Loading
                    selectedPart > partIndex + 1 -> ProgressAnimationState.Loaded
                    else -> ProgressAnimationState.Uninitialised
                },
                trackColor = inactiveColor,
                progressColor = progressColor,
                animationDurationMillis = animationDurationMillis,
                trackHeight = trackHeight
            )
        }
    }
}

@Composable
private fun ProgressAnimation(
    modifier: Modifier = Modifier,
    animationState: ProgressAnimationState,
    trackColor: Color,
    progressColor: Color,
    animationDurationMillis: Int,
    trackHeight: Dp
) {
    val infiniteTransition = rememberInfiniteTransition()

    val animatedColor by infiniteTransition.animateColor(
        initialValue = progressColor,
        targetValue = Color.Transparent,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = animationDurationMillis
                progressColor at 0
                progressColor at animationDurationMillis.times(0.75f).toInt()
                Color.Transparent at animationDurationMillis.times(0.9f).toInt()
            }
        )
    )

    val animatedProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = animationDurationMillis
                0f at 0
                1f at animationDurationMillis.times(0.75f).toInt()
                1f at animationDurationMillis
            }
        )
    )

    val conditionalColor by animateColorAsState(
        targetValue = when (animationState) {
            ProgressAnimationState.Uninitialised -> Color.Transparent
            ProgressAnimationState.Loading -> animatedColor
            ProgressAnimationState.Loaded -> progressColor
        },
        animationSpec = systemTween(durationMillis = StateSwitchDuration)
    )
    val conditionalProgress by animateFloatAsState(
        targetValue = when (animationState) {
            ProgressAnimationState.Uninitialised -> 0f
            ProgressAnimationState.Loading -> animatedProgress
            ProgressAnimationState.Loaded -> 1f
        },
        animationSpec = systemTween(durationMillis = StateSwitchDuration)
    )

    LinearProgressIndicator(
        modifier = modifier.height(trackHeight),
        progress = conditionalProgress,
        color = conditionalColor,
        trackColor = trackColor
    )
}

@Preview(showSystemUi = true)
@Composable
private fun ProgressBarAnimationPreview() {
    UberBookingExperienceTheme {
        ProgressBarAnimation()
    }
}
