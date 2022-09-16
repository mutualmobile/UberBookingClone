package com.example.uberbookingexperience.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.ui.theme.colorBlack
import com.example.uberbookingexperience.ui.theme.colorWhite

@Composable
fun DefaultAppButton(@StringRes buttonText: Int,modifier: Modifier=Modifier, onButtonClick: () -> Unit) {
    FilledTonalButton(
        onClick = onButtonClick, modifier = modifier.fillMaxWidth(0.9f).heightIn(42.dp), colors = ButtonDefaults
            .buttonColors(
                containerColor = colorBlack,
                contentColor = colorWhite,
                disabledContainerColor = colorBlack.copy(alpha = 0.3f),
                disabledContentColor = colorWhite.copy(alpha = 0.5f)
            )
    ) {
        Text(text = stringResource(id = buttonText))
    }
}