package com.example.uberbookingexperience.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = darkColorScheme(
    primary = Color.Black, surface = Color.White, background = Color.White,
)

@Composable
fun UberBookingExperienceTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorPalette, typography = Typography, shapes = Shapes, content = content
    )
}