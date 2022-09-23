package com.example.uberbookingexperience.ui.screens.confirmPickupScreen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.ui.common.*
import com.example.uberbookingexperience.ui.common.bottomsheet.UberBottomSheetScaffold
import com.example.uberbookingexperience.ui.screens.finalisingDriver.FinalisingDriverScreen
import com.example.uberbookingexperience.ui.screens.rideConfirmed.RideConfirmedScreen
import com.example.uberbookingexperience.ui.theme.colorLocationUI
import com.example.uberbookingexperience.ui.theme.colorUberGrayBg
import com.example.uberbookingexperience.ui.theme.colorWhite
import com.example.uberbookingexperience.ui.theme.spacing
import com.example.uberbookingexperience.ui.util.defaultCameraPosition
import com.example.uberbookingexperience.ui.util.pathLatLongsFirst
import com.example.uberbookingexperience.ui.util.rememberDeviceHeight
import com.example.uberbookingexperience.ui.util.rememberIsMobileDevice
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ConfirmPickupScreen(
    onSearchClick: () -> Unit = {},
    onNavigationBack: () -> Unit = {},
    onChooseConfirmLocationClick: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    val dynamicWidth: Float
    val dynamicPadding: Dp

    if (rememberIsMobileDevice()) {
        dynamicWidth = 1f
        dynamicPadding = 10.dp
    } else {
        dynamicWidth = 0.35f
        dynamicPadding = 60.dp
    }

    var isLocationConfirmed by rememberSaveable {
        mutableStateOf(false)
    }
    var bottomSheetCase by rememberSaveable {
        mutableStateOf(1)
    }
    /*==Bottom sheet related properties*/
    val isDeviceMobileType = rememberIsMobileDevice()

    var scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            BottomSheetValue.Collapsed
        )
    )
    val sheetToggle: () -> Unit = {
        scope.launch {
            if (scaffoldState.bottomSheetState.isCollapsed) {
                scaffoldState.bottomSheetState.expand()
            } else {
                scaffoldState.bottomSheetState.collapse()
            }
        }
    }
    val screenHeight = rememberDeviceHeight()
    //define dynamic height so we can show atlease 2 list item of cabs in different screen sizes
    var sheetPeekHeight by rememberSaveable {
        mutableStateOf(screenHeight.div(if (isDeviceMobileType) 3f else 2.4f))
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .navigationBarsPadding()) {
        Box(
            modifier = if (isDeviceMobileType) Modifier
                .fillMaxWidth()
                .zIndex(2f) else Modifier.zIndex(2f),
            contentAlignment = Alignment.BottomCenter
        ) {
            UberBottomSheetScaffold(
                modifier = Modifier
                    .fillMaxWidth(dynamicWidth)
                    .fillMaxHeight(),
                bottomSheetDynamicWidthFraction = dynamicWidth,
                scaffoldState = scaffoldState,
                sheetShape = RectangleShape,
                sheetBackgroundColor = Color.Transparent,
                sheetPeekHeight = sheetPeekHeight.dp,
                sheetContent = {

                    if (bottomSheetCase == 2) {
                        // case to show finalising bottom sheet with animation
                        FinalisingDriverScreen(
                            onAnimationFinished = {
                                val deviceHeight = screenHeight.div(1.25f)
                                sheetPeekHeight = deviceHeight
                                bottomSheetCase = 3
                            }
                        ) {
                            // on cancel click
                            onNavigationBack()
                        }
                    } else if (bottomSheetCase == 3) {
                        //case for ride confirm screen
                        RideConfirmedScreen(scaffoldState.bottomSheetState.isExpanded)
                    } else {
                        // normal bottom sheet content for choose pickup spot
                        Column(
                            modifier = Modifier
                                .systemBarsPadding()
                                .background(colorWhite),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                modifier = Modifier.padding(MaterialTheme.spacing.medium),
                                text = "Choose you pickup spot",
                                style = MaterialTheme.typography.titleLarge
                            )
                            UberDivider()
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(
                                    horizontal = MaterialTheme.spacing.medium,
                                    vertical = MaterialTheme.spacing.large
                                )
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(vertical = MaterialTheme.spacing.medium)
                                        .weight(1f),
                                    text = "Near Home",
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Text(
                                    modifier = Modifier
                                        .wrapContentHeight()
                                        .wrapContentWidth()
                                        .background(
                                            colorUberGrayBg,
                                            RoundedCornerShape(MaterialTheme.spacing.extraLarge)
                                        )
                                        .padding(
                                            horizontal = MaterialTheme.spacing.large,
                                            vertical = MaterialTheme.spacing.small
                                        )
                                        .clickable {
                                            onSearchClick()
                                        },
                                    text = "Search",
                                    style = MaterialTheme.typography.titleLarge.copy(textAlign = TextAlign.Center)
                                )
                            }
                            UberButton(
                                text = "Confirm Pickup",
                                modifier = Modifier.padding(MaterialTheme.spacing.medium)
                            ) {
                                //onChooseConfirmLocationClick()
                                isLocationConfirmed = true
                            }
                            Spacer(modifier = Modifier.padding(22.dp))
                        }
                    }

                }, bodyContent = {
                    if (isDeviceMobileType) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxSize(1f)
                        ) {
                            Box(
                                contentAlignment = Alignment.TopStart,
                                modifier = Modifier
                                    .fillMaxSize(1f)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    ShowConfirmLocationGoogleMap(
                                        modifier = Modifier.weight(1f)
                                    )
                                }

                                UberBackButton(
                                    modifier = Modifier.padding(
                                        vertical = 22.dp,
                                        horizontal = 8.dp
                                    ), iconId = R.drawable.baseline_arrow_back_24
                                ) {
                                    onNavigationBack()
                                }

                            }
                            ShowCurrentLocationUI()
                        }

                    } else {
                        Spacer(modifier = Modifier.padding(1.dp))
                    }
                })

        }
        if (!isDeviceMobileType) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize(1f)
                    .padding(bottom = dynamicPadding)
            ) {
                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    ShowConfirmLocationGoogleMap()
                    UberBackButton(
                        modifier = Modifier.padding(
                            vertical = 22.dp,
                            horizontal = 8.dp
                        ), iconId = R.drawable.baseline_arrow_back_24
                    ) {
                        onNavigationBack()
                    }
                }
                if (bottomSheetCase == 1) {
                    ShowCurrentLocationUI()
                }
            }
        }

        if (isLocationConfirmed) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(2f), contentAlignment = Alignment.Center
            ) {
                UberLoader(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),
                    text = "Processing your request...",
                    loaderColor = MaterialTheme.colorScheme.surface
                )
            }
            scaffoldState = rememberBottomSheetScaffoldState(
                bottomSheetState = rememberBottomSheetState(
                    BottomSheetValue.Collapsed
                )
            )
            val deviceHeight = screenHeight.div(1.35f)
            LaunchedEffect(isLocationConfirmed) {

            delay(5000)
                sheetPeekHeight = deviceHeight
                bottomSheetCase = 2
                isLocationConfirmed = false
                scaffoldState.bottomSheetState.collapse()
            }

        }
    }
    BackHandler {
        onNavigationBack()
    }
}

@Composable
fun ShowConfirmLocationGoogleMap(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition()
    val animateColor by infiniteTransition.animateColor(
        initialValue = Color(0xFFF4F4F4),
        targetValue = Color(0xFF000000),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    UberGoogleMap(
        modifier = modifier, cameraPositionState = rememberCameraPositionState {
            position = defaultCameraPosition
        },
        cameraPositionDefault = CameraPosition.fromLatLngZoom(pathLatLongsFirst.first(), 25f)
    ) {


    }
}

@Composable
fun ShowCurrentLocationUI() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .background(
                    colorLocationUI,
                    RoundedCornerShape(MaterialTheme.spacing.extraLarge)
                )
                .padding(
                    horizontal = MaterialTheme.spacing.large,
                    vertical = MaterialTheme.spacing.small
                ),
            text = "Pick-up here",
            color = colorWhite,
            style = MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.Center)
        )
        Divider(
            color = colorLocationUI, modifier = Modifier
                .width(2.dp)
                .height(26.dp)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(32.dp)
                .background(colorLocationUI, CircleShape)
        ) {
            Box(
                Modifier
                    .size(10.dp)
                    .background(colorWhite, RectangleShape)
                    .zIndex(2f))
        }
    }
}

@Preview
@Composable
fun ShowCurrentLocationUIPreview() {
    ShowCurrentLocationUI()
}

@Preview
@Composable
fun ConfirmPickupScreenPreview() {
    ConfirmPickupScreen()
}