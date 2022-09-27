package com.example.uberbookingexperience.ui.screens.rideConfirmed.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.HealthAndSafety
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BottomActions() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        BottomSheetActionButton(
            modifier = Modifier.weight(1f),
            text = "Cancel",
            textColor = MaterialTheme.colorScheme.error
        )
        BottomSheetActionButton(
            modifier = Modifier.weight(1f),
            text = "Safety",
            textColor = MaterialTheme.colorScheme.secondary,
            icon = Icons.Outlined.HealthAndSafety
        )
    }
}
