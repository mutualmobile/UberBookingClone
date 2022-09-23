package com.example.uberbookingexperience.ui.screens.whereTo

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.sharp.LocationOn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.service.RecentSearchesDataService.recentSearchesList
import com.example.uberbookingexperience.ui.theme.TextFieldFocusedGrey
import com.example.uberbookingexperience.ui.theme.TextFieldUnFocusedGrey
import com.example.uberbookingexperience.ui.theme.Typography
import com.example.uberbookingexperience.ui.theme.UberBookingExperienceTheme

@Composable
fun rememberIsMobileDevice(): Boolean {
  val config = LocalConfiguration.current
  return remember { derivedStateOf { config.screenWidthDp < 600 } }.value
}

@Composable
fun WhereToScreen(
  onGotoWhereScreen: () -> Unit,
  onBackScreen: () -> Unit,
) {
  var isRiderSheetVisible by remember { mutableStateOf(false) }
  Column(
    if (rememberIsMobileDevice()) {
      Modifier
        .padding(16.dp)
        .fillMaxSize()
        .background(Color.White)
    } else {
      Modifier
        .fillMaxHeight()
        .padding(16.dp)
        .width(300.dp)
        .background(Color.White)
    }
  ) {
    TopBar(
      backButtonClicked = {},
      onSwitchRiderClicked = { isRiderSheetVisible = !isRiderSheetVisible }
    )
    if (isRiderSheetVisible) {
      RiderTopSheet()
    }
    SearchFieldCard()
    RecentSearchesList(onGotoWhereScreen)
    SetLocationOnMapTile()
  }
  BackHandler() {
    onBackScreen()
  }
}

@Composable fun TopBar(backButtonClicked: ()-> Unit, onSwitchRiderClicked: () -> Unit, ) {
  Row(verticalAlignment = Alignment.CenterVertically) {
    Box(modifier = Modifier.padding(top = 24.dp)) {
      BackButton(backButtonClicked)
    }
    Box(modifier = Modifier
      .weight(0.9f),
      contentAlignment = Alignment.Center)
    {
      SwitchRider(onSwitchRiderClicked)
    }
    Spacer(modifier = Modifier.weight(0.1f))
  }
}

@Composable fun SearchFieldCard() {
  var isDropOffFocused by remember { mutableStateOf(false) }
  Row {
    TimeLineWidget(isDropOffFocused = isDropOffFocused)
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
    ) {
      SearchFields(
        isDropOffFocused,
        pickUpFocused = {
          isDropOffFocused = false
        },
        dropOffFocused = {
          isDropOffFocused = true
        }
      )
    }
    AddDestinationButton()
  }
}

@Composable fun TimeLineWidget(isDropOffFocused: Boolean) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 20.dp)
  ) {
    //circle
    Box(
      modifier = Modifier
        .width(8.dp)
        .height(8.dp)
        .clip(CircleShape)
        .background(if (isDropOffFocused) Color.LightGray else Color.Black)
    )

    //vertical line
    Box(
      modifier = Modifier
        .padding(vertical = 2.dp)
        .width(1.dp)
        .height(40.dp)
        .background(Color.LightGray)
    )

    //square
    Box(
      modifier = Modifier
        .width(8.dp)
        .height(8.dp)
        .background(if (isDropOffFocused) Color.Black else Color.LightGray)
    )
  }
}

@Composable fun BackButton(backButtonClicked: () -> Unit) {
  IconButton(onClick = { backButtonClicked }, modifier = Modifier.size(48.dp)) {
    Icon(Icons.Filled.ArrowBack, contentDescription = "Navigate to previous screen button")
  }
}

@Composable fun SearchFields(
  isDropOffFocused: Boolean,
  pickUpFocused: (isFocused: Boolean) -> Unit,
  dropOffFocused: (isFocused: Boolean) -> Unit
) {
  Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
    PickUpTextField(isDropOffFocused, pickUpFocused)
    DropOffTextField(isDropOffFocused, dropOffFocused)
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun RiderTopSheet() {
  Column {
    ListItem(
      leadingContent = { RiderIconWithGradient() },
      headlineText = {
        Text(
          text = "Me",
          style = Typography.titleMedium
        )
      },
      trailingContent = {
        Icon(
          painter = painterResource(id = R.drawable.ic_dot_circle),
          contentDescription = "",
          tint = Color.Black
        )
      },
      modifier = Modifier.clickable { }
    )

    Divider(color = Color.LightGray, thickness = 1.dp)

    ListItem(
      leadingContent = {
        Icon(
          painter = painterResource(id = R.drawable.ic_add_user),
          contentDescription = "",
          tint = Color.Black
        )
      },
      headlineText = {
        Text(
          text = "Add a rider",
          style = Typography.titleMedium
        )
      },
      modifier = Modifier.clickable { }
    )
  }
}

@Composable fun SwitchRider(onClicked: () -> Unit) {
  Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
    .wrapContentWidth()
    .clickable(onClick = onClicked)) {
    RiderIconWithGradient()
    Text(
      text = "Switch Rider",
      style = Typography.bodySmall,
      modifier = Modifier.padding(horizontal = 12.dp)
    )
    Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Switch rider dropdown button")
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun PickUpTextField(
  isDropOffFocused: Boolean,
  onFocused: (isFocused: Boolean) -> Unit
) {
  var pickUpTextFieldValue by rememberSaveable { mutableStateOf("") }
  var showLabel by remember { mutableStateOf(true) }

  TextField(
    value = pickUpTextFieldValue,
    onValueChange = {
      pickUpTextFieldValue = it
      when {
        pickUpTextFieldValue.isNotEmpty() -> showLabel = false
        pickUpTextFieldValue.isEmpty() -> showLabel = true
      }
    },
    label = { if (showLabel) Text("Enter pickup location") },
    singleLine = true,
    shape = RectangleShape,
    colors = TextFieldDefaults.textFieldColors(
      containerColor = if (isDropOffFocused) TextFieldUnFocusedGrey else TextFieldFocusedGrey,
      unfocusedLabelColor = Color.Gray,
      focusedIndicatorColor = Color.Transparent,
      unfocusedIndicatorColor = Color.Transparent
    ),
    textStyle = Typography.bodyLarge,
    modifier = Modifier
      .height(48.dp)
      .padding(top = 8.dp)
      .onFocusChanged {
        onFocused(it.hasFocus)
      }
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun DropOffTextField(
  isDropOffFocused: Boolean,
  onFocused: (isFocused: Boolean) -> Unit
) {
  var dropOffTextFieldValue by rememberSaveable { mutableStateOf("") }
  var showLabel by remember { mutableStateOf(true) }

  TextField(
    value = dropOffTextFieldValue,
    onValueChange = {
      dropOffTextFieldValue = it
      when {
        dropOffTextFieldValue.isNotEmpty() -> showLabel = false
        dropOffTextFieldValue.isEmpty() -> showLabel = true
      }
    },
    label = { if (showLabel) Text("Where to?") },
    singleLine = true,
    shape = RectangleShape,
    colors = TextFieldDefaults.textFieldColors(
      containerColor = if (isDropOffFocused) TextFieldFocusedGrey else TextFieldUnFocusedGrey,
      unfocusedLabelColor = Color.Gray,
      focusedIndicatorColor = Color.Transparent,
      unfocusedIndicatorColor = Color.Transparent
    ),
    textStyle = Typography.bodyLarge,
    modifier = Modifier
      .height(48.dp)
      .padding(top = 8.dp)
      .onFocusChanged {
        onFocused(it.hasFocus)
      }
  )
}

@Composable fun AddDestinationButton() {
  IconButton(
    onClick = { /*TODO*/ }, modifier = Modifier
      .padding(horizontal = 12.dp, vertical = 24.dp)
      .size(36.dp)
      .clip(CircleShape)
      .background(TextFieldFocusedGrey)
  ) {
    Icon(Icons.Outlined.Add, contentDescription = "Navigate to previous screen button")
  }
}

@Composable
fun RecentSearchesList(onGotoWhereScreen: () -> Unit) {
  LazyColumn(modifier = Modifier
    .fillMaxWidth()
    .clickable {
      onGotoWhereScreen()
    }

  ) {
    items(recentSearchesList) { searchItem ->
      ListTile(
        icon = Icons.Sharp.LocationOn,
        contentDesc = "",
        title = searchItem.location,
        subtitle = searchItem.locationDesc
      )
    }
  }
}

@Composable fun SetLocationOnMapTile() {
  ListTile(
    icon = Icons.Default.LocationOn,
    contentDesc = null,
    title = "Set location on map",
    subtitle = null
  )
}

@Preview(showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Preview(showSystemUi = true, device = "spec:width=673.5dp,height=841dp,dpi=480")
@Preview(showSystemUi = true, device = "spec:width=1280dp,height=800dp,dpi=480")
@Preview(showSystemUi = true, device = "spec:width=1920dp,height=1080dp,dpi=480")
@Composable
private fun WhereToScreenPreview() {
  UberBookingExperienceTheme {
    WhereToScreen({}, {})
  }
}