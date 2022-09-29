package com.example.uberbookingexperience.ui.screens.cabswithmap

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.model.UberCabInfo

class UberMapScreenVM : ViewModel() {


    fun selectItem(selectedUberCabIndex: Int) {
        cabListing.forEach {
            it.isChecked = false
        }
        cabListing[selectedUberCabIndex] = cabListing[selectedUberCabIndex].copy(isChecked = true)
        //selectedUberCab=cabListing[selectedUberCabIndex]
    }

    fun selectedItem(): UberCabInfo {
        return cabListing.first { it.isChecked }
    }

    var cabListing = mutableStateListOf(
        UberCabInfo(
            cabInfo = "Uber Pool", cabIcon =
            R.drawable.ub__mode_nav_carpool,
            cabPrice = 80.80f,
            isChecked = true,
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
            cabInfo = "Uber Bike Scooter", cabIcon =
            R.drawable.ub__mode_nav_bike_scooter,
            cabPrice = 120.80f,
            carTime = "11:19 AM"
        ),
        UberCabInfo(
            cabInfo = "Uber Pool", cabIcon =
            R.drawable.ub__mode_nav_carpool,
            cabPrice = 80.80f,
            cabPriceAlter = 100.20f,
            carTime = "3:09 PM"
        ),
        UberCabInfo(
            cabInfo = "Uber Bike", cabIcon =
            R.drawable.ub__mode_nav_bike,
            cabPrice = 120.80f,
            carTime = "11:19 AM"
        ), UberCabInfo(
            cabInfo = "Uber Car",
            cabIcon =
            R.drawable.ub__mode_nav_ride,
            cabPrice = 80.80f,
            cabPriceAlter = 110.20f,
            carTime = "1:19 PM",
        )
    )
    //var selectedUberCab:UberCabInfo=cabListing.first()
}
