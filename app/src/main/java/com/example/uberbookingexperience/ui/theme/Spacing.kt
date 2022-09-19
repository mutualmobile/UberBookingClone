package com.example.uberbookingexperience.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
class Spacing internal constructor(
    val none: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 24.dp,
    val extraLarge: Dp = 32.dp
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is Spacing) return false

        if (none != other.none) return false
        if (extraSmall != other.extraSmall) return false
        if (small != other.small) return false
        if (medium != other.medium) return false
        if (large != other.large) return false
        if (extraLarge != other.extraLarge) return false

        return true
    }

    override fun hashCode(): Int {
        var result = none.hashCode()
        result = 31 * result + extraSmall.hashCode()
        result = 31 * result + small.hashCode()
        result = 31 * result + medium.hashCode()
        result = 31 * result + large.hashCode()
        result = 31 * result + extraLarge.hashCode()
        return result
    }

    override fun toString(): String {
        return "Spacing(none=$none, extraSmall=$extraSmall, small=$small, medium=$medium," +
            " large=$large, extraLarge=$extraLarge)"
    }
}

/**
 * CompositionLocal used to specify the default [Spacing] for composables.
 * */
val LocalSpacing = staticCompositionLocalOf { Spacing() }

/**
 * Retrieves the current [Spacing] at the call site's position in the hierarchy.
 */
val MaterialTheme.spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current
