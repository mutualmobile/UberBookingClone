package com.example.uberbookingexperience.components

import android.location.Location
import android.util.Log
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.uberbookingexperience.ui.screen.UberMapScreenVM
import com.example.uberbookingexperience.ui.screen.newLocation
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.RoundCap
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

private const val zoom = 8f

//val mmLocation = LatLng(17.4805319,78.2195281)
val mmLocation = LatLng(-31.673, 128.892)
val collageLocation = LatLng(17.548553, 78.2050106)
private val defaultCameraPosition = CameraPosition.fromLatLngZoom(mmLocation, zoom)
private const val TAG = "UberGoogleMap"

@Composable
fun UberGoogleMap(viewModel: UberMapScreenVM) {

    val locationSource = UberMapLocationSource()
    var isMapLoaded by remember { mutableStateOf(false) }
    val cameraPositionState = rememberCameraPositionState {
        position = defaultCameraPosition
    }
    val mapProperties by remember { mutableStateOf(MapProperties(isMyLocationEnabled = true)) }

    val locationState = viewModel.locationFlow.collectAsState(initial = newLocation())

    LaunchedEffect(key1 = locationState.value) {
        locationSource.onLocationChanged(locationState.value)

        Log.d(TAG, "Updating camera position...")
        /*val cameraPosition = CameraPosition.fromLatLngZoom(LatLng(locationState.value.latitude, locationState.value.longitude), zoom)
        cameraPositionState.animate(CameraUpdateFactory.newCameraPosition(cameraPosition), 1_000)*/
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
    Box(Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState,
            onMapLoaded = {
                isMapLoaded = true
            },
            locationSource = locationSource,
            properties = mapProperties
        ) {

            Polyline(
                points = listOf(
                    LatLng(-31.673, 128.892),
                    LatLng(-31.952, 115.857),
                    LatLng(-17.785, 122.258),
                    LatLng(-12.4258, 130.7932)
                ),
                color = animateColor,
                clickable = true,
                startCap = RoundCap(),
                endCap = RoundCap(),
                jointType = JointType.ROUND
            )
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

private class UberMapLocationSource : LocationSource {

    private var listener: LocationSource.OnLocationChangedListener? = null

    override fun activate(listener: LocationSource.OnLocationChangedListener) {
        this.listener = listener
    }

    override fun deactivate() {
        listener = null
    }

    fun onLocationChanged(location: Location) {
        listener?.onLocationChanged(location)
    }
}