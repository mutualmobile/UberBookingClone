package com.example.uberbookingexperience.ui.screens.rideSummary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.uberbookingexperience.ui.common.UberButton
import com.example.uberbookingexperience.ui.common.UberDivider
import com.example.uberbookingexperience.ui.screens.rideSummary.components.LocationDescription
import com.example.uberbookingexperience.ui.screens.rideSummary.components.PaymentDescription
import com.example.uberbookingexperience.ui.theme.UberBookingExperienceTheme
import com.example.uberbookingexperience.ui.theme.spacing
import com.example.uberbookingexperience.ui.util.DevicePreviews
import com.example.uberbookingexperience.ui.util.LargeScreenChildMaxWidth
import com.example.uberbookingexperience.ui.util.limitWidth
import com.example.uberbookingexperience.ui.util.rememberIsMobileDevice

@Composable
fun RideSummaryScreen(onButtonClick: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .then(
                if (rememberIsMobileDevice()) {
                    Modifier.fillMaxWidth()
                } else {
                    Modifier.width(LargeScreenChildMaxWidth)
                }
            )
            .wrapContentSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(MaterialTheme.spacing.medium)
            ) {
                LocationDescription("Upcoming Ride","Dev Arc Commercial Complex", "Fri, 30 Sep 14:16 IST")
                UberDivider()
                PaymentDescription(tentativeEstimate = "₹404-486")
                UberButton(
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.large)
                        .limitWidth(),
                    text = "Done",
                    onClick = onButtonClick
                )
            }
        }
    }
}

@DevicePreviews
@Composable
private fun RideSummaryScreenPreview() {
    UberBookingExperienceTheme {
        Column(
            modifier = Modifier.then(
                if (rememberIsMobileDevice()) {
                    Modifier.fillMaxWidth()
                } else {
                    Modifier.width(LargeScreenChildMaxWidth)
                }
            )
        ) {
            RideSummaryScreen()
        }
    }
}