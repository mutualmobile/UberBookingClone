package com.example.uberbookingexperience.ui.screens.cabswithmap

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.model.UberCabInfo
import com.example.uberbookingexperience.model.UberCabInfoSaver
import com.example.uberbookingexperience.ui.common.UberBackButton
import com.example.uberbookingexperience.ui.common.UberButton
import com.example.uberbookingexperience.ui.common.UberGoogleMap
import com.example.uberbookingexperience.ui.common.UberIconButton
import com.example.uberbookingexperience.ui.common.UberMapInfoWindowText
import com.example.uberbookingexperience.ui.common.bottomsheet.SheetCollapsed
import com.example.uberbookingexperience.ui.common.bottomsheet.SheetExpanded
import com.example.uberbookingexperience.ui.common.bottomsheet.UberBottomSheetScaffold
import com.example.uberbookingexperience.ui.theme.UberBookingExperienceTheme
import com.example.uberbookingexperience.ui.theme.colorBlack
import com.example.uberbookingexperience.ui.theme.colorGrayExtraLight
import com.example.uberbookingexperience.ui.theme.colorWhite
import com.example.uberbookingexperience.ui.theme.spacing
import com.example.uberbookingexperience.ui.util.bitmapDescriptorFromVector
import com.example.uberbookingexperience.ui.util.cabsForPathOne
import com.example.uberbookingexperience.ui.util.cabsForPathTwo
import com.example.uberbookingexperience.ui.util.clickableWithRipple
import com.example.uberbookingexperience.ui.util.defaultCameraPosition
import com.example.uberbookingexperience.ui.util.pathLatLongsFirst
import com.example.uberbookingexperience.ui.util.rememberBottomSheetProgress
import com.example.uberbookingexperience.ui.util.rememberDeviceHeight
import com.example.uberbookingexperience.ui.util.rememberIsMobileDevice
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.RoundCap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChooseCabTypeScreen(
    uberMapScreenViewModel: UberMapScreenVM,
    onPaymentOptionClick: () -> Unit,
    onSchedulePickupOption: () -> Unit,
    onChooseUberClick: (UberCabInfo?) -> Unit,
    onNavigationBack: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val dynamicWidth = Modifier.then(
        if (rememberIsMobileDevice()) {
            Modifier.fillMaxWidth()
        } else {
            Modifier.width(MaterialTheme.spacing.minWidth)
        }
    )

    /*==Google maps related properties*/
    var selectedUberCab by rememberSaveable(stateSaver = UberCabInfoSaver) {
        mutableStateOf(uberMapScreenViewModel.selectedItem())
    }

    var isItemSelected by rememberSaveable {
        mutableStateOf(false)
    }

    val currentDen = LocalDensity.current
    val navBottom = WindowInsets.Companion.navigationBars.getBottom(currentDen)
    /*val paddingBottom by remember {
        mutableStateOf(navBottom)
    }*/
    // TODO: add bottom padding value in translationPx count to properly hide button on y axix translation
    val translationPx by remember {
        mutableStateOf(
            with(currentDen) {
                (ButtonDefaults.MinHeight + 24.dp).toPx() + navBottom
            }
        )
    }
    /*==Bottom sheet related properties*/
    val isDeviceMobileType = rememberIsMobileDevice()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed)
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
    // define dynamic height so we can show atlease 2 list item of cabs in different screen sizes
    val sheetPeekHeight = rememberDeviceHeight().div(if (isDeviceMobileType) 2f else 1.5f)

    Box(modifier = Modifier.fillMaxSize()) {
        UberBackButton(
            modifier = Modifier
                .zIndex(3f)
                .padding(
                    vertical = MaterialTheme.spacing.extraLarge,
                    horizontal = MaterialTheme.spacing.medium
                )
                .graphicsLayer(alpha = 1f - scaffoldState.rememberBottomSheetProgress()),
            iconId = R.drawable.baseline_arrow_back_24,
            backgroundColor = if (rememberIsMobileDevice()) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                colorGrayExtraLight
            }
        ) {
            when {
                scaffoldState.bottomSheetState.isExpanded -> {
                    scope.launch {
                        scaffoldState.bottomSheetState.collapse()
                    }
                }
                isItemSelected -> {
                    isItemSelected = false
                }
                else -> {
                    onNavigationBack()
                }
            }
        }
        val currentFraction = scaffoldState.rememberBottomSheetProgress()
        Box(
            modifier = dynamicWidth
                .background(colorBlack.copy(alpha = currentFraction))
                .zIndex(2f)
                .graphicsLayer(alpha = currentFraction)
        ) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "",
                    Modifier
                        .graphicsLayer(rotationZ = currentFraction * -90f)
                        .clickableWithRipple {
                            scope.launch {
                                scaffoldState.bottomSheetState.collapse()
                            }
                        }
                        .padding(horizontal = MaterialTheme.spacing.small),
                    tint = colorWhite.copy(alpha = 0.4f)
                )
                Text(
                    "Choose a ride",
                    style = MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.Center),
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.medium)
                        .fillMaxWidth(1f)
                )
            }
        }
        if (!isDeviceMobileType) {
            Box(
                contentAlignment = Alignment.TopStart,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CabTypeGoogleMap()
            }
        }
        Box(
            modifier = if (isDeviceMobileType) Modifier.fillMaxWidth() else Modifier,
            contentAlignment = Alignment.BottomCenter
        ) {
            UberBottomSheetScaffold(
                modifier = dynamicWidth
                    .wrapContentHeight(),
                scaffoldState = scaffoldState,
                sheetShape = RectangleShape,
                sheetBackgroundColor = Color.Transparent,
                sheetPeekHeight = sheetPeekHeight.dp,
                sheetContent = {
                    SheetExpanded(
                        isExpanded = scaffoldState.bottomSheetState.isExpanded
                    ) {
                        UberCabsListing(
                            isVisibleDivider = false,
                            uberMapScreenVM = uberMapScreenViewModel,
                            currentFraction = scaffoldState.rememberBottomSheetProgress(),
                            onItemChecked = {
                                selectedUberCab = it
                            }
                        ) {
                            scope.launch {
                                scaffoldState.bottomSheetState.collapse()
                                selectedUberCab = it
                                isItemSelected = true
                            }
                        }
                    }
                    SheetCollapsed(
                        isCollapsed = scaffoldState.bottomSheetState.isCollapsed,
                        isDetailsOpen = isItemSelected,
                        currentFraction = scaffoldState.rememberBottomSheetProgress(),
                        sheetCollapsedFraction = 1f,
                        onSheetClick = sheetToggle
                    ) {
                        AnimatedVisibility(
                            visible = isItemSelected,
                            enter = slideInVertically() + expandVertically(
                                // Expand from the top.
                                expandFrom = Alignment.Top
                            ),
                            exit = slideOutVertically() + shrinkVertically(shrinkTowards = Alignment.Bottom)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .background(colorWhite)
                            ) {
                                selectedUberCab.let { uberCabInfo ->
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .padding(horizontal = 14.dp, vertical = 6.dp)
                                    ) {
                                        UberCabsListItemDetails(uberCabInfo)
                                    }
                                }
                            }
                        }
                        AnimatedVisibility(
                            visible = !isItemSelected,
                            enter = slideInVertically() + expandVertically(
                                // Expand from the top.
                                expandFrom = Alignment.Top
                            ),
                            exit = slideOutVertically() + shrinkVertically(shrinkTowards = Alignment.Top)
                        ) {
                            UberCabsListing(
                                uberMapScreenViewModel,
                                onItemChecked = {
                                    selectedUberCab = it
                                }
                            ) {
                                selectedUberCab = it
                                isItemSelected = true
                            }
                        }
                        Spacer(modifier = Modifier.padding(1.dp))
                    }
                },
                bodyContent = {
                    if (isDeviceMobileType) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(1f)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(0.55f)
                            ) {
                                CabTypeGoogleMap(
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    } else {
                        Spacer(modifier = Modifier.padding(1.dp))
                    }
                },
            )

            val bottomSheetProgress = scaffoldState.rememberBottomSheetProgress()
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = dynamicWidth
                    .wrapContentHeight()
                    .graphicsLayer { translationY = translationPx * bottomSheetProgress }
                    .background(colorWhite)
            ) {
                Divider(
                    thickness = 1.dp,
                    color = Color.Transparent,
                    modifier = Modifier.shadow(2.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                        .fillMaxWidth()
                        .clickable {
                            onPaymentOptionClick()
                        }
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    // modifier = Modifier.padding(bottom = paddingBottom.dp)
                    modifier = Modifier.navigationBarsPadding()
                ) {
                    UberButton(
                        text = "Choose " + selectedUberCab.cabInfo,
                        modifier =
                        Modifier
                            .weight(1f)
                            .padding(horizontal = 14.dp, vertical = 12.dp)
                    ) {
                        onChooseUberClick(selectedUberCab)
                    }
                    UberIconButton(
                        iconId = R.drawable.schedule_button_icon,
                        modifier = Modifier.padding(MaterialTheme.spacing.small)
                    ) {
                        onSchedulePickupOption()
                    }
                }
            }
        }
    }
    BackHandler {
        if (scaffoldState.bottomSheetState.isExpanded) {
            scope.launch {
                scaffoldState.bottomSheetState.collapse()
            }
        } else if (isItemSelected) {
            isItemSelected = false
        } else {
            onNavigationBack()
        }
    }
}

@Composable
fun CabTypeGoogleMap(modifier: Modifier = Modifier) {
    val positionBuilder = LatLngBounds.Builder()
    positionBuilder.include(pathLatLongsFirst.first())
    positionBuilder.include(pathLatLongsFirst.last())
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
        modifier = modifier,
        cameraPositionState = rememberCameraPositionState {
            position = defaultCameraPosition
        },
        latLngBounds = positionBuilder.build()
    ) {
        cabsForPathOne.forEach {
            Marker(
                MarkerState(it),
                icon = BitmapDescriptorFactory.fromResource(R.mipmap.ub__marker_vehicle_fallback)
            )
        }
        cabsForPathTwo.forEach {
            Marker(
                MarkerState(it),
                icon = BitmapDescriptorFactory.fromResource(R.mipmap.ub__marker_vehicle_fallback)
            )
        }

        MarkerInfoWindowContent(
            MarkerState(pathLatLongsFirst.first()),
            icon = bitmapDescriptorFromVector(LocalContext.current, R.drawable.ic_location_end)
        ) {
            UberMapInfoWindowText("My start Location", R.drawable.baseline_navigate_next_24)
        }
        MarkerInfoWindowContent(
            MarkerState(pathLatLongsFirst.last()),
            icon = bitmapDescriptorFromVector(LocalContext.current, R.drawable.ic_location_start)
        ) {
            UberMapInfoWindowText("My end Location", R.drawable.baseline_navigate_next_24)
        }
        Polyline(
            points = pathLatLongsFirst,
            color = animateColor,
            clickable = true,
            startCap = RoundCap(),
            endCap = RoundCap(),
            jointType = JointType.ROUND
        )
    }
}

@Preview(showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Preview(showSystemUi = true, device = "spec:width=673.5dp,height=841dp,dpi=480")
@Preview(showSystemUi = true, device = "spec:width=1280dp,height=800dp,dpi=480")
@Preview(showSystemUi = true, device = "spec:width=1920dp,height=1080dp,dpi=480")
@Composable
private fun UberMapScreenScreenPreview() {
    UberBookingExperienceTheme {
        ChooseCabTypeScreen(UberMapScreenVM(), {}, {}, {}) {}
    }
}
