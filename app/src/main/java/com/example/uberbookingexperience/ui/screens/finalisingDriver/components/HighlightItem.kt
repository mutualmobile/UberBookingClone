package com.example.uberbookingexperience.ui.screens.finalisingDriver.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.ui.theme.spacing

@Composable
fun HighlightItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    title: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape),
            painter = painterResource(id = icon),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(vertical = MaterialTheme.spacing.medium),
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}
