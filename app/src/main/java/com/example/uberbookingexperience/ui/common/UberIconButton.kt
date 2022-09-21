package com.example.uberbookingexperience.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.uberbookingexperience.ui.theme.colorUberGrayBg
import com.example.uberbookingexperience.ui.util.UberIconSize.MediumIcon

/**
 * An Uber-styled icon button that helps its users initiate actions.
 * @param modifier the [Modifier] to be applied to this button
 * @param iconId paint resource id of icon
 * @param backgroundColor icon button background color
 * @param contentDescription icon content Description
 * @param onClick called when this button is clicked
 * */
@Composable
fun UberIconButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    backgroundColor: Color = colorUberGrayBg,
    contentDescription: String = "",
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier
            .background(backgroundColor),
        onClick = onClick
    ) {
        Icon(
            painterResource(id = iconId),
            contentDescription = contentDescription,
            modifier = Modifier.size(MediumIcon)
        )
    }
}

/*
* Box(contentAlignment = Alignment.Center,
                    modifier = Modifier.background(
                    colorUberGrayBg).padding(8.dp)){
                    Icon(painterResource(id = R.drawable.schedule_button_icon),
                        contentDescription = "",modifier=Modifier.size(SizeButton))
                }
* */
