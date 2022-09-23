package com.example.uberbookingexperience.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.uberbookingexperience.ui.screens.dashboard.components.*
import com.example.uberbookingexperience.ui.theme.UberBookingExperienceTheme
import com.example.uberbookingexperience.ui.theme.spacing
import com.example.uberbookingexperience.ui.util.rememberIsMobileDevice

@Composable
fun DashboardScreen(onGotoMap: () -> Unit) {
    val isMobile = rememberIsMobileDevice()
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(color = Color.White)
    ) {
        //header
        HorizontalPagerWithIndicator(isMobile)
        //options
        QuickOptions(isMobile)
        //start/end location
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        PickupSelection(Modifier.align(Alignment.CenterHorizontally))
        DestinationSelection(Modifier.align(Alignment.CenterHorizontally))
        //around you
        AroundYou(isMobile, onGotoMap)
    }
}

@Preview(showSystemUi = true, device = "spec:width=1920dp,height=1080dp,dpi=480")
@Composable
private fun DashboardScreenPreview() {
    UberBookingExperienceTheme {
        DashboardScreen() {}
    }
}