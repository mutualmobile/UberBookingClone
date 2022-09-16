package com.example.uberbookingexperience.ui.screens.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DashboardScreen(onTextViewClick:  () -> Unit) {
    Text(
        text = "Dashboard Screen", modifier = Modifier
            .fillMaxSize()
            .wrapContentSize().
                clickable { onTextViewClick() }
    )
}
