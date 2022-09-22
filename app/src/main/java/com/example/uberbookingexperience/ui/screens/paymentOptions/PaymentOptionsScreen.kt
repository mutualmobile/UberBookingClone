package com.example.uberbookingexperience.ui.screens.paymentOptions

import androidx.annotation.DrawableRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.ui.common.UberTopBar
import com.example.uberbookingexperience.ui.screens.paymentOptions.components.BusinessPaymentOptionScreen
import com.example.uberbookingexperience.ui.screens.paymentOptions.components.PayeeType
import com.example.uberbookingexperience.ui.screens.paymentOptions.components.PaymentOptionsCategory
import com.example.uberbookingexperience.ui.theme.UberBookingExperienceTheme

@Immutable
data class PaymentOption(
    @DrawableRes val icon: Int,
    val name: String,
    val value: String? = null,
    val selected: Boolean = false,
    val onClick: () -> Unit = {}
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentOptionsScreen(onNavigationBack: () -> Unit) {
    Scaffold { bodyPadding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(bodyPadding)) {
                UberTopBar(
                    title = "Payment Options",
                    iconOnClick = onNavigationBack
                )

                var selectedPayeeType by rememberSaveable { mutableStateOf("Personal") }
                PayeeType(selectedItemTitle = selectedPayeeType) { selectedItem ->
                    selectedPayeeType = selectedItem
                }

                Crossfade(targetState = selectedPayeeType) { payeeType ->
                    when (payeeType) {
                        "Personal" -> {
                            var isUberCashSelected by rememberSaveable { mutableStateOf(true) }
                            var currentPaymentOption by rememberSaveable { mutableStateOf("") }

                            LazyColumn(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                item {
                                    PaymentOptionsCategory(
                                        title = "Uber Cash",
                                        paymentOptions = listOf(
                                            PaymentOption(
                                                R.drawable.ic_uber_logo,
                                                name = "Uber Cash",
                                                value = "$0.00",
                                                selected = isUberCashSelected,
                                                onClick = {
                                                    isUberCashSelected = !isUberCashSelected
                                                }
                                            )
                                        ),
                                        mobileUseSwitchForSelected = true
                                    )
                                }
                                item {
                                    PaymentOptionsCategory(
                                        title = "Payment Method",
                                        paymentOptions = listOf(
                                            PaymentOption(
                                                R.drawable.ic_paytm_logo,
                                                name = "Paytm",
                                                selected = currentPaymentOption == "Paytm",
                                                onClick = {
                                                    currentPaymentOption = "Paytm"
                                                }
                                            ),
                                            PaymentOption(
                                                R.drawable.ic_google_pay_logo,
                                                name = "www.mutualmobile.com@oksbi",
                                                selected = currentPaymentOption == "www.mutualmobile.com@oksbi",
                                                onClick = {
                                                    currentPaymentOption =
                                                        "www.mutualmobile.com@oksbi"
                                                }
                                            ),
                                            PaymentOption(
                                                R.drawable.ic_cash_logo,
                                                name = "Cash",
                                                selected = currentPaymentOption == "Cash",
                                                onClick = {
                                                    currentPaymentOption = "Cash"
                                                }
                                            )
                                        ),
                                        footer = "Add Payment Method"
                                    )
                                }
                                item {
                                    PaymentOptionsCategory(
                                        modifier = Modifier.padding(top = 48.dp),
                                        title = "Vouchers",
                                        useBigTitle = true,
                                        paymentOptions = emptyList(),
                                        footer = "Add voucher code"
                                    )
                                }
                            }
                        }
                        "Business" -> {
                            BusinessPaymentOptionScreen()
                        }
                        else -> Unit
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Preview(showSystemUi = true, device = "spec:width=673.5dp,height=841dp,dpi=480")
@Preview(showSystemUi = true, device = "spec:width=1280dp,height=800dp,dpi=480")
@Preview(showSystemUi = true, device = "spec:width=1920dp,height=1080dp,dpi=480")
@Composable
private fun PaymentOptionsScreenPreview() {
    UberBookingExperienceTheme {
        PaymentOptionsScreen {}
    }
}
