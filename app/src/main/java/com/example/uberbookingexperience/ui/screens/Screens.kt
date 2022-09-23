package com.example.uberbookingexperience.ui.screens

enum class Screens(private val route: String) {
    SplashScreen("splashScreen"),
    DashboardScreen("dashboardScreen"),
    WhereToScreen("whereToScreen");
    operator fun invoke() = route
}
