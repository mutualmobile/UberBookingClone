package com.example.uberbookingexperience.ui.screens.rideConfirmed.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uberbookingexperience.ui.common.UberDivider
import com.example.uberbookingexperience.ui.theme.spacing

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
