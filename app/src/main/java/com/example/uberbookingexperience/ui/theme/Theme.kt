package com.example.uberbookingexperience.ui.theme // ktlint-disable filename

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = darkColorScheme(
    primary = Color.Black,
    onPrimary = Color.White,
    surface = Color.White,
    onSurface = Color.Black,
    background = Color(0xFFEEEDEE),
    onBackground = Color.Black,
    secondary = Color(0xFF0070FA),
    tertiary = Color(0xFF67A6C5)
)

@Composable
fun UberBookingExperienceTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
