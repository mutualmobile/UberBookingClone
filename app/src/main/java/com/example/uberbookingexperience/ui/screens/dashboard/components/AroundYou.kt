package com.example.uberbookingexperience.ui.screens.dashboard.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.ui.common.UberGoogleMap
import com.example.uberbookingexperience.ui.theme.spacing
import com.example.uberbookingexperience.ui.util.defaultCameraPosition
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun AroundYou(isMobile: Boolean, onGotoMap: () -> Unit) {
    val mapHeightForLargerSize = LocalConfiguration.current.screenHeightDp.dp / 2

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 200.dp)
            .padding(horizontal = MaterialTheme.spacing.medium)
    ) {
        Text(
            "Around You",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        UberGoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = if (isMobile) 150.dp else mapHeightForLargerSize)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(16.dp)),
            cameraPositionState = rememberCameraPositionState {
                position = defaultCameraPosition
            },
            isClickEnable = false,
            onMapClick = onGotoMap
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
    }
}
