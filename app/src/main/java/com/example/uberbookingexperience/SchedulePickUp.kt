package com.example.uberbookingexperience

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.ui.theme.colorBlack
import com.example.uberbookingexperience.ui.theme.colorWhite

@Composable
fun SchedulePickUp(
    viewModel: MainViewModel, modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)

        ) {
            Icon(Icons.Default.Close,
                contentDescription = stringResource(id = R.string.close),
                modifier = Modifier
                    .clickable {
                        //implement close icon click
                    })
            DefaultTextViewH3(
                labelText = R.string.when_do_you_want_pickup,
                modifier = Modifier.padding(12.dp)
            )
            Spacer(modifier = Modifier.padding(14.dp))
            DateTimeTextView("Fri, 9") {
                //on date textview click
            }
            Divider(thickness = 1.dp)
            DateTimeTextView("11:30 PM") {
                //on time textview click
            }
            DefaultTextViewH3(
                labelText = R.string.flexibility_info,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(vertical = 14.dp)
            )
            TextViewWithIcon(R.string.schedule_time_info_first, R.drawable.ic_date_range)
            TextViewWithIcon(R.string.schedule_time_info_two, R.drawable.ic_time_laps)
            TextViewWithIcon(
                R.string.schedule_time_info_three,
                R.drawable.ic_credit_card,
                isShowDivider = false
            )
            Spacer(modifier = Modifier.padding(10.dp))
            DefaultTextViewH3(
                labelText = R.string.see_terms,
                style = MaterialTheme.typography.titleSmall.copy(textDecoration = TextDecoration.Underline),
                modifier = Modifier.padding(12.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            DefaultAppButton(buttonText = R.string.set_pickup_time) {

            }
        }
    }
}

@Preview
@Composable
fun SchedulePickUpPreview() {
    SchedulePickUp(MainViewModel())
}

@Composable
fun DefaultAppButton(@StringRes buttonText: Int, onButtonClick: () -> Unit) {
    FilledTonalButton(
        onClick = onButtonClick, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults
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

@Composable
fun DefaultTextViewH3(
    @StringRes labelText: Int,
    style: TextStyle = MaterialTheme.typography.titleMedium,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = labelText), style = style,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun DateTimeTextView(
    labelText: String,
    modifier: Modifier = Modifier,
    onTextViewClick: () -> Unit
) {
    TextButton(onClick = onTextViewClick) {
        Text(
            text = labelText,
            style = MaterialTheme.typography.titleSmall.copy(
                textAlign = TextAlign.Center,
                color = Color.Black
            ),
            modifier = modifier
                .fillMaxWidth()
        )
    }

}

@Composable
fun TextViewWithIcon(
    @StringRes labelTextId: Int,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
    isShowDivider: Boolean = true
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = stringResource(id = R.string.close),
            modifier = Modifier
                .padding(4.dp)
        )
        Column {
            if (isShowDivider) {
                Text(
                    text = stringResource(id = labelTextId),
                    style = MaterialTheme.typography.labelMedium,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp, top = 14.dp, start = 10.dp, end = 14.dp)
                )
                Divider(
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 12.dp), thickness = 0.5.dp
                )
            } else {
                Text(
                    text = stringResource(id = labelTextId),
                    style = MaterialTheme.typography.labelMedium,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp, top = 14.dp, start = 10.dp, end = 14.dp)
                )
            }
        }
    }
}