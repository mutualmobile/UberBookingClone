package com.example.uberbookingexperience.ui.screens.rideConfirmed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material.icons.outlined.HealthAndSafety
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShareLocation
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.ui.common.UberBottomSheetListItem
import com.example.uberbookingexperience.ui.common.UberDivider
import com.example.uberbookingexperience.ui.theme.UberBookingExperienceTheme
import com.example.uberbookingexperience.ui.theme.spacing
import com.example.uberbookingexperience.ui.util.DevicePreviews
import com.example.uberbookingexperience.ui.util.UberIconSize
import com.example.uberbookingexperience.ui.util.clickableWithRipple
import com.example.uberbookingexperience.ui.util.rememberIsMobileDevice

@Composable
fun RideConfirmedScreen() {
    Column(
        modifier = Modifier.then(
            if (rememberIsMobileDevice()) {
                Modifier.fillMaxWidth()
            } else {
                Modifier.width(300.dp)
            }
        ).wrapContentSize().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DescriptionTopBar(
                    elevation = 16.dp,
                    title = "Meet at the pickup point for Home",
                    timeLeftInMinutes = 3
                )
                RidePin(
                    title = "PIN for this ride",
                    pin = "4890"
                )
                DriverDescription(
                    driverImageUrl = "",
                    driverRating = 5.0,
                    driverName = "Dhaval",
                    driverTotalTrips = "7,261",
                    carImageUrl = "",
                    carNumber = "GJ01FT3805",
                    carName = "Maruti Suzuki Wagon R"
                )
                UberDivider()
                Image(
                    modifier = Modifier.padding(MaterialTheme.spacing.medium).fillMaxWidth()
                        .clip(RoundedCornerShape(10)),
                    painter = painterResource(id = R.drawable.travel_responsibly),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                UberDivider()
                Image(
                    modifier = Modifier.padding(vertical = MaterialTheme.spacing.medium)
                        .width(200.dp)
                        .aspectRatio(2f)
                        .clip(RoundedCornerShape(10)),
                    painter = painterResource(id = R.drawable.reserve),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }
        Surface(
            modifier = Modifier.padding(top = MaterialTheme.spacing.medium)
        ) {
            Column {
                UberBottomSheetListItem(
                    icon = Icons.Filled.LocationOn,
                    title = "Dev Arc Commercial Complex",
                    subtitle = "14:16 dropoff",
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
                UberBottomSheetListItem(
                    icon = Icons.Outlined.ShareLocation,
                    title = "Share trip status",
                    actionTitle = "Share",
                    useFullSizeDivider = true
                )
                BottomActions()
            }
        }
    }
}

@Composable
fun BottomActions() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        RideConfirmedScreenBottomActionButton(
            modifier = Modifier.weight(1f),
            text = "Cancel",
            textColor = MaterialTheme.colorScheme.error
        )
        RideConfirmedScreenBottomActionButton(
            modifier = Modifier.weight(1f),
            text = "Safety",
            textColor = MaterialTheme.colorScheme.secondary,
            icon = Icons.Outlined.HealthAndSafety
        )
    }
}

@Composable
fun RideConfirmedScreenBottomActionButton(
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

@Composable
fun DriverDescription(
    driverImageUrl: String,
    driverRating: Double,
    driverName: String,
    driverTotalTrips: String,
    carImageUrl: String,
    carNumber: String,
    carName: String
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.CenterStart
            ) {
                Image(
                    modifier = Modifier.width(150.dp),
                    painter = painterResource(id = R.drawable.wagon_r_image),
                    contentDescription = null
                )
                Box(
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Surface(
                        modifier = Modifier.size(UberIconSize.LargeIcon),
                        shape = CircleShape
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(MaterialTheme.spacing.extraSmall).clip(CircleShape),
                            painter = painterResource(id = R.drawable.driver_image),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                    Surface(
                        shape = CircleShape,
                        shadowElevation = 8.dp
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = driverRating.toString(),
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Icon(
                                modifier = Modifier.size(UberIconSize.SmallIcon),
                                imageVector = Icons.Filled.Star,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    modifier = Modifier.padding(bottom = MaterialTheme.spacing.small),
                    text = carNumber,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Medium
                )
                Text(text = carName, style = MaterialTheme.typography.bodyMedium)
            }
        }
        CompositionLocalProvider(
            LocalTextStyle provides MaterialTheme.typography.bodyMedium
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = driverName,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
                    text = "\u00B7",
                    fontSize = 32.sp
                )
                Text(
                    text = "$driverTotalTrips trips"
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.small)
                .padding(bottom = MaterialTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(0.1f))
                    .clickableWithRipple {}
                    .padding(
                        horizontal = MaterialTheme.spacing.medium,
                        vertical = MaterialTheme.spacing.medium.times(0.75f)
                    ),
                text = "Message $driverName",
                style = MaterialTheme.typography.bodyMedium
            )
            DriverDescriptionIconButton(icon = Icons.Filled.Call)
            DriverDescriptionIconButton(icon = Icons.Filled.LightMode)
        }
    }
}

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

@Composable
fun RidePin(title: String, pin: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal
        )
        Row {
            pin.forEach { pinCharacter ->
                Text(
                    modifier = Modifier
                        .padding(end = MaterialTheme.spacing.extraSmall)
                        .background(MaterialTheme.colorScheme.secondary)
                        .padding(
                            horizontal = MaterialTheme.spacing.small,
                            vertical = MaterialTheme.spacing.extraSmall
                        ),
                    text = pinCharacter.toString(),
                    color = MaterialTheme.colorScheme.surface,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun DescriptionTopBar(elevation: Dp, title: String, timeLeftInMinutes: Int) {
    Surface(
        shadowElevation = elevation
    ) {
        Column(
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(MaterialTheme.spacing.medium),
                    text = title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Medium
                )
                Column(
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.medium)
                        .background(MaterialTheme.colorScheme.primary).width(56.dp).aspectRatio(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CompositionLocalProvider(
                        LocalTextStyle provides MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text(
                            text = timeLeftInMinutes.toString(),
                            fontSize = 24.sp
                        )
                        Text(text = "min")
                    }
                }
            }
        }
    }
}

@DevicePreviews
@Composable
private fun RideConfirmedScreenPreview() {
    UberBookingExperienceTheme {
        Column(
            modifier = Modifier.then(
                if (rememberIsMobileDevice()) {
                    Modifier.fillMaxWidth()
                } else {
                    Modifier.width(300.dp)
                }
            )
        ) {
            RideConfirmedScreen()
        }
    }
}
