package com.example.uberbookingexperience.ui.screens.whereTo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.ui.screens.whereTo.UberTextField
import com.example.uberbookingexperience.ui.theme.Typography
import com.example.uberbookingexperience.ui.theme.colorGrayLighter

@Composable fun TopSection(onBackPressed: () -> Unit) {
    Surface(modifier = Modifier.shadow(elevation = 8.dp)) {
        Column {
            TopBar(backButtonClicked = onBackPressed)
            SearchFieldCard()
        }
    }
}

@Composable fun TopBar(backButtonClicked: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)
    ) {
        Box(modifier = Modifier.padding(top = 24.dp)) {
            BackButton(backButtonClicked)
        }
        Box(
            modifier = Modifier
                .weight(0.9f),
            contentAlignment = Alignment.Center
        ) {
            SwitchRider {}
        }
        Spacer(modifier = Modifier.weight(0.1f))
    }
}

@Composable fun SearchFieldCard() {
    var isTfFocused by rememberSaveable { mutableStateOf(false) }
    Row(modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)) {
        TimeLineWidget(isTfFocused = isTfFocused)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            SearchFields {
                isTfFocused = !isTfFocused
            }
        }
        AddDestinationButton()
    }
}

@Composable fun SearchFields(tfFocusChanged: () -> Unit) {
    var tf1Value by rememberSaveable {
        mutableStateOf("")
    }
    var tf2Value by rememberSaveable {
        mutableStateOf("")
    }
    Column {
        UberTextField(
            modifier = Modifier.padding(horizontal = 8.dp),
            placeholder = "Enter pickup location",
            value = tf1Value,
            onValueChange = { newTf1Value ->
                tf1Value = newTf1Value
            },
            tfFocusChanged = tfFocusChanged
        )
        Spacer(modifier = Modifier.padding(top = 8.dp))
        UberTextField(
            modifier = Modifier.padding(horizontal = 8.dp),
            placeholder = "Where to?",
            value = tf2Value,
            onValueChange = { newTf2Value ->
                tf2Value = newTf2Value
            },
            tfFocusChanged = tfFocusChanged
        )
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
    }
}

@Composable fun SwitchRider(onClicked: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentWidth()
            .clickable(onClick = onClicked)
    ) {
        RiderIconWithGradient()
        Text(
            text = "Switch Rider",
            style = Typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        Icon(Filled.KeyboardArrowDown, contentDescription = "Switch rider dropdown button")
    }
}

@Composable fun RiderIconWithGradient() {
    val gradientRadial = Brush.radialGradient(listOf(Color.White, Color.White, Color.LightGray))
    Icon(
        imageVector = Icons.Filled.Person,
        contentDescription = "Rider Icon",
        tint = Color.Gray,
        modifier = Modifier
            .clip(CircleShape)
            .background(gradientRadial)
    )
}

@Composable fun AddDestinationButton() {
    IconButton(
        onClick = {},
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 24.dp)
            .size(36.dp)
            .clip(CircleShape)
            .background(colorGrayLighter)
    ) {
        Icon(Outlined.Add, contentDescription = "Navigate to previous screen button")
    }
}

@Composable fun BackButton(backButtonClicked: () -> Unit) {
    IconButton(onClick = backButtonClicked, modifier = Modifier.size(48.dp)) {
        Icon(Icons.Filled.ArrowBack, contentDescription = "Navigate to previous screen button")
    }
}

@Composable fun TimeLineWidget(isTfFocused: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 20.dp)
    ) {
        // circle
        Box(
            modifier = Modifier
                .width(8.dp)
                .height(8.dp)
                .clip(CircleShape)
                .background(if (isTfFocused) Color.LightGray else Color.Black)
        )

        // vertical line
        Box(
            modifier = Modifier
                .padding(vertical = 2.dp)
                .width(1.dp)
                .height(45.dp)
                .background(Color.LightGray)
        )

        // square
        Box(
            modifier = Modifier
                .width(8.dp)
                .height(8.dp)
                .background(if (isTfFocused) Color.Black else Color.LightGray)
        )
    }
}
