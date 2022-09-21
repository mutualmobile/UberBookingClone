package com.example.uberbookingexperience.ui.common.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex
import com.example.uberbookingexperience.ui.theme.colorWhite

@Composable
fun SheetExpanded(
    isExpanded: Boolean,
    backgroundColor: Color = colorWhite,
    content: @Composable BoxScope.() -> Unit
) {

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .zIndex(if (isExpanded) 2f else 0f)
    ) {
        if (isExpanded) {
            content()
        }
    }
}