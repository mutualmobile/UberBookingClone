package com.example.uberbookingexperience.ui.screens.schedulePickup

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.ui.common.UberButton
import com.example.uberbookingexperience.ui.common.UberDivider
import com.example.uberbookingexperience.ui.common.UberTopBar
import com.example.uberbookingexperience.ui.screens.schedulePickup.components.PrebookingBenefitItem
import com.example.uberbookingexperience.ui.screens.schedulePickup.components.PrebookingBenefitsList
import com.example.uberbookingexperience.ui.screens.schedulePickup.components.SchedulePickupText
import com.example.uberbookingexperience.ui.screens.schedulePickup.components.UberDateTimePicker
import com.example.uberbookingexperience.ui.theme.UberBookingExperienceTheme
import com.example.uberbookingexperience.ui.theme.spacing
import com.example.uberbookingexperience.ui.util.limitWidth
import com.example.uberbookingexperience.ui.util.rememberIsMobileDevice
import com.example.uberbookingexperience.ui.util.uberFormattedDate
import com.example.uberbookingexperience.ui.util.uberFormattedTime
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun SchedulePickupScreen(
    onNavigationIconClick: () -> Unit,
    onScheduleButtonClick: (
        selectedDate: LocalDate,
        selectedTime: LocalTime
    ) -> Unit
) {
    val isMobile = rememberIsMobileDevice()

    val datePickerDialogState = rememberMaterialDialogState()
    val timePickerDialogState = rememberMaterialDialogState()

    var currentDate by rememberSaveable { mutableStateOf(LocalDate.now()) }
    var currentTime by rememberSaveable { mutableStateOf(LocalTime.now()) }

    Column(
        modifier = Modifier.systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UberTopBar(
            modifier = Modifier.align(Alignment.Start),
            title = "When do you want to be picked up?",
            titleOffsetFromIcon = MaterialTheme.spacing.small,
            iconOnClick = onNavigationIconClick
        )

        val dateTimePickerSection: @Composable (Modifier) -> Unit =
            remember(currentDate, currentTime) {
                movableContentOf { modifier ->
                    Column(
                        modifier = modifier.padding(horizontal = MaterialTheme.spacing.extraLarge)
                            .padding(top = MaterialTheme.spacing.extraLarge),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        SchedulePickupText(text = currentDate.uberFormattedDate()) {
                            datePickerDialogState.show()
                        }
                        UberDivider()
                        SchedulePickupText(text = currentTime.uberFormattedTime()) {
                            timePickerDialogState.show()
                        }
                    }
                }
            }

        val prebookingBenefitsSection = remember {
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

        val prebookingBenefitItems: @Composable (Modifier) -> Unit =
            remember(prebookingBenefitsSection) {
                movableContentOf { modifier ->
                    PrebookingBenefitsList(
                        modifier = modifier,
                        prebookingBenefits = prebookingBenefitsSection
                    )
                }
            }

        Crossfade(targetState = isMobile) { isMobileDevice ->
            if (isMobileDevice) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    dateTimePickerSection(Modifier)
                    prebookingBenefitItems(Modifier)
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    dateTimePickerSection(Modifier.limitWidth())
                    prebookingBenefitItems(Modifier.limitWidth())
                }
            }
        }

        if (isMobile) {
            Spacer(modifier = Modifier.weight(1f))
        }

        UberButton(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .limitWidth(),
            text = "Set pickup time",
            onClick = { onScheduleButtonClick(currentDate, currentTime) }
        )
    }

    UberDateTimePicker(dialogState = datePickerDialogState) {
        datepicker(
            colors = DatePickerDefaults.colors(
                headerBackgroundColor = MaterialTheme.colorScheme.primary,
                headerTextColor = MaterialTheme.colorScheme.onPrimary,
                calendarHeaderTextColor = MaterialTheme.colorScheme.primary,
                dateActiveBackgroundColor = MaterialTheme.colorScheme.primary,
                dateInactiveBackgroundColor = MaterialTheme.colorScheme.onPrimary,
                dateActiveTextColor = MaterialTheme.colorScheme.onPrimary,
                dateInactiveTextColor = MaterialTheme.colorScheme.primary
            )
        ) { currentDate = it }
    }

    UberDateTimePicker(dialogState = timePickerDialogState) {
        timepicker(
            colors = TimePickerDefaults.colors(
                activeBackgroundColor = MaterialTheme.colorScheme.primary,
                inactiveBackgroundColor = MaterialTheme.colorScheme.onPrimary,
                activeTextColor = MaterialTheme.colorScheme.onPrimary,
                inactiveTextColor = MaterialTheme.colorScheme.primary,
                inactivePeriodBackground = MaterialTheme.colorScheme.onPrimary,
                selectorColor = MaterialTheme.colorScheme.primary,
                selectorTextColor = MaterialTheme.colorScheme.onPrimary,
                headerTextColor = MaterialTheme.colorScheme.primary,
                borderColor = MaterialTheme.colorScheme.onPrimary
            )
        ) { currentTime = it }
    }
}

@Preview(showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Preview(showSystemUi = true, device = "spec:width=673.5dp,height=841dp,dpi=480")
@Preview(showSystemUi = true, device = "spec:width=1280dp,height=800dp,dpi=480")
@Preview(showSystemUi = true, device = "spec:width=1920dp,height=1080dp,dpi=480")
@Composable
private fun SchedulePickupScreenPreview() {
    UberBookingExperienceTheme {
        SchedulePickupScreen(
            onNavigationIconClick = {},
            onScheduleButtonClick = { _, _ -> }
        )
    }
}
