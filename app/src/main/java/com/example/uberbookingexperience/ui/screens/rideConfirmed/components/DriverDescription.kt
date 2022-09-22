package com.example.uberbookingexperience.ui.screens.rideConfirmed.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uberbookingexperience.ui.theme.spacing
import com.example.uberbookingexperience.ui.util.UberIconSize
import com.example.uberbookingexperience.ui.util.clickableWithRipple

@Composable
fun DriverDescription(
    @DrawableRes driverImageUrl: Int,
    driverRating: Double,
    driverName: String,
    driverTotalTrips: String,
    @DrawableRes carImageUrl: Int,
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
                    painter = painterResource(id = carImageUrl),
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
                            painter = painterResource(id = driverImageUrl),
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
