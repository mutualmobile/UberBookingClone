package com.example.uberbookingexperience.ui.screens.finalisingDriver

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.ui.common.UberBottomSheetListItem
import com.example.uberbookingexperience.ui.common.UberDivider
import com.example.uberbookingexperience.ui.screens.finalisingDriver.components.HighlightItem
import com.example.uberbookingexperience.ui.screens.finalisingDriver.components.ProgressBarAnimation
import com.example.uberbookingexperience.ui.theme.UberBookingExperienceTheme
import com.example.uberbookingexperience.ui.theme.spacing
import com.example.uberbookingexperience.ui.util.DevicePreviews
import com.example.uberbookingexperience.ui.util.rememberIsMobileDevice

@Composable
fun FinalisingDriverScreen(
    onAnimationFinished: () -> Unit = {},
    onCancelButtonClick: () -> Unit = {}
) {
    Surface(
        shadowElevation = 8.dp,
        modifier = Modifier.wrapContentSize()
    ) {
        Column(
            modifier = Modifier.then(
                if (rememberIsMobileDevice()) {
                    Modifier.fillMaxWidth()
                } else {
                    Modifier.width(300.dp)
                }
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UberDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth()
                    .width(56.dp)
                    .padding(top = MaterialTheme.spacing.small),
                thickness = 4.dp,
                shape = CircleShape,
                alpha = 0.1f
            )
            Text(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .align(Alignment.Start),
                text = "Ride requested, finalising driver details",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Medium
            )
            UberDivider(alpha = 0.1f)
            ProgressBarAnimation(
                modifier = Modifier.padding(MaterialTheme.spacing.small)
            ) {
                onAnimationFinished()
            }
            HighlightItem(
                modifier = Modifier.padding(MaterialTheme.spacing.large),
                icon = R.drawable.ic_map_location,
                title = "Dropoff by 17:26"
            )
            UberDivider()
            UberBottomSheetListItem(
                icon = Icons.Filled.LocationOn,
                title = "Dev Arc Commercial Complex",
                subtitle = null,
                actionTitle = "Add or Change"
            )
            UberBottomSheetListItem(
                icon = Icons.Outlined.Person,
                title = "$7.58",
                subtitle = "Personal \u00B7 Payment",
                actionTitle = "Switch"
            )
            UberBottomSheetListItem(
                icon = Icons.Outlined.Error,
                iconTint = MaterialTheme.colorScheme.error,
                title = null,
                subtitle = "We can't reach our network, so the trip total might be outdated",
                actionTitle = null
            )
            TextButton(
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
                onClick = onCancelButtonClick,
                shape = RoundedCornerShape(10)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Cancel",
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@DevicePreviews
@Composable
private fun FinalisingDriverScreenPreview() {
    UberBookingExperienceTheme {
        FinalisingDriverScreen()
    }
}
