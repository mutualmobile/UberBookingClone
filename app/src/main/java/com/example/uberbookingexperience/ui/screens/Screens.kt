package com.example.uberbookingexperience.ui.screens

enum class Screens(private val route: String) {
    SplashScreen("splashScreen"),
    DashboardScreen("dashboardScreen"),
    WhereToScreen("whereToScreen"),
    PaymentOptionsScreen("paymentOptionsScreen"),
    SchedulePickupScreen("schedulePickupScreen"),
    AddPaymentMethodScreen("addPaymentMethodScreen"),
    MapScreen("MapScreen");
    operator fun invoke() = route
}
