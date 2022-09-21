package com.example.uberbookingexperience.components

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.ui.util.rememberDeviceWidth
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.*
import kotlinx.coroutines.CoroutineScope

private const val TAG = "UberGoogleMap"

@Composable
fun UberGoogleMap(
    modifier: Modifier = Modifier,
    scope: CoroutineScope = rememberCoroutineScope(),
    cameraPositionState: CameraPositionState = rememberCameraPositionState(),
    mapProperties: MapProperties = MapProperties(isMyLocationEnabled = true),
    cameraPositionDefault: CameraPosition? = null,
    latLngBounds: LatLngBounds? = null,
    locationSource: LocationSource? = null,
    defaultZoomAnimation: Int = 1_000,
    defaultZoom: Float = 8f,
    zoomPadding: Int = ((rememberDeviceWidth() * 5) / 50),
    content: (@Composable @GoogleMapComposable () -> Unit)? = null
) {


    val isMapReady = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(isMapReady) {
        Log.d(TAG, "Updating camera position...")
        if (isMapReady.value) {
            //val cameraPosition = CameraPosition.fromLatLngZoom(LatLng(locationState.value.latitude, locationState.value.longitude), viewModel.zoom)
            if (cameraPositionDefault != null) {
                cameraPositionState.animate(
                    CameraUpdateFactory.newCameraPosition(
                        cameraPositionDefault
                    )
                )
            }
            if (cameraPositionDefault == null && latLngBounds != null) {
                cameraPositionState.animate(
                    CameraUpdateFactory.newLatLngBounds(
                        latLngBounds,
                        zoomPadding
                    ), defaultZoomAnimation
                )

            }
        }


    }
    // Detect when the map starts moving and print the reason
    LaunchedEffect(cameraPositionState.isMoving) {
        if (cameraPositionState.isMoving) {
            Log.d(
                TAG,
                "Map camera started moving due to ${cameraPositionState.cameraMoveStartedReason.name}"
            )
        }
    }


    Box(modifier.fillMaxSize()) {
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
        //Open this if you want show progress indicator till map loading
        /*if (!isMapLoaded) {
            AnimatedVisibility(
                modifier = Modifier
                    .matchParentSize(),
                visible = !isMapLoaded,
                enter = EnterTransition.None,
                exit = fadeOut()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .background(MaterialTheme.colors.background)
                        .wrapContentSize()
                )
            }
        }*/
    }
}

@Composable
fun TextViewWithEndIcon(
    labelTextId: String,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)) {
        Text(
            text = labelTextId,
            style = MaterialTheme.typography.labelMedium,
            modifier = modifier
                .wrapContentWidth()
        )
        Icon(
            painter = painterResource(id = icon),
            contentDescription = stringResource(id = R.string.next),
            modifier = Modifier
                .padding(4.dp)
        )
    }
}