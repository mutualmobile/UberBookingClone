package com.example.uberbookingexperience.ui.screens.schedulePickup.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.uberbookingexperience.ui.theme.spacing
import com.example.uberbookingexperience.ui.util.clickableWithRipple

@Composable
fun SchedulePickupText(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Text(
        modifier = modifier.clickableWithRipple(onClick = onClick)
            .padding(MaterialTheme.spacing.medium).fillMaxWidth(),
        text = text,
        textAlign = TextAlign.Center
    )
}
