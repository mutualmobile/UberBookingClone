package com.example.uberbookingexperience.ui.screens.addPaymentMethod

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.ui.common.UberTopBar
import com.example.uberbookingexperience.ui.screens.paymentOptions.PaymentOption
import com.example.uberbookingexperience.ui.screens.paymentOptions.components.PaymentOptionsCategory
import com.example.uberbookingexperience.ui.theme.UberBookingExperienceTheme
import com.example.uberbookingexperience.ui.theme.spacing

@Composable
fun AddPaymentMethodScreen(
    paymentOptions: List<PaymentOption> = listOf(
        PaymentOption(
            icon = R.drawable.ic_google_pay_logo,
            name = "Google Pay"
        ),
        PaymentOption(
            icon = R.drawable.ic_amazon_pay_logo,
            name = "Amazon Pay"
        ),
        PaymentOption(
            icon = R.drawable.ic_upi_logo,
            name = "UPI"
        ),
        PaymentOption(
            icon = R.drawable.ic_generic_card,
            name = "Credit or Debit Card"
        ),
        PaymentOption(
            icon = R.drawable.ic_uber_gift_card,
            name = "Gift Card"
        )
    ),
    onNavigationIconClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.systemBarsPadding())
        UberTopBar(
            title = "Add Payment",
            icon = Icons.Default.ArrowBack,
            iconOnClick = onNavigationIconClick
        )
        PaymentOptionsCategory(
            modifier = Modifier.padding(top = MaterialTheme.spacing.medium),
            paymentOptions = paymentOptions,
            desktopHorizontalItemSpacing = MaterialTheme.spacing.small,
            desktopVerticalItemSpacing = MaterialTheme.spacing.small
        )
    }
}

@Preview(showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Preview(showSystemUi = true, device = "spec:width=673.5dp,height=841dp,dpi=480")
@Preview(showSystemUi = true, device = "spec:width=1280dp,height=800dp,dpi=480")
@Preview(showSystemUi = true, device = "spec:width=1920dp,height=1080dp,dpi=480")
@Composable
private fun AddPaymentMethodScreenPreview() {
    UberBookingExperienceTheme {
        AddPaymentMethodScreen() {}
    }
}
