package com.example.uberbookingexperience.ui.screens.whereTo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.ui.theme.Typography
import com.example.uberbookingexperience.ui.theme.bodyMedium_gray
import com.example.uberbookingexperience.ui.util.clickableWithRipple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListTile(
    icon: ImageVector?,
    contentDesc: String?,
    title: String?,
    subtitle: String?,
    modifier: Modifier = Modifier,
    isClicked: () -> Unit
) {
    ListItem(
        modifier = modifier.clickableWithRipple { isClicked() },
        leadingContent = {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                icon?.let { nnIcon ->
                    Icon(
                        imageVector = nnIcon,
                        contentDescription = contentDesc,
                        tint = Color.White,
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
            }
        },
        headlineText = {
            title?.let { nnTitle ->
                Text(
                    text = nnTitle,
                    style = Typography.titleMedium
                )
            }
        },
        supportingText = {
            subtitle?.let { nnSubtitle ->
                Text(
                    text = nnSubtitle,
                    style = Typography.bodyMedium_gray
                )
            }
        },
        shadowElevation = ListItemDefaults.Elevation
    )
    Divider(color = Color.LightGray, thickness = 1.dp)
}
