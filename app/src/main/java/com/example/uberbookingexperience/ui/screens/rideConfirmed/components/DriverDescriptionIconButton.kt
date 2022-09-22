package com.example.uberbookingexperience.ui.screens.rideConfirmed.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.uberbookingexperience.ui.theme.spacing
import com.example.uberbookingexperience.ui.util.clickableWithRipple

@Composable
fun DriverDescriptionIconButton(
    icon: ImageVector
) {
    Icon(
        modifier = Modifier
            .padding(end = MaterialTheme.spacing.small)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary.copy(0.1f))
            .clickableWithRipple {}
            .padding(MaterialTheme.spacing.small),
        imageVector = icon,
        contentDescription = null
    )
}
