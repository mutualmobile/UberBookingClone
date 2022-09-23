package com.example.uberbookingexperience.ui.screens.dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.ui.screens.dashboard.RideOptions
import com.example.uberbookingexperience.ui.screens.dashboard.getRideOptions
import com.example.uberbookingexperience.ui.theme.spacing
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun QuickOptions(isMobile: Boolean) {
    val options = getRideOptions()
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = MaterialTheme.spacing.medium,
                top = MaterialTheme.spacing.large,
                bottom = MaterialTheme.spacing.medium
            )
    ) {
        if (isMobile) {
            QuickOptionsLargeTile(modifier = Modifier.weight(1f), options[0])
            QuickOptionsLargeTile(modifier = Modifier.weight(1f), options[1])
        }
    }

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = MaterialTheme.spacing.medium)
    ) {
        options.map {
            QuickOptionsTile(modifier = Modifier, it)
        }
    }
}

@Composable
fun QuickOptionsLargeTile(modifier: Modifier, rideOption: RideOptions) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(end = MaterialTheme.spacing.medium)
            .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
            .background(color = Color(0XFFEFEFEF))
    ) {
        Image(
            painter = painterResource(id = rideOption.image),
            contentDescription = "option image",
            modifier = Modifier.requiredSize(94.dp).align(Alignment.TopEnd)
        )
        Text(
            text = rideOption.title,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(16.dp).align(Alignment.BottomStart),
        )
    }
}

@Composable
fun QuickOptionsTile(modifier: Modifier, rideOption: RideOptions) {
        Box(
            modifier = modifier
                .size(84.dp)
                .padding(end = MaterialTheme.spacing.medium, bottom = MaterialTheme.spacing.medium)
                .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
                .background(color = Color(0XFFEFEFEF)),
        ) {
            Image(
                painter = painterResource(id = rideOption.image),
                contentDescription = "option image",
                modifier = Modifier.requiredSize(60.dp).align(Alignment.TopCenter)
            )
            Text(
                text = rideOption.title,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(4.dp).align(Alignment.BottomCenter),
            )
        }
}