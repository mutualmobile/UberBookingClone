package com.example.uberbookingexperience.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.R

@Composable
fun UberMapInfoWindowTextView(
    labelTextId: String,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(
            text = labelTextId,
            style = MaterialTheme.typography.labelMedium,
            modifier = modifier
                .wrapContentWidth()
        )
        Icon(
            painter = painterResource(id = icon),
            contentDescription = stringResource(id = R.string.next),
            modifier = Modifier
                .padding(4.dp)
        )
    }
}