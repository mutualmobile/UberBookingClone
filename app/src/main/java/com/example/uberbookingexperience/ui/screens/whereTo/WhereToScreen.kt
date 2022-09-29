package com.example.uberbookingexperience.ui.screens.whereTo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.LocationOn
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.service.RecentSearchesDataService
import com.example.uberbookingexperience.ui.common.UberGoogleMap
import com.example.uberbookingexperience.ui.screens.whereTo.components.TopSection
import com.example.uberbookingexperience.ui.util.LargeScreenChildMaxWidth
import com.example.uberbookingexperience.ui.util.rememberIsMobileDevice

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WhereTo(listItemClicked: () -> Unit, onBackPressed: () -> Unit) {
    val isMobile = rememberIsMobileDevice()
    Row(modifier = Modifier.fillMaxWidth()) {
        val state = rememberBottomSheetScaffoldState(
            bottomSheetState = rememberBottomSheetState(
                initialValue = if (isMobile) BottomSheetValue.Collapsed else BottomSheetValue.Expanded
            )
        )
        val swipeProgress = state.rememberBottomSheetProgress()

        Scaffold(
            modifier = if (isMobile) Modifier.fillMaxWidth() else Modifier.width(
                LargeScreenChildMaxWidth
            ),
            topBar = {
                TopSection(onBackPressed)
            }
        ) { padding ->
            BottomSheetScaffold(
                scaffoldState = state,
                modifier = Modifier
                    .padding(padding),
                sheetContent = {
                    LazyColumn(
                        modifier = Modifier
                            .padding(horizontal = 32.dp.times(1 - swipeProgress))
                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                            .fillMaxHeight()
                            .background(Color.White),
                        userScrollEnabled = isMobile
                    ) {
                        items(RecentSearchesDataService.recentSearchesList) { searchItem ->
                            ListTile(
                                icon = Icons.Sharp.LocationOn,
                                contentDesc = "",
                                title = searchItem.location,
                                subtitle = searchItem.locationDesc,
                                isClicked = listItemClicked
                            )
                        }
                    }
                },
                sheetPeekHeight = 100.dp,
                sheetElevation = 0.dp,
                sheetBackgroundColor = Color.Transparent,
                sheetGesturesEnabled = isMobile
            ) { _ ->
                if (isMobile) {
                    UberGoogleMap(
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
        }
        if (!isMobile) {
            UberGoogleMap(
                modifier = Modifier
                    .weight(1f)
            )
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
