package com.example.uberbookingexperience.ui.screens.whereTo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.sharp.LocationOn
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.service.RecentSearchesDataService
import com.example.uberbookingexperience.ui.common.UberButton
import com.example.uberbookingexperience.ui.common.UberGoogleMap
import com.example.uberbookingexperience.ui.theme.Typography
import com.example.uberbookingexperience.ui.theme.colorGrayLighter
import com.example.uberbookingexperience.ui.theme.spacing
import com.example.uberbookingexperience.ui.util.LargeScreenChildMaxWidth
import com.example.uberbookingexperience.ui.util.clickableWithRipple
import com.example.uberbookingexperience.ui.util.limitWidth
import com.example.uberbookingexperience.ui.util.rememberIsMobileDevice
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun WhereTo(
    navigateToMapScreen: () -> Unit,
    onBackPressed: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    val isMobile = rememberIsMobileDevice()

    var isPickupLocationTfFocused by rememberSaveable { mutableStateOf(false) }
    var isWhereToTfFocused by rememberSaveable { mutableStateOf(false) }

    var pickupLocationTfText by rememberSaveable { mutableStateOf("") }
    var whereToTfText by rememberSaveable { mutableStateOf("") }

    val pickupLocationTfFocusRequester = remember { FocusRequester() }
    val whereToTfRequester = remember { FocusRequester() }

    val state = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Expanded
        )
    )

    val keyboardManager = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val swipeProgress = state.rememberBottomSheetProgress()

    val isMapPinVisible by remember {
        derivedStateOf {
            !state.bottomSheetState.isExpanded || !isMobile
        }
    }

    var filteredList = remember {
        derivedStateOf {
            RecentSearchesDataService.recentSearchesList.filter { searchItem ->
                when {
                    !isMapPinVisible || !isMobile -> true
                    isPickupLocationTfFocused -> {
                        searchItem.location.contains(pickupLocationTfText, true)
                    }
                    isWhereToTfFocused -> {
                        searchItem.location.contains(whereToTfText, true)
                    }
                    else -> false
                }
            }
        }
    }

    val cameraPositionState = rememberCameraPositionState()

    val uberGoogleMap: @Composable (modifier: Modifier) -> Unit = remember(isMobile) {
        movableContentOf { modifier: Modifier ->
            UberGoogleMap(
                modifier = modifier,
                cameraPositionState = cameraPositionState,
                nonMapContent = {
                    UberButton(
                        modifier = Modifier
                            .then(
                                if (isMobile) {
                                    Modifier.fillMaxWidth()
                                } else {
                                    Modifier.limitWidth()
                                }
                            )
                            .align(Alignment.BottomEnd)
                            .padding(MaterialTheme.spacing.medium)
                            .navigationBarsPadding()
                            .imePadding(),
                        text = "Done"
                    ) {
                        when {
                            pickupLocationTfText.isBlank() -> {
                                pickupLocationTfFocusRequester.requestFocus()
                            }
                            whereToTfText.isBlank() -> {
                                whereToTfRequester.requestFocus()
                            }
                            else -> {
                                navigateToMapScreen()
                            }
                        }
                    }
                    if (isMapPinVisible) {
                        Image(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .imePadding(),
                            painter = painterResource(id = R.drawable.ic_location_pin),
                            contentDescription = null,
                        )
                    }
                }
            )
        }
    }

    LaunchedEffect(cameraPositionState.isMoving, isMobile) {
        if (cameraPositionState.isMoving) {
            if (isMobile) {
                filteredList = derivedStateOf { emptyList() }
            }
        } else {
            when {
                isPickupLocationTfFocused -> {
                    pickupLocationTfText = RecentSearchesDataService
                        .recentSearchesList
                        .random()
                        .location
                }
                isWhereToTfFocused -> {
                    whereToTfText = RecentSearchesDataService
                        .recentSearchesList
                        .random()
                        .location
                }
            }
        }
    }

    LaunchedEffect(Unit) { pickupLocationTfFocusRequester.requestFocus() }

    LaunchedEffect(state.bottomSheetState.isExpanded) {
        if (state.bottomSheetState.isExpanded) {
            when {
                isPickupLocationTfFocused -> {
                    pickupLocationTfFocusRequester.requestFocus()
                }
                isWhereToTfFocused -> {
                    whereToTfRequester.requestFocus()
                }
            }
        } else {
            keyboardManager?.hide()
            focusManager.clearFocus()
        }
    }

    Row(modifier = Modifier.fillMaxWidth()) {
        Scaffold(
            modifier = if (isMobile) Modifier.fillMaxWidth() else Modifier.width(
                LargeScreenChildMaxWidth
            ),
            topBar = {
                Surface(
                    modifier = Modifier.shadow(elevation = MaterialTheme.spacing.small)
                ) {
                    Column {
                        Box(
                            modifier = Modifier.statusBarsPadding(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            // BackButton
                            IconButton(onClick = onBackPressed) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Navigate to previous screen button"
                                )
                            }
                            // SwitchRider
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth()
                                    .clickableWithRipple {}
                            ) {
                                // RiderIconWithGradient
                                val gradientRadial =
                                    Brush.radialGradient(
                                        listOf(
                                            Color.White,
                                            Color.White,
                                            Color.LightGray
                                        )
                                    )
                                Icon(
                                    imageVector = Icons.Filled.Person,
                                    contentDescription = "Rider Icon",
                                    tint = Color.Gray,
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(gradientRadial)
                                )
                                Text(
                                    text = "Switch Rider",
                                    style = Typography.bodyMedium,
                                    modifier = Modifier.padding(horizontal = 12.dp)
                                )
                                Icon(
                                    Icons.Filled.KeyboardArrowDown,
                                    contentDescription = "Switch rider dropdown button"
                                )
                            }
                        }

                        // SearchFieldCard
                        Row(modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)) {
                            // TimeLineWidget
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(horizontal = MaterialTheme.spacing.medium)
                                    .padding(top = 20.dp)
                            ) {
                                // circle
                                Box(
                                    modifier = Modifier
                                        .size(MaterialTheme.spacing.small)
                                        .clip(CircleShape)
                                        .background(if (isPickupLocationTfFocused) Color.Black else Color.LightGray)
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
                                        .size(MaterialTheme.spacing.small)
                                        .background(if (isWhereToTfFocused) Color.Black else Color.LightGray)
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            ) {
                                // SearchFields
                                Column {
                                    UberTextField(
                                        modifier = Modifier
                                            .padding(horizontal = MaterialTheme.spacing.small)
                                            .onFocusChanged {
                                                if ((it.hasFocus || it.isFocused) && state.bottomSheetState.isCollapsed) {
                                                    coroutineScope.launch {
                                                        state.bottomSheetState.expand()
                                                    }
                                                }
                                            }
                                            .focusRequester(pickupLocationTfFocusRequester),
                                        placeholder = "Enter pickup location",
                                        value = pickupLocationTfText,
                                        onValueChange = { newPickupLocationText ->
                                            pickupLocationTfText = newPickupLocationText
                                        },
                                        onFocus = {
                                            isPickupLocationTfFocused = true
                                            isWhereToTfFocused = false
                                        }
                                    )
                                    Spacer(modifier = Modifier.padding(top = 8.dp))
                                    UberTextField(
                                        modifier = Modifier
                                            .padding(horizontal = MaterialTheme.spacing.small)
                                            .onFocusChanged {
                                                if ((it.hasFocus || it.isFocused) && state.bottomSheetState.isCollapsed) {
                                                    coroutineScope.launch {
                                                        state.bottomSheetState.expand()
                                                    }
                                                }
                                            }
                                            .focusRequester(whereToTfRequester),
                                        placeholder = "Where to?",
                                        value = whereToTfText,
                                        onValueChange = { newWhereToText ->
                                            whereToTfText = newWhereToText
                                        },
                                        onFocus = {
                                            isPickupLocationTfFocused = false
                                            isWhereToTfFocused = true
                                        }
                                    )
                                    Spacer(modifier = Modifier.padding(bottom = 16.dp))
                                }
                            }
                            // AddDestinationButton
                            IconButton(
                                onClick = {},
                                modifier = Modifier
                                    .padding(horizontal = 12.dp, vertical = 24.dp)
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(colorGrayLighter)
                            ) {
                                Icon(
                                    Icons.Outlined.Add,
                                    contentDescription = "Navigate to previous screen button"
                                )
                            }
                        }
                    }
                }
            }
        ) { padding ->
            BottomSheetScaffold(
                scaffoldState = state,
                modifier = Modifier
                    .padding(padding),
                sheetContent = {
                    LazyColumn(
                        modifier = Modifier
                            .padding(horizontal = MaterialTheme.spacing.extraLarge.times(1 - swipeProgress))
                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                            .fillMaxHeight()
                            .background(Color.White),
                        userScrollEnabled = isMobile
                    ) {
                        items(filteredList.value) { searchItem ->
                            ListTile(
                                icon = Icons.Sharp.LocationOn,
                                contentDesc = "",
                                title = searchItem.location,
                                subtitle = searchItem.locationDesc,
                                isClicked = {
                                    when {
                                        isPickupLocationTfFocused -> {
                                            pickupLocationTfText = searchItem.location
                                            focusManager.moveFocus(FocusDirection.Down)
                                        }
                                        isWhereToTfFocused -> {
                                            whereToTfText = searchItem.location
                                            navigateToMapScreen()
                                        }
                                    }
                                }
                            )
                        }
                    }
                },
                sheetPeekHeight = if (!isMobile || !isMapPinVisible) 100.dp else 0.dp,
                sheetElevation = 0.dp,
                sheetBackgroundColor = Color.Transparent,
                sheetGesturesEnabled = isMobile
            ) {
                if (isMobile) {
                    uberGoogleMap(Modifier.fillMaxSize())
                }
            }
        }
        if (!isMobile) {
            uberGoogleMap(Modifier.weight(1f))
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetScaffoldState.rememberBottomSheetProgress() = remember {
    derivedStateOf {
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        when {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 0f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 1f
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> fraction
            else -> 1f - fraction
        }
    }
}.value
