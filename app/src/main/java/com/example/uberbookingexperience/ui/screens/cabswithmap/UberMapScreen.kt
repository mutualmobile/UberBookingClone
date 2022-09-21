package com.example.uberbookingexperience.ui.screens.cabswithmap

import android.annotation.SuppressLint
import android.location.Location
import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.components.TextViewWithEndIcon
import com.example.uberbookingexperience.components.UberGoogleMap
import com.example.uberbookingexperience.model.UberCabInfo
import com.example.uberbookingexperience.ui.common.UberButton
import com.example.uberbookingexperience.ui.common.UberIconButton
import com.example.uberbookingexperience.ui.common.bottomsheet.SheetCollapsed
import com.example.uberbookingexperience.ui.common.bottomsheet.SheetExpanded
import com.example.uberbookingexperience.ui.common.bottomsheet.UberBottomSheetScaffold
import com.example.uberbookingexperience.ui.theme.UberBookingExperienceTheme
import com.example.uberbookingexperience.ui.theme.colorWhite
import com.example.uberbookingexperience.ui.util.currentFraction
import com.example.uberbookingexperience.ui.util.rememberDeviceHeight
import com.example.uberbookingexperience.ui.util.rememberIsMobileDevice
import com.google.android.gms.maps.model.*
import com.google.maps.android.compose.*
import kotlin.random.Random
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun UberMapScreen(
    uberMapScreenViewModel: UberMapScreenVM,
    onPaymentOptionClick: () -> Unit,
    onSchedulePickupOption: () -> Unit,
    onNavigationBack: () -> Unit
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

    /*==Google maps related properties*/
    var selectedUberCab: UberCabInfo? = null
    var isItemSelected = MutableStateFlow(false)

    /*==Bottom sheet related properties*/
    val isDeviceMobileType = rememberIsMobileDevice()
    //TODO: make savable and full screen issue
    val isSelected by isItemSelected.collectAsState(false)
    var selectedIndexState by remember {
        mutableStateOf(1)
    }
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
    val sheetPeekHeight = rememberDeviceHeight().div(if (isDeviceMobileType) 2f else 1.5f)
    val mapHeight = rememberDeviceHeight().minus(sheetPeekHeight).dp
    Row(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = if (isDeviceMobileType) Modifier.fillMaxWidth() else Modifier,
            contentAlignment = Alignment.BottomCenter
        ) {
            UberBottomSheetScaffold(modifier = Modifier
                .fillMaxWidth(dynamicWidth)
                .fillMaxHeight(),
                bottomSheetDynamicWidthFraction = dynamicWidth,
                scaffoldState = scaffoldState,
                sheetShape = RectangleShape,
                sheetBackgroundColor = Color.Transparent,
                sheetContentColor = Color.Transparent,
                sheetPeekHeight = sheetPeekHeight.dp,
                sheetContent = {
                    SheetExpanded(
                        isExpanded = scaffoldState.bottomSheetState.isExpanded
                    ) {
                        UberCabsListing(
                            isVisibleDivider = false,
                            uberMapScreenVM = uberMapScreenViewModel
                        ) {
                            scope.launch {
                                scaffoldState.bottomSheetState.collapse()
                                selectedUberCab = it
                                isItemSelected.value = true
                            }
                        }
                    }
                    SheetCollapsed(
                        isCollapsed = scaffoldState.bottomSheetState.isCollapsed,
                        isDetailsOpen = isSelected,
                        currentFraction = scaffoldState.currentFraction,
                        sheetCollapsedFraction = 0.4f,
                        onSheetClick = sheetToggle
                    ) {
                        AnimatedVisibility(
                            visible = !isSelected,
                            enter = slideInVertically() + expandVertically(
                                // Expand from the top.
                                expandFrom = Alignment.Top
                            ),
                            exit = slideOutVertically() + shrinkVertically(shrinkTowards = Alignment.Top)
                        ) {
                            UberCabsListing(uberMapScreenViewModel) {
                                selectedUberCab = it
                                isItemSelected.value = true
                                val current = selectedIndexState
                                selectedIndexState = if (current == 1) 2 else 1
                            }
                        }
                        AnimatedVisibility(
                            visible = isSelected,
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
                                uberMapScreenViewModel.selectedUberCab?.let { it1 ->
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .padding(horizontal = 14.dp, vertical = 6.dp)
                                    ) {
                                        UberCabsListItemDetails(it1)
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.padding(1.dp))
                    }
                }, bodyContent = {
                    if (isDeviceMobileType) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.55f)
                        ) {
                            ShowGoogleMap(
                                modifier = Modifier.weight(1f),
                                itemSelectedIndex = selectedIndexState
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.padding(1.dp))
                    }
                })

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth(dynamicWidth)
                    .wrapContentHeight()
                    .graphicsLayer(alpha = 1f - scaffoldState.currentFraction)
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
                    androidx.compose.material3.Text(
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
                    modifier = Modifier.padding(bottom = dynamicPadding)
                ) {
                    UberButton(
                        text = stringResource(id = R.string.choose_uber), modifier =
                        Modifier
                            .weight(1f)
                            .padding(horizontal = 14.dp, vertical = 12.dp)
                    ) {

                    }
                    UberIconButton(
                        iconId = R.drawable.schedule_button_icon,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        onSchedulePickupOption()
                    }
                }

            }
        }
        if (!isDeviceMobileType) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = dynamicPadding)
            ) {
                ShowGoogleMap(itemSelectedIndex = selectedIndexState)
            }
        }
    }
    BackHandler {
        if (scaffoldState.bottomSheetState.isExpanded) {
            scope.launch {
                scaffoldState.bottomSheetState.collapse()
            }
        } else if (isSelected) {
            isItemSelected.value = false
        } else {
            onNavigationBack()
        }
    }
}

@Composable
fun ShowGoogleMap(modifier: Modifier = Modifier, itemSelectedIndex: Int = 0) {
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
        modifier = modifier, cameraPositionState = rememberCameraPositionState {
            position = defaultCameraPosition
        },
        latLngBounds = positionBuilder.build(),
        defaultZoom = 75f,
        cameraPositionDefault = CameraPosition.fromLatLngZoom(pathLatLongsFirst.first(), 25f)
    ) {

        cabsForPathOne.forEach {
            Marker(
                MarkerState(it),
                icon = BitmapDescriptorFactory.fromResource(R.mipmap.ub__marker_vehicle_fallback),
            )
        }
        cabsForPathTwo.forEach {
            Marker(
                MarkerState(it),
                icon = BitmapDescriptorFactory.fromResource(R.mipmap.ub__marker_vehicle_fallback),
            )
        }


        MarkerInfoWindowContent(
            MarkerState(pathLatLongsFirst.first()),
            icon = BitmapDescriptorFactory.fromResource(R.drawable.ub__ic_marker_destination)
        ) {
            TextViewWithEndIcon("My start Location", R.drawable.baseline_navigate_next_24)
        }
        MarkerInfoWindowContent(
            MarkerState(pathLatLongsFirst.last()),
            icon = BitmapDescriptorFactory.fromResource(R.drawable.ub__ic_marker_pickup)
        ) {
            TextViewWithEndIcon("My end Location", R.drawable.baseline_navigate_next_24)
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

val zoom = 8f
val zoomAnimationDuration = 1_000
val mmLocation = LatLng(23.117983, 72.537436)
val defaultCameraPosition = CameraPosition.fromLatLngZoom(mmLocation, zoom)

// For one location related path and cab pin location
val testCabLocation = LatLng(23.068085, 72.523016)
val testCabLocationTwo = LatLng(23.056713, 72.528510)
val testCabLocationThree = LatLng(23.068085, 72.507567)

/*val pathLatLongsFirst = listOf(LatLng(23.117983, 72.537436),LatLng(23.117668, 72.538123)
    ,testCabLocation, LatLng(23.129807, 72.541251),
    LatLng(23.003948, 72.501044), LatLng(22.9985471,72.4468048),
)*/
val cabsForPathOne = listOf(testCabLocation, testCabLocationThree, testCabLocationTwo)
var startLocation: LatLng = LatLng(23.117983, 72.537436)
var endLocation: LatLng = LatLng(22.979615, 72.492701)
val pathLatLongsFirst = listOf(
    startLocation, LatLng(23.117668, 72.538123),
    LatLng(23.068085, 72.523016), LatLng(23.042497, 72.513747),
    LatLng(23.003948, 72.501044), endLocation,
)

// For second location related path and cab pin location
val pathLatLongsTwo = listOf(
    LatLng(23.1526849, 72.5126409), LatLng(23.220379, 72.634520),
    LatLng(23.209967, 72.619071),
    LatLng(23.189260, 72.608210),
    LatLng(23.172397, 72.578137),
    LatLng(23.160581, 72.556924),
    LatLng(23.132514, 72.541643),
)
val testCab2Location = LatLng(23.202101, 72.622791)
val testCab2LocationTwo = LatLng(23.193286, 72.599712)
val testCab2LocationThree = LatLng(23.184194, 72.577533)
val testCab2LocationFour = LatLng(23.147544, 72.542165)
val cabsForPathTwo =
    listOf(testCab2Location, testCab2LocationThree, testCab2LocationTwo, testCab2LocationFour)

//var startLocation: LatLng = pathLatLongsFirst.first()
//var endLocation: LatLng =  pathLatLongsFirst.last()
fun newLocation(): Location {
    val location = Location("MyLocationProvider")
    location.apply {
        latitude = mmLocation.latitude + Random.nextFloat()
        longitude = mmLocation.longitude + Random.nextFloat()
    }
    return location
}

@Composable
fun dynamicPercentageHeight(): Float {
    return if (rememberIsMobileDevice()) {
        0.35f
    } else {
        0.45f
    }
}

@Preview(showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Preview(showSystemUi = true, device = "spec:width=673.5dp,height=841dp,dpi=480")
@Preview(showSystemUi = true, device = "spec:width=1280dp,height=800dp,dpi=480")
@Preview(showSystemUi = true, device = "spec:width=1920dp,height=1080dp,dpi=480")
@Composable
private fun UberMapScreenScreenPreview() {
    UberBookingExperienceTheme {
        UberMapScreen(UberMapScreenVM(), {}, {}) {}
    }
}