package com.example.uberbookingexperience.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import com.example.uberbookingexperience.ui.theme.spacing

/**
 * An Uber-styled button that helps its users initiate actions.
 * @param modifier the [Modifier] to be applied to this button
 * @param text the text to be displayed
 * @param onClick called when this button is clicked
 * */
@Composable
fun UberButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .padding(horizontal = MaterialTheme.spacing.small)
            .padding(top = MaterialTheme.spacing.extraLarge)
            .fillMaxWidth(),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onSurface,
            contentColor = MaterialTheme.colorScheme.surface
        ),
        shape = RectangleShape,
        contentPadding = PaddingValues(vertical = MaterialTheme.spacing.medium)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
