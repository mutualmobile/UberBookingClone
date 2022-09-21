package com.example.uberbookingexperience.ui.common.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import com.example.uberbookingexperience.ui.util.noRippleClickable

@Composable
fun SheetCollapsed(
    isCollapsed: Boolean,
    isDetailsOpen: Boolean,
    currentFraction: Float,
    onSheetClick: () -> Unit,
    sheetCollapsedFraction: Float = 0.5f,
    backgroundColor: Color = Color.Transparent,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(sheetCollapsedFraction)
            .background(backgroundColor)
            .graphicsLayer(alpha = 1f - currentFraction)
            .noRippleClickable(
                onClick = onSheetClick,
                enabled = isCollapsed && !isDetailsOpen
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isCollapsed) {
            content()
        }
    }
    /*Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(sheetCollapsedFraction)
            .background(backgroundColor)
            //.graphicsLayer(alpha = 1f - currentFraction)
            .noRippleClickable(
                onClick = onSheetClick,
                enabled = isCollapsed
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        content()
    }*/
}