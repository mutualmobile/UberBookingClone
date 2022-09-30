package com.example.uberbookingexperience.ui.screens.dashboard.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
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
import com.example.uberbookingexperience.ui.util.clickableWithRipple
import com.example.uberbookingexperience.ui.util.defaultCameraPosition
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun AroundYou(isMobile: Boolean, screenWidth: Dp, onGotoMap: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 200.dp)
            .padding(horizontal = MaterialTheme.spacing.medium)
            .then(
                if (isMobile) Modifier.padding(bottom = MaterialTheme.spacing.medium)
                else Modifier.navigationBarsPadding()
            )

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
                .heightIn(min = if (isMobile) 150.dp else (screenWidth / 4))
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(16.dp)),
            cameraPositionState = rememberCameraPositionState {
                position = defaultCameraPosition
            },
            nonMapContent = {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clickableWithRipple(onClick = onGotoMap)
                ) {}
            }
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
    }
}
