package com.example.uberbookingexperience.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion
import androidx.compose.ui.unit.sp
import com.example.uberbookingexperience.R

val uberMove = FontFamily(
    Font(R.font.uber_move_light, FontWeight.W300),
    Font(R.font.uber_move_regular, FontWeight.W400),
    Font(R.font.uber_move_medium, FontWeight.W600),
    Font(R.font.uber_move_bold, FontWeight.W700),
)

// Set of Material typography styles to start with
val Typography = Typography(

    bodySmall = TextStyle(
        fontFamily = uberMove,
        fontSize = 14.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 20.sp,
    ) ,
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    titleMedium = TextStyle(
        fontFamily = uberMove,
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 24.sp,
    ),

    titleSmall = TextStyle(
        fontFamily = uberMove,
        fontSize = 14.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 20.sp,
    ),

    bodyLarge = TextStyle(
        fontFamily = uberMove,
        fontSize = 18.sp,
        fontWeight = FontWeight.W400,
        textAlign = TextAlign.Center,
    )

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val Typography.titleSmall_gray: TextStyle
    get() = titleSmall.copy(
        color = TextGrey,
    )

val Typography.bodyLarge_gray: TextStyle
    get() = bodyLarge.copy(
        color = TextGrey,
    )
