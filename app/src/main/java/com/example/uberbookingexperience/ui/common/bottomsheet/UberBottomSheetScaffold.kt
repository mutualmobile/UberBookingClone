package com.example.uberbookingexperience.ui.common.bottomsheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.example.uberbookingexperience.ui.theme.colorWhite

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UberBottomSheetScaffold(
    modifier: Modifier = Modifier,
    bottomSheetDynamicWidthFraction: Float = 1f,
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed)
    ),
    sheetShape: Shape = RectangleShape,
    sheetBackgroundColor: Color = colorWhite,
    sheetContentColor: Color = Color.Transparent,
    sheetPeekHeight: Dp = BottomSheetScaffoldDefaults.SheetPeekHeight,
    bottomSheetBodyContentHeightFraction: Float = 1f,
    sheetContent: @Composable BoxScope.() -> Unit,
    bodyContent: @Composable BoxScope.() -> Unit,
) {
    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        sheetShape = sheetShape,
        sheetBackgroundColor = sheetBackgroundColor,
        sheetContentColor = sheetContentColor,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = bottomSheetBodyContentHeightFraction)
            ) {
                sheetContent()
            }
        }, sheetPeekHeight = sheetPeekHeight
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            bodyContent()
        }
    }

}