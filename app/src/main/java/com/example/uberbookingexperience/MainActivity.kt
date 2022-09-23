package com.example.uberbookingexperience

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uberbookingexperience.ui.screens.Screens
import com.example.uberbookingexperience.ui.screens.dashboard.DashboardScreen
import com.example.uberbookingexperience.ui.screens.paymentOptions.PaymentOptionsScreen
import com.example.uberbookingexperience.ui.screens.schedulePickup.SchedulePickupScreen
import com.example.uberbookingexperience.ui.screens.splash.SplashScreen
import com.example.uberbookingexperience.ui.screens.whereTo.WhereToScreen
import com.example.uberbookingexperience.ui.theme.UberBookingExperienceTheme
import com.example.uberbookingexperience.ui.util.changeSystemBarsColor
import com.example.uberbookingexperience.ui.util.clearAndNavigate
import com.example.uberbookingexperience.ui.util.getSystemAnimationDuration
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setupSystemSplashScreen()

        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            UberBookingExperienceTheme {
                val systemUiController = rememberSystemUiController()

                LaunchedEffect(Unit) {
                    systemUiController.changeSystemBarsColor()
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screens.SplashScreen()
                    ) {
                        composable(Screens.SplashScreen()) {
                            SplashScreen(onAnimationFinish = {
                                systemUiController.changeSystemBarsColor()

                                navController.clearAndNavigate(
                                    clearDestination = Screens.SplashScreen(),
                                    navigateToDestination = Screens.DashboardScreen()
                                )
                            })
                        }

                        composable(Screens.DashboardScreen()) {
                            DashboardScreen(onNextClicked = {
                                navController.clearAndNavigate(
                                    clearDestination = Screens.DashboardScreen(),
                                    navigateToDestination = Screens.WhereToScreen()
                                )
                            })
                        }

                        composable(Screens.WhereToScreen()) {
                            WhereToScreen()
                        }

                        composable(Screens.PaymentOptionsScreen()) {
                            PaymentOptionsScreen { navController.navigateUp() }
                        }

                        composable(Screens.SchedulePickupScreen()) {
                            SchedulePickupScreen(
                                onNavigationIconClick = { navController.navigateUp() },
                                onScheduleButtonClick = { _, _ -> }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun setupSystemSplashScreen() {
        installSplashScreen().setOnExitAnimationListener { splashScreenView ->
            val fadeAnim = ObjectAnimator.ofFloat(
                splashScreenView.view,
                View.ALPHA,
                1f,
                0f
            )

            with(fadeAnim) {
                interpolator = LinearInterpolator()
                duration = getSystemAnimationDuration().toLong()
                doOnStart { changeSystemBarsColor() }
                doOnEnd { splashScreenView.remove() }
                start()
            }
        }
    }
}
