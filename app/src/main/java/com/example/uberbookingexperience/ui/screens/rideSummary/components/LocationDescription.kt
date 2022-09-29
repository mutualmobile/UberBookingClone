package com.example.uberbookingexperience.ui.screens.rideSummary.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.uberbookingexperience.ui.theme.spacing

@Composable
fun LocationDescription(title: String, whereTo: String, dateTimeString: String) {
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.medium),
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Medium
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "To $whereTo",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Medium
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.medium),
            text = dateTimeString,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium
        )
    }
}
