package com.example.uberbookingexperience.ui.screen

import androidx.compose.runtime.Composable
import com.example.uberbookingexperience.components.UberGoogleMap

@Composable
fun UberMapScreen(){
    //TODO: update viewmodel access code
    UberGoogleMap(viewModel  = UberMapScreenVM() )
}