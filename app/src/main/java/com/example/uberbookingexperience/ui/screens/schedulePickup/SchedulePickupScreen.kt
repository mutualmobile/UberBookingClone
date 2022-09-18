package com.example.uberbookingexperience.ui.screens.schedulePickup

import androidx.annotation.DrawableRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.ui.common.UberDivider
import com.example.uberbookingexperience.ui.common.UberTopBar
import com.example.uberbookingexperience.ui.theme.UberBookingExperienceTheme
import com.example.uberbookingexperience.ui.util.UberIconSize
import com.example.uberbookingexperience.ui.util.clickableWithRipple
import com.example.uberbookingexperience.ui.util.rememberIsMobileDevice

@Composable
fun SchedulePickupScreen(
    onNavigationIconClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UberTopBar(
            modifier = Modifier.align(Alignment.Start),
            title = "When do you want to be picked up?",
            titleOffsetFromIcon = 8.dp,
            iconOnClick = onNavigationIconClick
        )
        var currentDate by remember { mutableStateOf("Fri, 9 Sep") }
        var currentTime by remember { mutableStateOf("18:00") }
        val dateTimePicker: @Composable (Modifier) -> Unit = remember(currentDate, currentTime) {
            movableContentOf { modifier ->
                Column(
                    modifier = modifier.padding(horizontal = 32.dp).padding(top = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SchedulePickupText(text = currentDate) {}
                    UberDivider()
                    SchedulePickupText(text = currentTime) {}
                }
            }
        }
        val prebookingBenefits = remember {
            mutableStateListOf(
                PrebookingBenefitItem(
                    icon = R.drawable.ic_calendar,
                    text = "Choose your exact pick-up time up to 30 days in advance"
                ),
                PrebookingBenefitItem(
                    icon = R.drawable.ic_hourglass,
                    text = "Extra wait time included to meet your trip"
                ),
                PrebookingBenefitItem(
                    icon = R.drawable.ic_credit_card,
                    text = "Cancel at no charge up to 60 minutes in advance"
                )
            )
        }
        val prebookingBenefitItems: @Composable (Modifier) -> Unit = remember(prebookingBenefits) {
            movableContentOf { modifier ->
                Column(
                    modifier = modifier.fillMaxWidth().padding(16.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 16.dp),
                        text = "Added flexibility if you book 2 hours ahead",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    prebookingBenefits.forEachIndexed { index, prebookingBenefitItem ->
                        PrebookingBenefitsListItem(
                            prebookingBenefitItem = prebookingBenefitItem,
                            showDivider = index != prebookingBenefits.lastIndex
                        )
                    }
                    Text(
                        modifier = Modifier.padding(24.dp).clickableWithRipple {},
                        text = "See terms",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }
        Crossfade(targetState = rememberIsMobileDevice()) { isMobileDevice ->
            if (isMobileDevice) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    dateTimePicker(Modifier)
                    prebookingBenefitItems(Modifier)
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    dateTimePicker(Modifier.widthIn(max = 400.dp))
                    prebookingBenefitItems(Modifier.widthIn(max = 400.dp))
                }
            }
        }
    }
}

@Composable
private fun SchedulePickupText(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Text(
        modifier = modifier.clickableWithRipple(onClick = onClick).padding(16.dp).fillMaxWidth(),
        text = text,
        textAlign = TextAlign.Center
    )
}

@Immutable
private data class PrebookingBenefitItem(
    @DrawableRes val icon: Int,
    val text: String
)

@Composable
private fun PrebookingBenefitsListItem(
    prebookingBenefitItem: PrebookingBenefitItem,
    showDivider: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(12.dp).size(UberIconSize.NormalIcon),
            painter = painterResource(id = prebookingBenefitItem.icon),
            contentDescription = null
        )
        Text(
            text = prebookingBenefitItem.text,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Light,
            letterSpacing = 0.5.sp
        )
    }
    if (showDivider) {
        UberDivider(Modifier.padding(start = 48.dp))
    }
}

@Preview(showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Preview(showSystemUi = true, device = "spec:width=673.5dp,height=841dp,dpi=480")
@Preview(showSystemUi = true, device = "spec:width=1280dp,height=800dp,dpi=480")
@Preview(showSystemUi = true, device = "spec:width=1920dp,height=1080dp,dpi=480")
@Composable
private fun SchedulePickupScreenPreview() {
    UberBookingExperienceTheme {
        SchedulePickupScreen {}
    }
}
