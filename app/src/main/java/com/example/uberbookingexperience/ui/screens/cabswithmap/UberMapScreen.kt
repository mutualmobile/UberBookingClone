package com.example.uberbookingexperience.ui.screens.cabswithmap

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.components.DefaultAppButton
import com.example.uberbookingexperience.components.UberGoogleMap
import com.example.uberbookingexperience.model.UberCabInfo
import com.example.uberbookingexperience.ui.theme.colorWhite
import kotlinx.coroutines.launch

@Composable
fun UberMapScreen(
    windowSize: WindowSizeClass,
    onNavigationBack: () -> Unit
) {
    val exampleCabList = listOf(
        UberCabInfo(
            cabInfo = "Uber Pool", cabIcon =
            painterResource(id = R.drawable.ub__mode_nav_carpool),
            cabPrice = 80.80f,
            cabPriceAlter = 100.20f,
            carTime = "3:09 PM",
            isSelected = true
        ),
        UberCabInfo(
            cabInfo = "Uber Car",
            cabIcon =
            painterResource(id = R.drawable.ub__mode_nav_ride),
            cabPrice = 120.80f,
            cabPriceAlter = 200.20f,
            carTime = "1:19 PM",
        ),
        UberCabInfo(
            cabInfo = "Uber Bike", cabIcon =
            painterResource(id = R.drawable.ub__mode_nav_bike_scooter),
            cabPrice = 120.80f,
            carTime = "11:19 AM"
        )
    )

    Box(contentAlignment = Alignment.BottomCenter) {
        //Uber cab listing map
        //TODO: update viewmodel access code
        UberGoogleMap(viewModel = UberMapScreenVM())

        val dynamicWidth = when (windowSize.widthSizeClass) {
            WindowWidthSizeClass.Expanded -> {
               0.4f
            }
            WindowWidthSizeClass.Medium -> {
                0.5f
            }
            else -> {
                1f
            }
        }
        val dynamicHeight = when (windowSize.widthSizeClass) {
            WindowWidthSizeClass.Expanded -> {
                0.3f
            }
            /*WindowWidthSizeClass.Medium -> {
                0.2f
            }*/
            else -> {
                0.15f
            }
        }
        //Uber cab listing code
        BottomSheetWrapper(dynamicWidth, onNavigationBack) {
            UberCabsListing(exampleCabList) {

            }
        }

        Divider(thickness = 1.dp, color = Color.Transparent, modifier = Modifier.shadow(2.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(dynamicWidth)
                .fillMaxHeight(dynamicHeight)
                .background(colorWhite)
                .zIndex(2f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 14.dp, vertical = 6.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_upi_npci_logo),
                    contentDescription = stringResource(id = R.string.upi),
                    modifier = Modifier
                        .padding(horizontal = 4.dp, vertical = 10.dp)
                        .fillMaxWidth(0.1f)
                )
                Text(
                    text = stringResource(id = R.string.upi),
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 12.dp, vertical = 14.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_navigate_next_24),
                    contentDescription = stringResource(id = R.string.next),
                    modifier = Modifier
                        .padding(4.dp)
                )
            }
            DefaultAppButton(buttonText = R.string.choose_uber) {

            }

        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview
@Composable
fun UberMapScreenPreview() {
    UberMapScreen(WindowSizeClass.calculateFromSize(DpSize(200.dp,200.dp))){

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BottomSheetWrapper(
    dynamicWidth:Float=1f,
    onNavigationBack: () -> Unit,
    content: @Composable (() -> Unit) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.HalfExpanded, confirmStateChange = {false})
    var isSheetOpened by remember { mutableStateOf(false) }

    ModalBottomSheetLayout(
        sheetBackgroundColor = Color.Transparent,
        sheetState = modalBottomSheetState,
        sheetContent = {
            content {
                // Action passed for clicking close button in the content
                coroutineScope.launch {
                    modalBottomSheetState.hide() // will trigger the LaunchedEffect
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth(dynamicWidth)
            .wrapContentHeight()
    ) {}

    BackHandler {
        coroutineScope.launch {
            if (modalBottomSheetState.currentValue == ModalBottomSheetValue.Hidden) {
                modalBottomSheetState.animateTo(ModalBottomSheetValue.HalfExpanded)
            } else {
                //modalBottomSheetState.hide() // will trigger the LaunchedEffect
                //navigate back to previous screen
                onNavigationBack()
            }
        }
    }

    // Take action based on hidden state
    LaunchedEffect(modalBottomSheetState.currentValue) {
        when (modalBottomSheetState.currentValue) {
            ModalBottomSheetValue.Hidden -> {
                modalBottomSheetState.animateTo(ModalBottomSheetValue.HalfExpanded)
            }
            else -> {
                Log.i("Bottom sheet->", " ${modalBottomSheetState.currentValue} state")
            }
        }
    }
}
