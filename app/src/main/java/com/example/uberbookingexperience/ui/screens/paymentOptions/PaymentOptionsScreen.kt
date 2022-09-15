package com.example.uberbookingexperience.ui.screens.paymentOptions

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.ui.theme.UberBookingExperienceTheme
import com.example.uberbookingexperience.ui.util.UberIconSize
import com.example.uberbookingexperience.ui.util.clickableWithRipple
import com.example.uberbookingexperience.ui.util.rememberActivity

@Immutable
data class PaymentOption(
    @DrawableRes val icon: Int,
    val name: String,
    val value: String? = null,
    val selected: Boolean,
    val onClick: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentOptionsScreen(deviceType: WindowWidthSizeClass) {
    Scaffold(topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    modifier = Modifier.size(UberIconSize.Navigation),
                    imageVector = Icons.Default.Close,
                    contentDescription = null
                )
            }
        }, title = {})
    }) { bodyPadding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(bodyPadding)) {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Payment options",
                    style = MaterialTheme.typography.headlineLarge
                )
                PayeeType()

                var isUberCashSelected by remember { mutableStateOf(true) }
                var currentPaymentOption by remember { mutableStateOf("") }

                if (deviceType == WindowWidthSizeClass.Compact) {
                    LazyColumn {
                        item {
                            MobilePaymentOptionsCategory(
                                title = "Uber Cash",
                                options = listOf(
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
                                useSwitchForSelected = true
                            )
                        }
                        item {
                            MobilePaymentOptionsCategory(
                                title = "Payment Method",
                                options = listOf(
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
                                            currentPaymentOption = "www.mutualmobile.com@oksbi"
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
                            MobilePaymentOptionsCategory(
                                modifier = Modifier.padding(top = 48.dp),
                                title = "Vouchers",
                                useBigTitle = true,
                                options = emptyList(),
                                footer = "Add voucher code"
                            )
                        }
                    }
                } else {
                    // TODO: Implement DesktopPaymentOptionsCategory
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MobilePaymentOptionsCategory(
    modifier: Modifier = Modifier,
    title: String,
    useBigTitle: Boolean = false,
    options: List<PaymentOption>,
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
        options.forEach { paymentOption ->
            ListItem(
                modifier = Modifier.clickableWithRipple(onClick = paymentOption.onClick),
                headlineText = {
                    Text(
                        text = paymentOption.name,
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                supportingText = paymentOption.value?.let { nnValue ->
                    {
                        Text(
                            text = nnValue,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onSurface.copy(
                                    alpha = 0.7f
                                )
                            )
                        )
                    }
                },
                leadingContent = {
                    Image(
                        modifier = Modifier.padding(vertical = 8.dp).size(UberIconSize.ListItem)
                            .aspectRatio(1.5f).clip(shape = RoundedCornerShape(20)),
                        painter = painterResource(id = paymentOption.icon),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                },
                trailingContent = {
                    if (useSwitchForSelected) {
                        Switch(
                            checked = paymentOption.selected,
                            onCheckedChange = {
                                paymentOption.onClick()
                            }
                        )
                    } else {
                        if (paymentOption.selected) {
                            Icon(
                                modifier = Modifier.size(UberIconSize.TrailingIconSize),
                                imageVector = Icons.Default.Done,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.tertiary
                            )
                        }
                    }
                }
            )
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

@Composable
private fun PayeeType() {
    var selectedItemTitle by remember { mutableStateOf("Personal") }

    LazyRow(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)
    ) {
        item {
            UberSelectableChip(
                modifier = Modifier.padding(start = 8.dp),
                title = "Personal",
                isSelected = selectedItemTitle == "Personal",
                icon = Icons.Filled.Person,
                onClick = {
                    selectedItemTitle = "Personal"
                }
            )
        }
        item {
            UberSelectableChip(
                modifier = Modifier.padding(start = 8.dp),
                title = "Business",
                isSelected = selectedItemTitle == "Business",
                icon = Icons.Filled.Work,
                onClick = {
                    selectedItemTitle = "Business"
                },
                selectedBackgroundColor = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UberSelectableChip(
    modifier: Modifier = Modifier,
    title: String,
    isSelected: Boolean,
    icon: ImageVector,
    chipPadding: PaddingValues = PaddingValues(start = 8.dp),
    contentPadding: PaddingValues = PaddingValues(10.dp),
    selectedBackgroundColor: Color = MaterialTheme.colorScheme.onSurface,
    elevation: Dp = 2.dp,
    onClick: () -> Unit
) {
    val layoutDirection = LocalLayoutDirection.current

    FilterChip(
        modifier = modifier.padding(chipPadding).graphicsLayer {
            clip = false
        },
        selected = isSelected, onClick = onClick, label = {
            Text(
                modifier = Modifier.padding(
                    top = contentPadding.calculateTopPadding(),
                    end = contentPadding.calculateEndPadding(layoutDirection),
                    bottom = contentPadding.calculateBottomPadding()
                ),
                text = title
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = MaterialTheme.colorScheme.background,
            selectedContainerColor = selectedBackgroundColor,
            labelColor = MaterialTheme.colorScheme.onSurface,
            selectedLabelColor = MaterialTheme.colorScheme.surface,
            iconColor = MaterialTheme.colorScheme.onSurface,
            selectedLeadingIconColor = MaterialTheme.colorScheme.surface
        ),
        leadingIcon = {
            Icon(
                modifier = Modifier.padding(
                    start = contentPadding.calculateStartPadding(layoutDirection),
                    top = contentPadding.calculateTopPadding(),
                    bottom = contentPadding.calculateBottomPadding()
                ),
                imageVector = icon,
                contentDescription = "$title payee type chip"
            )
        }, shape = CircleShape,
        border = FilterChipDefaults.filterChipBorder(
            borderColor = Color.Transparent
        ),
        elevation = FilterChipDefaults.filterChipElevation(
            defaultElevation = elevation
        )
    )
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Preview(showSystemUi = true, device = "spec:width=673.5dp,height=841dp,dpi=480")
@Preview(showSystemUi = true, device = "spec:width=1280dp,height=800dp,dpi=480")
@Preview(showSystemUi = true, device = "spec:width=1920dp,height=1080dp,dpi=480")
@Composable
private fun PaymentOptionsScreenPreview() {
    UberBookingExperienceTheme {
        val windowSize = calculateWindowSizeClass(activity = rememberActivity())
        PaymentOptionsScreen(deviceType = windowSize.widthSizeClass)
    }
}
