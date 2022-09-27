package com.example.uberbookingexperience.ui.screens.paymentOptions.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.ui.screens.paymentOptions.PaymentOption
import com.example.uberbookingexperience.ui.theme.UberBookingExperienceTheme
import com.example.uberbookingexperience.ui.theme.spacing
import com.example.uberbookingexperience.ui.util.clickableWithRipple
import com.example.uberbookingexperience.ui.util.rememberIsMobileDevice
import com.google.accompanist.flowlayout.FlowRow

/**
 * Composable for displaying a payment options category. A payment options category consists of
 * a title (its size depends on [useBigTitle]), and a list of [PaymentOption] items. This composable
 * deals with both mobile and non-mobile screen sizes to change the look and feel of the list items
 * accordingly.
 *
 * @param modifier [Modifier] to be applied on this composable
 * @param title text to be displayed on top of the item list (use null if you want to remove it)
 * @param footer text to be displayed on bottom of the item list (use null if you want to remove it)
 * @param useBigTitle whether to use a large [title] or small
 * @param paymentOptions list of [PaymentOption] that denotes the list items in this composable
 * @param mobileUseSwitchForSelected whether to use a [Switch] (in mobile-mode only) to denote
 * whether the item is selected or not. Using true uses a [Switch] and using false uses a tick icon
 * @param desktopHorizontalItemSpacing how much horizontal spacing to give between [paymentOptions]
 * in the desktop mode
 * @param desktopVerticalItemSpacing how much vertical spacing to give between [paymentOptions]
 * in the desktop mode
 * */
@Composable
fun PaymentOptionsCategory(
    modifier: Modifier = Modifier,
    title: String? = null,
    footer: String? = null,
    useBigTitle: Boolean = false,
    paymentOptions: List<PaymentOption>,
    mobileUseSwitchForSelected: Boolean = false,
    desktopHorizontalItemSpacing: Dp = 0.dp,
    desktopVerticalItemSpacing: Dp = 0.dp
) {
    val commonPadding = MaterialTheme.spacing.none
    val paddingModifier = remember { Modifier.padding(horizontal = commonPadding) }
    Column(modifier = modifier) {
        title?.let { nnTitle ->
            Text(
                modifier = paddingModifier.padding(vertical = 12.dp),
                text = nnTitle,
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
        }
        if (rememberIsMobileDevice()) {
            Column {
                paymentOptions.forEach { paymentOption ->
                    MobilePaymentOptionItem(
                        paymentOption = paymentOption,
                        useSwitchForSelected = mobileUseSwitchForSelected
                    )
                }
            }
        } else {
            FlowRow(
                modifier = Modifier.padding(start = MaterialTheme.spacing.medium),
                mainAxisSpacing = desktopHorizontalItemSpacing,
                crossAxisSpacing = desktopVerticalItemSpacing
            ) {
                paymentOptions.forEach { paymentOption ->
                    DesktopPaymentOptionItem(paymentOption = paymentOption)
                }
            }
        }
        footer?.let { nnFooter ->
            Text(
                modifier = paddingModifier
                    .padding(vertical = MaterialTheme.spacing.medium)
                    .clickableWithRipple {},
                text = nnFooter,
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = MaterialTheme.colorScheme.tertiary
                )
            )
        }
    }
}

@Preview(showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Preview(showSystemUi = true, device = "spec:width=673.5dp,height=841dp,dpi=480")
@Preview(showSystemUi = true, device = "spec:width=1280dp,height=800dp,dpi=480")
@Preview(showSystemUi = true, device = "spec:width=1920dp,height=1080dp,dpi=480")
@Composable
private fun PaymentOptionsCategoryPreview() {
    UberBookingExperienceTheme {
        PaymentOptionsCategory(
            paymentOptions = listOf(
                PaymentOption(
                    icon = R.drawable.ic_cash_logo,
                    name = "Cash",
                    selected = false,
                    onClick = {}
                )
            )
        )
    }
}
