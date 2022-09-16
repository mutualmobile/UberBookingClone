package com.example.uberbookingexperience.ui.screens.cabswithmap

import android.location.Location
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import kotlin.random.Random
import kotlinx.coroutines.flow.flow

class UberMapScreenVM : ViewModel() {
    val zoom = 8f
    val mmLocation = LatLng(-31.673, 128.892)
    val defaultCameraPosition = CameraPosition.fromLatLngZoom(mmLocation, zoom)
    var startLocation: LatLng = LatLng(-31.673, 128.892)
    var endLocation: LatLng =  LatLng(-12.4258, 130.7932)


    val locationFlow = flow {
        emit(newLocation())
    }

    fun setLocations(start: LatLng, end: LatLng) {
        startLocation = start
        endLocation = end
    }

    fun newLocation(): Location {
        val location = Location("MyLocationProvider")
        location.apply {
            latitude = mmLocation.latitude + Random.nextFloat()
            longitude = mmLocation.longitude + Random.nextFloat()
        }
        return location
    }
}
