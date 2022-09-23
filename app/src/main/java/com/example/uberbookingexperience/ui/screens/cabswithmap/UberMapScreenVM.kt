package com.example.uberbookingexperience.ui.screens.cabswithmap

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.model.UberCabInfo
import kotlinx.coroutines.flow.MutableStateFlow

class UberMapScreenVM : ViewModel() {

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
