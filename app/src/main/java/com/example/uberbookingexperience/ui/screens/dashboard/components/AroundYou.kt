package com.example.uberbookingexperience.ui.screens.dashboard.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.ui.common.UberGoogleMap
import com.example.uberbookingexperience.ui.theme.spacing
import com.example.uberbookingexperience.ui.util.defaultCameraPosition
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun AroundYou(isMobile: Boolean, screenWidth: Dp, onGotoMap: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 200.dp)
            .padding(horizontal = MaterialTheme.spacing.medium)
    ) {
        Text(
            "Around You",
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Medium)
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        UberGoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = if (isMobile) 150.dp else (screenWidth / 2))
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(16.dp)),
            cameraPositionState = rememberCameraPositionState {
                position = defaultCameraPosition
            },
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
    }
}
