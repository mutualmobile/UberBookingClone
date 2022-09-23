package com.example.uberbookingexperience.ui.screens.dashboard

import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

// on below line we are
// creating a function for tabs
@ExperimentalPagerApi
@Composable
fun BottomTabs() {
	val pagerState = rememberPagerState(initialPage = 0)

	val tabsList = listOf(
		"Home" to Icons.Default.Home,
		"Account" to Icons.Default.SupervisedUserCircle,
	)
	val scope = rememberCoroutineScope()
	TabRow(
		selectedTabIndex = pagerState.currentPage,
		backgroundColor = Color.White,
		contentColor = Color.Black,
		indicator = { tabPositions ->
			TabRowDefaults.Indicator(
				Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
				height = 2.dp,
				color = Color.Black
			)
		}
	) {
		tabsList.forEachIndexed { index, _ ->
			Tab(
				icon = {
					Icon(imageVector = tabsList[index].second, contentDescription = null)
				},
				text = {
					Text(
						tabsList[index].first,
						color = if (pagerState.currentPage == index) Color.White else Color.LightGray
					)
				},
				selected = pagerState.currentPage == index,
				onClick = {
					scope.launch {
						pagerState.animateScrollToPage(index)
					}
				}
			)
		}
	}
}
