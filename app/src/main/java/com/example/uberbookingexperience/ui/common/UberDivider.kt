package com.example.uberbookingexperience.ui.common

import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp

/**
 * Uber Design divider.
 * @param modifier the Modifier to be applied to this divider line
 * */
@Composable
fun UberDivider(modifier: Modifier = Modifier) {
    Divider(modifier = modifier.alpha(0.15f), thickness = 1.5.dp)
}
