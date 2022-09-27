package com.example.uberbookingexperience.ui.util

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng


val zoom = 12f
val zoomAnimationDuration = 1_000
val mmLocation = LatLng(23.117983, 72.537436)


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
val defaultCameraPosition = CameraPosition.fromLatLngZoom(startLocation, zoom)

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
