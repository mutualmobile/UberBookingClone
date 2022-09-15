package com.example.uberbookingexperience.ui.util

import android.app.Activity
import android.provider.Settings
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import kotlin.math.roundToInt

/**
 * A util method that takes in an animation duration (in milliseconds) and multiplies it to the
 * 'Animator duration scale' so that the app animations react to the system animation scale.
 * */
fun Activity.getSystemAnimationDuration(
    animDuration: Int = AnimationConstants.DefaultDurationMillis
): Int {
    return animDuration * getAnimationDurationScale().roundToInt()
}

/**
 * A util method that takes in an animation duration (in milliseconds) and multiplies it to the
 * 'Animator duration scale' so that the app animations react to the system animation scale.
 * */
@Composable
fun getSystemAnimationDuration(
    animDuration: Int = AnimationConstants.DefaultDurationMillis
): Int {
    val animationDurationScale = getAnimationDurationScale().roundToInt()
    return remember { animDuration * animationDurationScale }
}

/**
 * Creates a [tween] AnimationSpec that follows system 'Animator duration scale' (i.e. if the
 * 'Animator duration scale' value is modified in the device's developer options, then this value
 * would also be tweaked accordingly).
 * */
@Composable
fun <T> systemTween(
    durationMillis: Int = AnimationConstants.DefaultDurationMillis
): TweenSpec<T> = tween(durationMillis = durationMillis.times(getAnimationDurationScale()).toInt())

/**
 * A util function for easily changing systemBars colors.
 * @param statusBarColor refers to the system bar on top of the display
 * @param navigationBarColor refers to the system bar on the bottom of the display
 * (or on side of the display when in landscape mode)
 * */
fun Activity.changeSystemBarsColor(
    statusBarColor: Color = Color.Transparent,
    navigationBarColor: Color = Color.Transparent,
) {
    if (window.statusBarColor != statusBarColor.toArgb()) {
        window.statusBarColor = statusBarColor.toArgb()
    }
    if (window.navigationBarColor != navigationBarColor.toArgb()) {
        window.navigationBarColor = navigationBarColor.toArgb()
    }
}

@Composable
fun rememberActivity(): Activity {
    return LocalContext.current as Activity
}

@Composable
private fun getAnimationDurationScale(): Float {
    val activity = LocalContext.current as Activity
    return remember { activity.getAnimationDurationScale() }
}

private fun Activity.getAnimationDurationScale(): Float {
    return Settings.Global.getFloat(
        contentResolver, Settings.Global.ANIMATOR_DURATION_SCALE, 1.0f
    )
}