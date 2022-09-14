package com.example.uberbookingexperience.ui.screen

import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uberbookingexperience.components.mmLocation
import kotlin.random.Random
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.shareIn

class UberMapScreenVM:ViewModel() {


    val locationFlow = callbackFlow {
        while (true) {

            val location = newLocation()
            Log.d("-->", "Location : $location")
            trySend(location)

            delay(2_000)
        }
    }.shareIn(
        viewModelScope,
        replay = 0,
        started = SharingStarted.WhileSubscribed()
    )
}
fun newLocation(): Location {
    val location = Location("MyLocationProvider")
    location.apply {
        latitude = mmLocation.latitude + Random.nextFloat()
        longitude = mmLocation.longitude + Random.nextFloat()
    }
    return location
}