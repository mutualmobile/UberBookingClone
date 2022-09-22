package com.example.uberbookingexperience.ui.screens.rideConfirmed.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import com.example.uberbookingexperience.ui.theme.spacing

@Composable
fun BottomSheetActionButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color? = null,
    icon: ImageVector? = null,
    iconTint: Color? = null,
    onClick: () -> Unit = {}
) {
    TextButton(
        modifier = modifier.padding(horizontal = MaterialTheme.spacing.small),
        onClick = onClick,
        shape = RoundedCornerShape(10)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let { nnIcon ->
                Icon(
                    imageVector = nnIcon,
                    contentDescription = null,
                    tint = iconTint ?: textColor ?: LocalContentColor.current
                )
            }
            Spacer(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.extraSmall))
            Text(
                text = text,
                color = textColor ?: LocalContentColor.current,
                textAlign = TextAlign.Center
            )
        }
    }
}
