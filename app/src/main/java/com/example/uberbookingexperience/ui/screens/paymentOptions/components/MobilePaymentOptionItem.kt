package com.example.uberbookingexperience.ui.screens.paymentOptions.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.uberbookingexperience.ui.screens.paymentOptions.PaymentOption
import com.example.uberbookingexperience.ui.theme.spacing
import com.example.uberbookingexperience.ui.util.UberIconSize
import com.example.uberbookingexperience.ui.util.clickableWithRipple

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MobilePaymentOptionItem(
    paymentOption: PaymentOption,
    useSwitchForSelected: Boolean
) {
    // Extracted it out since it didn't need to be recomposed when either container or content
    // color changed.
    val optionIcon = remember(paymentOption.icon) {
        movableContentOf {
            Image(
                modifier = Modifier.padding(vertical = MaterialTheme.spacing.small).size(UberIconSize.ListItem)
                    .aspectRatio(1.5f).clip(shape = RoundedCornerShape(20)),
                painter = painterResource(id = paymentOption.icon),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
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
        leadingContent = { optionIcon() },
        trailingContent = {
            if (useSwitchForSelected) {
                Switch(
                    checked = paymentOption.selected,
                    onCheckedChange = { paymentOption.onClick() }
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
