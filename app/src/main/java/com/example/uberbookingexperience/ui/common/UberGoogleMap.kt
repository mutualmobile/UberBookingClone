package com.example.uberbookingexperience.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.uberbookingexperience.ui.util.rememberDeviceWidth
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.*

/**
 * Common Google map composable that we can use in the app
 * @param modifier Used to modify the overall look and behavior of the BottomSheetScaffold
 * @param cameraPositionState Defines `CameraPositionState` for GoogleMap composable to define initial state of GoogleMap having default lat-long with zoom value
 * @param mapProperties Default Google map properties we want to set for google map.
 * @param cameraPositionDefault Camera position that you want to animate after map is ready
 * @param latLngBounds Lat-Long bounds values having combine lat-longs where Google map will zoom. To use in  CameraUpdateFactory.newLatLngBounds(..)
 * @param mapZoomAnimationDuration Animation duration while google map is zoom in with camera position
 * @param mapZoomPadding map padding value that we want to set once google map zoom using lat-long or lat-long bounds
 * @param content @GoogleMapComposable related composable that we want to draw on Google map like Marker, polyline etc
 * */
@Composable
fun UberGoogleMap(
    modifier: Modifier = Modifier,
    cameraPositionState: CameraPositionState = rememberCameraPositionState(),
    mapProperties: MapProperties = MapProperties(isMyLocationEnabled = false),
    cameraPositionDefault: CameraPosition? = null,
    latLngBounds: LatLngBounds? = null,
    locationSource: LocationSource? = null,
    mapZoomAnimationDuration: Int = 1_000,
    isClickEnable: Boolean = true,
    mapZoomPadding: Int = ((rememberDeviceWidth() * 5) / 50),
    onMapClick: () -> Unit = {},
    content: (@Composable @GoogleMapComposable () -> Unit)? = null
) {


    val isMapReady = rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(isMapReady) {
        if (isMapReady.value) {
            if (cameraPositionDefault != null) { // Camera position that you want to animate after map is ready
                cameraPositionState.animate(
                    CameraUpdateFactory.newCameraPosition(
                        cameraPositionDefault
                    )
                )
            }
            if (cameraPositionDefault == null && latLngBounds != null) {
                // LatLngBounds Camera position that you want to animate after map is ready
                cameraPositionState.animate(
                    CameraUpdateFactory.newLatLngBounds(
                        latLngBounds,
                        mapZoomPadding
                    ), mapZoomAnimationDuration
                )

            }
        }


    }
    // Detect when the map starts moving and print the reason
    LaunchedEffect(cameraPositionState.isMoving) {
        if (cameraPositionState.isMoving) {
            // use "${cameraPositionState.cameraMoveStartedReason.name}" to see reason for moving
        }
    }


    Box(
        modifier
            .fillMaxSize()
    ) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState,
            onMapLoaded = {
                //Map loaded callback
                isMapReady.value = true
            },
            locationSource = locationSource,
            properties = mapProperties
        ) {
            if (content != null) {
                content()
            }

        }
        if (!isClickEnable) {
            Box(modifier = Modifier
                .matchParentSize()
                .clickable {
                    onMapClick()
                })
        }
    }
}