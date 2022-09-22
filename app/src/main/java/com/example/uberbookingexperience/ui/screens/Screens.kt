package com.example.uberbookingexperience.ui.screens

enum class Screens(private val route: String) {
    SplashScreen("splashScreen"),
    DashboardScreen("dashboardScreen"),
    PaymentOptionsScreen("paymentOptionsScreen"),
    SchedulePickupScreen("schedulePickupScreen"),
    AddPaymentMethodScreen("addPaymentMethodScreen"),
    MapScreen("MapScreen"),
    ConfirmPickUpLocation("ConfirmPickUpLocation");

    operator fun invoke() = route
}
