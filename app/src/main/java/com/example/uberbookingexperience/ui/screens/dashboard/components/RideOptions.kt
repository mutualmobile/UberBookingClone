package com.example.uberbookingexperience.ui.screens.dashboard.components

import com.example.uberbookingexperience.R

data class RideOptions(
    val title: String,
    val image: Int
)

fun getRideOptions() : List<RideOptions> {
    val options = arrayListOf<RideOptions>()
    options.add(RideOptions("Bike", R.drawable.ub__mode_nav_bike_scooter))
    options.add(RideOptions("E Bike", R.drawable.ub__mode_nav_bike))
    options.add(RideOptions("Uber Go", R.drawable.ub__mode_nav_ride))
    options.add(RideOptions("Carpool", R.drawable.ub__mode_nav_carpool))
    options.add(RideOptions("Uber X", R.drawable.ub__mode_nav_ride))
    options.add(RideOptions("Scooter", R.drawable.ub__mode_nav_bike))
    options.add(RideOptions("Rental", R.drawable.ub__mode_nav_ride))
    return options
}
