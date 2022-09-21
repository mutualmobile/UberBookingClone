package com.example.uberbookingexperience.ui.screens.finalisingDriver.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import com.example.uberbookingexperience.ui.common.UberDivider
import com.example.uberbookingexperience.ui.util.UberIconSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinalisingDriverScreenListItem(
    icon: ImageVector,
    iconTint: Color? = null,
    title: String? = null,
    subtitle: String? = null,
    actionTitle: String? = null,
    actionColor: Color = MaterialTheme.colorScheme.secondary
) {
    Column {
        ListItem(
            leadingContent = {
                Icon(
                    modifier = Modifier.size(UberIconSize.NormalIcon),
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint ?: LocalContentColor.current
                )
            },
            headlineText = {
                title?.let { nnTitle ->
                    Text(
                        text = nnTitle,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
            },
            supportingText = subtitle?.let { nnSubtitle ->
                {
                    Text(
                        text = nnSubtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.75f
                        )
                    )
                }
            },
            trailingContent = actionTitle?.let { nnActionTitle ->
                {
                    TextButton(
                        onClick = {},
                        shape = RoundedCornerShape(10)
                    ) {
                        Text(
                            text = nnActionTitle,
                            color = actionColor,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        )
        UberDivider(
            thickness = DividerDefaults.Thickness
        )
    }
}
