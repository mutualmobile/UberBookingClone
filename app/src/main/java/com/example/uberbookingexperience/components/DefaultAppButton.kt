package com.example.uberbookingexperience.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.ui.theme.colorBlack
import com.example.uberbookingexperience.ui.theme.colorUberGrayBg
import com.example.uberbookingexperience.ui.theme.colorWhite
import com.example.uberbookingexperience.ui.util.UberIconSize.SizeButton

@Composable
fun DefaultAppButton(@StringRes buttonText: Int,modifier: Modifier=Modifier, onButtonClick: () -> Unit) {
    /*FilledTonalButton(
        onClick = onButtonClick, modifier = modifier
            .fillMaxWidth(0.9f)
            .heightIn(42.dp), colors = ButtonDefaults
            .buttonColors(
                containerColor = colorBlack,
                contentColor = colorWhite,
                disabledContainerColor = colorBlack.copy(alpha = 0.3f),
                disabledContentColor = colorWhite.copy(alpha = 0.5f)
            )
    ) {
        Text(text = stringResource(id = buttonText))
    }*/
    Box(contentAlignment = Alignment.Center, modifier = modifier
        .fillMaxWidth(0.9f)
        .heightIn(SizeButton)
        .background(colorBlack)
        .clickable {
            onButtonClick()
        }){
        Text(text = stringResource(id = buttonText), color = colorWhite, style = MaterialTheme.typography.labelSmall)
    }
}
@Composable
fun DefaultAppIconButton(@DrawableRes buttonIcon: Int,modifier: Modifier=Modifier, onButtonClick: () -> Unit) {
    Box(contentAlignment = Alignment.Center, modifier = modifier
        .size(SizeButton)
        .background(colorUberGrayBg)
        .clickable {
            onButtonClick()
        }){
        Icon(painterResource(id = buttonIcon), contentDescription = "" ,)
    }
}
@Composable
@Preview
fun DefaultAppButtonPreview(){
    DefaultAppButton(R.string.choose_uber){}
}
@Composable
@Preview
fun DefaultAppIconButtonPreview(){
    DefaultAppIconButton(R.drawable.schedule_button_icon) {}
}