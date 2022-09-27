package com.example.uberbookingexperience.ui.util // ktlint-disable filename

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
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
    val SizeButton = 42.dp
    val SmallImage = 80.dp

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

/**
 * Get BitmapDescriptor for vector drawable using resource id
 */
fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
    return ContextCompat.getDrawable(context, vectorResId)?.run {
        setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
        draw(Canvas(bitmap))
        BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}
