package com.example.uberbookingexperience.ui.screens.paymentOptions.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.ui.screens.paymentOptions.PaymentOption
import com.example.uberbookingexperience.ui.util.clickableWithRipple
import com.example.uberbookingexperience.ui.util.rememberIsMobileDevice
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun PaymentOptionsCategory(
    modifier: Modifier = Modifier,
    title: String,
    useBigTitle: Boolean = false,
    paymentOptions: List<PaymentOption>,
    footer: String? = null,
    useSwitchForSelected: Boolean = false
) {
    val paddingModifier = remember { Modifier.padding(horizontal = 16.dp) }
    Column(modifier = modifier) {
        Text(
            modifier = paddingModifier.padding(vertical = 12.dp),
            text = title,
            style = if (!useBigTitle) {
                MaterialTheme.typography.headlineSmall.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.4f
                    )
                )
            } else {
                MaterialTheme.typography.headlineMedium
            }
        )
        if (rememberIsMobileDevice()) {
            Column {
                paymentOptions.forEach { paymentOption ->
                    MobilePaymentOptionItem(
                        paymentOption = paymentOption,
                        useSwitchForSelected = useSwitchForSelected
                    )
                }
            }
        } else {
            FlowRow(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                paymentOptions.forEach { paymentOption ->
                    DesktopPaymentOptionItem(paymentOption = paymentOption)
                }
            }
        }
        footer?.let { nnFooter ->
            Text(
                modifier = paddingModifier.padding(vertical = 16.dp).clickableWithRipple {},
                text = nnFooter,
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = MaterialTheme.colorScheme.tertiary
                )
            )
        }
    }
}
