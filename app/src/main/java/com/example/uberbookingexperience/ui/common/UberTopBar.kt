package com.example.uberbookingexperience.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.ui.theme.UberBookingExperienceTheme
import com.example.uberbookingexperience.ui.theme.spacing
import com.example.uberbookingexperience.ui.util.UberIconSize

/**
 * A commonly used composable in the Uber App that allows its user to quickly configure a generic
 * TopBar that can look consistent between various app screens.
 * @param modifier Used to modify the overall look and behavior of the TopBar
 * @param icon Defines the icon to be displayed on the topLeft of the available view
 * @param iconOnClick Executes the given lambda function onClick of the [icon]
 * @param title Sets the text for the title composable used in the TopBar
 * @param titleOffsetFromIcon Sets how far is the [title] placed from the [icon] on the x-axis
 * @sample UberTopBarSample
 * */
@Composable
fun UberTopBar(
    modifier: Modifier = Modifier,
    title: String,
    titleOffsetFromIcon: Dp = 0.dp,
    icon: ImageVector = Icons.Default.Close,
    iconOnClick: () -> Unit
) {
    Column(modifier = modifier) {
        IconButton(onClick = iconOnClick) {
            Icon(
                modifier = Modifier.size(UberIconSize.Navigation),
                imageVector = icon,
                contentDescription = null
            )
        }
        Text(
            modifier = Modifier
                .padding(start = 12.dp)
                .padding(start = titleOffsetFromIcon),
            text = title,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Composable
private fun UberTopBarSample() {
    val goToPreviousScreen = {}
    UberTopBar(
        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
        icon = Icons.Default.Close,
        iconOnClick = { goToPreviousScreen() },
        title = "Welcome to Uber",
        titleOffsetFromIcon = MaterialTheme.spacing.small
    )
}

@Preview(showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Preview(showSystemUi = true, device = "spec:width=673.5dp,height=841dp,dpi=480")
@Preview(showSystemUi = true, device = "spec:width=1280dp,height=800dp,dpi=480")
@Preview(showSystemUi = true, device = "spec:width=1920dp,height=1080dp,dpi=480")
@Composable
private fun UberTopBarPreview() {
    UberBookingExperienceTheme {
        UberTopBar(
            title = "When do you want to be picked up?",
            titleOffsetFromIcon = MaterialTheme.spacing.small
        ) {}
    }
}
