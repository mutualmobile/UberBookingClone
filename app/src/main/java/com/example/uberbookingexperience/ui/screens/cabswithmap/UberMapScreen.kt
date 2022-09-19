package com.example.uberbookingexperience.ui.screens.cabswithmap

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.components.DefaultAppButton
import com.example.uberbookingexperience.components.DefaultAppIconButton
import com.example.uberbookingexperience.components.UberGoogleMap
import com.example.uberbookingexperience.ui.theme.UberBookingExperienceTheme
import com.example.uberbookingexperience.ui.theme.colorWhite
import com.example.uberbookingexperience.ui.util.rememberIsDeviceUnfoldedFoldable
import com.example.uberbookingexperience.ui.util.rememberIsMobileDevice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UberMapScreen(
    uberMapScreenViewModel: UberMapScreenVM,
    onNavigationBack: () -> Unit
) {
    val rememberScope = rememberCoroutineScope()
    val dynamicWidth: Float
    val dynamicPadding: Dp

    if (rememberIsMobileDevice()) {
        dynamicWidth = 1f
        dynamicPadding = 10.dp
    } else if (rememberIsDeviceUnfoldedFoldable()) {
        dynamicWidth = 0.6f
        dynamicPadding = 60.dp
    } else {
        dynamicWidth = 0.3f
        dynamicPadding = 60.dp
    }
    val isSelected by uberMapScreenViewModel.isItemSelected.collectAsState(false)
//    var isSelected by remember{ mutableStateOf(uberMapScreenViewModel.isItemSelected) }

    val stateListBottomSheet = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { false })
    val itemDetailsBottomSheet = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { false })

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = if (rememberIsMobileDevice()) Alignment.BottomCenter else Alignment.BottomStart
    ) {
        UberGoogleMap(viewModel = uberMapScreenViewModel)
        /*
        BottomSheetWrapper(
                dynamicWidth,
                modalBottomSheetState = stateListBottomSheet,
                openByDefault = true,
                onNavigationBack = onNavigationBack
            ) {
                UberCabsListing(uberMapScreenViewModel) {
                    rememberScope.launch {
                        stateListBottomSheet.animateTo(ModalBottomSheetValue.Hidden)
                        itemDetailsBottomSheet.animateTo(ModalBottomSheetValue.Expanded)
                    }
                }
            }
            BottomSheetWrapper(
                dynamicWidth,
                modalBottomSheetState = stateListBottomSheet,
                onNavigationBack = onNavigationBack
            ) {
                Spacer(modifier = Modifier.height(1.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(colorWhite)
                        .fillMaxHeight(0.3f)
                ) {
                    uberMapScreenViewModel.selectedUberCab?.let { it1 ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(horizontal = 14.dp, vertical = 6.dp)
                                .fillMaxWidth()
                        ) {
                            UberCabsListItemDetails(it1)
                        }
                    }
                }
        }
*/

        BottomSheetWrapper(dynamicWidth, onNavigationBack = onNavigationBack) {
            AnimatedVisibility(
                visible = !isSelected, enter = slideInVertically() + expandVertically(
                    // Expand from the top.
                    expandFrom = Alignment.Top
                ),
                exit = slideOutVertically() + shrinkVertically()
            ) {
                UberCabsListing(uberMapScreenViewModel) {
                    uberMapScreenViewModel.isItemSelected.value = true
                }
            }
            AnimatedVisibility(
                visible = isSelected, enter = slideInVertically() + expandVertically(
                    // Expand from the top.
                    expandFrom = Alignment.Top
                ),
                exit = slideOutVertically() + shrinkVertically()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(colorWhite)
                        .fillMaxWidth()
                        .fillMaxHeight(dynamicPercentageHeight())
                ) {
                    uberMapScreenViewModel.selectedUberCab?.let { it1 ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(horizontal = 14.dp, vertical = 6.dp)
                        ) {
                            UberCabsListItemDetails(it1)
                        }
                    }
                }
            }
            // }
        }

        Divider(thickness = 1.dp, color = Color.Transparent, modifier = Modifier.shadow(2.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(dynamicWidth)
                .background(colorWhite)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 14.dp, vertical = 6.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_upi_npci_logo),
                    contentDescription = stringResource(id = R.string.upi),
                    modifier = Modifier
                        .padding(horizontal = 4.dp, vertical = 10.dp)
                        .fillMaxWidth(0.1f)
                )
                androidx.compose.material3.Text(
                    text = stringResource(id = R.string.upi),
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 12.dp, vertical = 14.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_navigate_next_24),
                    contentDescription = stringResource(id = R.string.next),
                    modifier = Modifier
                        .padding(4.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = dynamicPadding)
            ) {
                DefaultAppButton(
                    buttonText = R.string.choose_uber, modifier =
                    Modifier
                        .weight(1f)
                        .padding(6.dp)
                ) {

                }
                DefaultAppIconButton(
                    R.drawable.baseline_schedule_24, modifier =
                    Modifier.padding(vertical = 6.dp, horizontal = 4.dp)
                ) {}
            }

        }
    }

    BackHandler {
        if (isSelected) {
            uberMapScreenViewModel.isItemSelected.value = false
            rememberScope.launch {
                itemDetailsBottomSheet.animateTo(ModalBottomSheetValue.Hidden)
                stateListBottomSheet.animateTo(ModalBottomSheetValue.HalfExpanded)
            }
        } else {
            onNavigationBack()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BottomSheetWrapper(
    dynamicWidth: Float = 1f,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    modalBottomSheetState: ModalBottomSheetState =
        rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.HalfExpanded,
            confirmStateChange = { false }),
    openByDefault: Boolean = false,
    onNavigationBack: () -> Unit,
    content: @Composable (() -> Unit) -> Unit
) {

    ModalBottomSheetLayout(
        sheetBackgroundColor = Color.Transparent,
        sheetState = modalBottomSheetState,
        sheetContent = {
            content {

            }
        },
        modifier = Modifier
            .fillMaxWidth(dynamicWidth)
            .wrapContentHeight()
    ) {}

    /*BackHandler {
        coroutineScope.launch {
            if (modalBottomSheetState.currentValue == ModalBottomSheetValue.Expanded) {
                modalBottomSheetState.animateTo(ModalBottomSheetValue.HalfExpanded)
            } else {
                onNavigationBack()
            }
        }
    }
    */
    if (openByDefault) {
        // Take action based on hidden state
        LaunchedEffect(modalBottomSheetState.currentValue) {
            when (modalBottomSheetState.currentValue) {
                ModalBottomSheetValue.Hidden -> {
                    modalBottomSheetState.animateTo(ModalBottomSheetValue.HalfExpanded)
                }
                else -> {
                    Log.i("Bottom sheet->", " ${modalBottomSheetState.currentValue} state")
                }
            }
        }
    }
}

@Composable
fun dynamicPercentageHeight(): Float {
    return if (rememberIsMobileDevice()) {
        0.35f
    } else {
        0.45f
    }
}

@Preview(showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Preview(showSystemUi = true, device = "spec:width=673.5dp,height=841dp,dpi=480")
@Preview(showSystemUi = true, device = "spec:width=1280dp,height=800dp,dpi=480")
@Preview(showSystemUi = true, device = "spec:width=1920dp,height=1080dp,dpi=480")
@Composable
private fun UberMapScreenScreenPreview() {
    UberBookingExperienceTheme {
        UberMapScreen(UberMapScreenVM()) {}
    }
}