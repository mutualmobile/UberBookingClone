package com.example.uberbookingexperience.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.ui.theme.colorWhite
import com.example.uberbookingexperience.ui.util.UberIconSize.LargeButton

/**
 * An Uber-styled back button that helps its users for back navigation from screen.
 * @param modifier the [Modifier] to be applied to this button
 * @param iconId paint resource id of icon
 * @param backgroundColor icon button background color
 * @param contentDescription icon content Description
 * @param onClick called when this button is clicked
 * */
@Composable
fun UberBackButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    backgroundColor: Color = colorWhite,
    contentDescription: String = "",
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier
            .size(48.dp)
            .background(backgroundColor, CircleShape)
            .padding(2.dp),
        onClick = onClick
    ) {
        Icon(
            painterResource(id = iconId),
            contentDescription = contentDescription,
            modifier = Modifier.size(LargeButton)
        )
    }
}

@Composable
@Preview
fun UberBackButtonPreview() {
    UberBackButton(iconId = R.drawable.baseline_arrow_back_24) {

    }
}
