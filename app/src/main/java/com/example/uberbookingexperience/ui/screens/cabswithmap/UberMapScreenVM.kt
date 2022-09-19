package com.example.uberbookingexperience.ui.screens.cabswithmap

import android.location.Location
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.model.UberCabInfo
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import kotlin.random.Random
import kotlinx.coroutines.flow.MutableStateFlow
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

    var selectedUberCab:UberCabInfo?=null
    var isItemSelected= MutableStateFlow(false)

    fun selectItem(selectedUberCabIndex: Int) {
        cabListing.forEach {
            it.isChecked = false
        }
        cabListing[selectedUberCabIndex] = cabListing[selectedUberCabIndex].copy(isChecked = true)
        selectedUberCab=cabListing[selectedUberCabIndex]
    }

    var cabListing = mutableStateListOf(
        UberCabInfo(
            cabInfo = "Uber Pool", cabIcon =
            R.drawable.ub__mode_nav_carpool,
            cabPrice = 80.80f,
            cabPriceAlter = 100.20f,
            carTime = "3:09 PM"
        ),
        UberCabInfo(
            cabInfo = "Uber Car",
            cabIcon =
             R.drawable.ub__mode_nav_ride,
            cabPrice = 120.80f,
            cabPriceAlter = 200.20f,
            carTime = "1:19 PM",
        ),
        UberCabInfo(
            cabInfo = "Uber Bike", cabIcon =
             R.drawable.ub__mode_nav_bike_scooter,
            cabPrice = 120.80f,
            carTime = "11:19 AM"
        )
    )
}
