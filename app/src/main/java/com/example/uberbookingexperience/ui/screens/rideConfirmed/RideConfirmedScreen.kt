package com.example.uberbookingexperience.ui.screens.rideConfirmed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShareLocation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.ui.common.UberBottomSheetListItem
import com.example.uberbookingexperience.ui.common.UberDivider
import com.example.uberbookingexperience.ui.screens.rideConfirmed.components.BottomActions
import com.example.uberbookingexperience.ui.screens.rideConfirmed.components.DescriptionTopBar
import com.example.uberbookingexperience.ui.screens.rideConfirmed.components.DriverDescription
import com.example.uberbookingexperience.ui.screens.rideConfirmed.components.RidePin
import com.example.uberbookingexperience.ui.theme.UberBookingExperienceTheme
import com.example.uberbookingexperience.ui.theme.spacing
import com.example.uberbookingexperience.ui.util.DevicePreviews
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
        )
            .wrapContentSize()
            .verticalScroll(rememberScrollState()),
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
                    driverImageUrl = R.drawable.driver_image,
                    driverRating = 5.0,
                    driverName = "Dhaval",
                    driverTotalTrips = "7,261",
                    carImageUrl = R.drawable.wagon_r_image,
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
