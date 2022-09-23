package com.example.uberbookingexperience.ui.screens.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material.icons.filled.PunchClock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.ui.theme.spacing

@Composable
fun PickupSelection(modifier: Modifier) {
    Row(
        modifier = modifier
            .height(60.dp)
            .widthIn(max = 400.dp)
            .padding(horizontal = MaterialTheme.spacing.medium)
            .clip(RoundedCornerShape(50.dp))
            .background(color = Color(0XFFEFEFEF)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .height(40.dp)
                .weight(1.5f)
                .padding(start = MaterialTheme.spacing.medium)
                .background(color = Color.Transparent),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search Icon",
                tint = Color.Black,
                modifier = Modifier.size(36.dp)
            )
            Text(
                text = "Enter pickup point",
                color = Color.Black,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.alpha(ContentAlpha.medium)
            )
        }
        Row(
            modifier = Modifier
                .height(40.dp)
                .weight(1f)
                .padding(horizontal = MaterialTheme.spacing.medium)
                .clip(RoundedCornerShape(50.dp))
                .background(color = Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                imageVector = Icons.Default.PunchClock,
                contentDescription = "timer Icon",
                tint = Color.Black
            )
            Text(
                text = "Now",
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium,
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "drop down Icon",
                tint = Color.Black
            )
        }
    }
}

@Composable
fun DestinationSelection(modifier: Modifier) {
    Row(
        modifier = modifier
            .padding(MaterialTheme.spacing.medium)
            .heightIn(max = 50.dp)
            .widthIn(max = 360.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Default.PinDrop,
            contentDescription = "pin Icon",
            tint = Color.Black,
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .drawBehind {
                    drawCircle(
                        color = Color(0XFFEFEFEF),
                        radius = this.size.maxDimension
                    )
                }
        )
        Text(
            text = "Set destination on map",
            color = Color.Black,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = MaterialTheme.spacing.small)
        )
        Icon(
            imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = "forward Icon",
            tint = Color.Black,
            modifier = Modifier.size(14.dp)
        )
    }
}