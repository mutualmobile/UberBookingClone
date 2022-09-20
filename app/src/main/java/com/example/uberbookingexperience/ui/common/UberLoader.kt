package com.example.uberbookingexperience.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.ui.theme.UberBookingExperienceTheme

private val UberLoaderEasing = CubicBezierEasing(0.4f, 0.0f, 0.0f, 0.4f)

/**
 * A composable that denotes the loading state for some incoming data with a basic animation.
 * @param modifier [Modifier] to be applied on this loader
 * @param loaderMinSize during the animation, this is the minimum size the loader can shrink to.
 * Defaults to 8.dp
 * @param loaderMaxSize during the animation, this is the maximum size the loader can expand to.
 * Defaults to 64.dp
 * @param loaderColor color of the loader
 * @param loaderThickness thickness of the loader
 * @param text string to be displayed while the loader is visible
 * @param textColor color to be applied on [text]
 * @param spaceBetweenTextAndLoader defines how far the loader and the [text] are from each other
 * @param animationDurationMillis how long should the animation take (in milliseconds)
 * @param animationEasing the easing curve that will be used to interpolate between start and end
 * destination(s) of the loader animation
 * @sample UberLoaderPreview
 * */
@Composable
fun UberLoader(
    modifier: Modifier = Modifier,
    loaderMinSize: Dp = 8.dp,
    loaderMaxSize: Dp = 64.dp,
    loaderColor: Color = MaterialTheme.colorScheme.primary,
    loaderThickness: Dp = 2.dp,
    text: String,
    textColor: Color = loaderColor,
    spaceBetweenTextAndLoader: Dp = 8.dp,
    animationDurationMillis: Int = 1000,
    animationEasing: Easing = UberLoaderEasing
) {
    val density = LocalDensity.current

    val screenWidthInDp = LocalConfiguration.current.screenWidthDp.dp
    val screenWidthInPx = remember(screenWidthInDp) { with(density) { screenWidthInDp.toPx() } }

    val loaderStartPositionPx = remember { 0f }
    val loaderEndPositionPx =
        remember(screenWidthInPx) { with(density) { screenWidthInPx - loaderMinSize.toPx() } }

    val infiniteTransition = rememberInfiniteTransition()

    val loaderCurrentPosition by infiniteTransition.animateFloat(
        initialValue = loaderStartPositionPx,
        targetValue = loaderEndPositionPx,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDurationMillis,
                easing = animationEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    val loaderCurrentSizeInDp by infiniteTransition.animateFloat(
        initialValue = loaderMinSize.value,
        targetValue = loaderMaxSize.value,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDurationMillis.div(2),
                easing = animationEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    val loaderCurrentSizeInPx by remember { derivedStateOf { with(density) { loaderCurrentSizeInDp.dp.toPx() } } }

    Column(
        modifier = modifier
            .defaultMinSize(minWidth = 200.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Canvas(
            modifier = Modifier,
            onDraw = {
                drawLine(
                    color = loaderColor,
                    start = Offset(x = loaderCurrentPosition, y = 0f),
                    end = Offset(x = loaderCurrentPosition + loaderCurrentSizeInPx, y = 0f),
                    strokeWidth = loaderThickness.toPx()
                )
            }
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth()
                .padding(top = spaceBetweenTextAndLoader),
            text = text,
            color = textColor
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UberLoaderPreview() {
    UberBookingExperienceTheme {
        var isLoaderDisplayed by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { isLoaderDisplayed = true },
                shape = ShapeDefaults.Small
            ) {
                Text(text = "Show Loader")
            }
            AnimatedVisibility(
                visible = isLoaderDisplayed,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                UberLoader(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),
                    text = "Scheduling your ride",
                    loaderColor = MaterialTheme.colorScheme.surface
                )
            }
        }
    }
}
