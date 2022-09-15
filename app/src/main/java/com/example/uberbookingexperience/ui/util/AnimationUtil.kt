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
import com.google.accompanist.systemuicontroller.SystemUiController
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
 * */
fun Activity.changeSystemBarsColor(
    color: Color = Color.Transparent,
) {
    if (window.statusBarColor != color.toArgb()) {
        window.statusBarColor = color.toArgb()
    }

    if (window.navigationBarColor != color.toArgb()) {
        window.navigationBarColor = color.toArgb()
    }
}

/**
 * A util function for easily changing systemBars colors.
 * */
fun SystemUiController.changeSystemBarsColor(
    color: Color = Color.Transparent,
    darkIcons: Boolean = true,
) {
    setSystemBarsColor(
        color = color,
        darkIcons = darkIcons
    )
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