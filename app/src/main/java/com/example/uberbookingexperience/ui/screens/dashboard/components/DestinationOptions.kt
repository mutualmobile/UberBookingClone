package com.example.uberbookingexperience.ui.screens.dashboard.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.ui.theme.colorGrayExtraLight
import com.example.uberbookingexperience.ui.theme.spacing
import com.example.uberbookingexperience.ui.util.clickableWithRipple
import com.example.uberbookingexperience.ui.util.limitWidth

@Composable
fun PickupSelection(modifier: Modifier, onGotoWhereScreen: () -> Unit) {
    Row(
        modifier = modifier
            .height(60.dp)
            .limitWidth()
            .padding(horizontal = MaterialTheme.spacing.medium)
            .clip(RoundedCornerShape(50.dp))
            .background(color = colorGrayExtraLight)
            .clickableWithRipple {
                onGotoWhereScreen()
            },
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
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(36.dp)
            )
            Text(
                text = "Enter pickup point",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.alpha(ContentAlpha.medium)
            )
        }
        Row(
            modifier = Modifier
                .height(40.dp)
                .weight(1f)
                .padding(horizontal = MaterialTheme.spacing.medium)
                .clip(RoundedCornerShape(50.dp))
                .background(color = MaterialTheme.colorScheme.onPrimary),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                imageVector = Icons.Default.AccessTimeFilled,
                contentDescription = "timer Icon",
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Now",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium
                ),
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "drop down Icon",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun DestinationSelection(modifier: Modifier, onGotoWhereScreen: () -> Unit) {
    Row(
        modifier = modifier
            .padding(MaterialTheme.spacing.medium)
            .heightIn(max = 50.dp)
            .widthIn(max = 360.dp)
            .clip(RoundedCornerShape(50.dp))
            .clickableWithRipple {
                onGotoWhereScreen()
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(contentAlignment = Alignment.Center) {
            Circle()
            Image(
                painter = painterResource(id = R.drawable.location_pin),
                contentDescription = "pin Icon",
                modifier = Modifier.requiredSize(24.dp)
            )
        }
        Text(
            text = "Set destination on map",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = MaterialTheme.spacing.small)
        )
        Icon(
            imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = "forward Icon",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(14.dp)
        )
    }
}

@Composable
fun Circle(){
    Canvas(modifier = Modifier.size(50.dp), onDraw = {
        drawCircle(color = colorGrayExtraLight)
    })
}