package com.example.uberbookingexperience.ui.screens.paymentOptions.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.ui.screens.paymentOptions.PaymentOption
import com.example.uberbookingexperience.ui.theme.UberBookingExperienceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DesktopPaymentOptionItem(paymentOption: PaymentOption) {
    val containerColor by animateColorAsState(
        targetValue = if (paymentOption.selected) MaterialTheme.colorScheme.onSurface
        else MaterialTheme.colorScheme.surface
    )

    val contentColor by animateColorAsState(
        targetValue = if (paymentOption.selected) MaterialTheme.colorScheme.surface
        else MaterialTheme.colorScheme.onSurface
    )

    Card(
        modifier = Modifier.size(128.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        onClick = paymentOption.onClick
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.6f),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                modifier = Modifier.fillMaxHeight(0.75f).aspectRatio(1f).clip(CardDefaults.shape),
                painter = painterResource(id = paymentOption.icon),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth().wrapContentSize().padding(horizontal = 8.dp)
                .padding(top = 8.dp),
            text = paymentOption.name,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
        paymentOption.value?.let { nnValue ->
            Text(
                modifier = Modifier.alpha(0.5f).fillMaxWidth().wrapContentSize()
                    .padding(horizontal = 8.dp),
                text = nnValue,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun DesktopPaymentOptionItemPreview() {
    UberBookingExperienceTheme {
        var isSelected by remember { mutableStateOf(false) }
        DesktopPaymentOptionItem(
            paymentOption = PaymentOption(
                icon = R.drawable.ic_uber_logo,
                name = "Uber Cash",
                value = "$0.00",
                selected = isSelected,
                onClick = {
                    isSelected = !isSelected
                }
            )
        )
    }
}
