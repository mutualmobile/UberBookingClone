package com.example.uberbookingexperience.ui.util

import androidx.navigation.NavController

fun NavController.clearAndNavigate(
    clearDestination: String,
    navigateToDestination: String,
) {
    navigate(navigateToDestination) {
        popUpTo(clearDestination) {
            inclusive = true
        }
    }
}