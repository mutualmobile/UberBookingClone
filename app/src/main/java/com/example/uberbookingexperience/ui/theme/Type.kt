package com.example.uberbookingexperience.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.uberbookingexperience.R

val UberMove = FontFamily(
    Font(R.font.uber_move_light, FontWeight.Light),
    Font(R.font.uber_move_regular, FontWeight.Normal),
    Font(R.font.uber_move_medium, FontWeight.Medium),
    Font(R.font.uber_move_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    displaySmall = TextStyle(
        fontFamily = UberMove,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        letterSpacing = 0.75.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = UberMove,
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = UberMove,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = UberMove,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp
    ),
    bodySmall = TextStyle(
        fontFamily = UberMove,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.75.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = UberMove,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = UberMove,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        fontFamily = UberMove
    ),
    titleSmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        fontFamily = UberMove
    )
)
