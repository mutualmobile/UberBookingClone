package com.example.uberbookingexperience.ui.util // ktlint-disable filename

import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.LocalTime
import java.util.Locale

object UberIconSize {
    val Navigation = 36.dp
    val ListItem = 36.dp
    val TrailingIconSize = 16.dp
    val LargeIcon = 64.dp
    val MediumImage = 110.dp
    val NormalIcon = 24.dp
    val MediumIcon = 32.dp
    val LargeButton = 42.dp
    val SmallIcon = 12.dp
}

fun LocalDate.uberFormattedDate() =
    "${dayOfWeek.name.substring(0, 3)}, $dayOfMonth ${month.name.substring(0, 3)}".toCamelCase()

fun LocalTime.uberFormattedTime() = "$hour:${if (minute < 10) "0$minute" else "$minute"}"
private fun String.toCamelCase(delimiter: String = " "): String {
    val wordList = this.split(delimiter).map { word ->
        word
            .lowercase()
            .replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }
    }
    return wordList.joinToString(separator = delimiter)
}
