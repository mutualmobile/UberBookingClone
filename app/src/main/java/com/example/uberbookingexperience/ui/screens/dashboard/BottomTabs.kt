package com.example.uberbookingexperience.ui.screens.dashboard

import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun BottomTabs() {

    val tabsList = listOf(
        "Home" to Icons.Default.Home,
        "Account" to Icons.Default.AccountBox,
    )
    var tabIndex by remember { mutableStateOf(0) }

    TabRow(
        selectedTabIndex = tabIndex,
        backgroundColor = Color.White,
        contentColor = Color.Black,
    ) {
        tabsList.forEachIndexed { index, _ ->
            Tab(
                icon = { Icon(imageVector = tabsList[index].second, contentDescription = null) },
                text = { Text(tabsList[index].first) },
                selected = tabIndex == index,
                onClick = {
                    tabIndex = index
                }
            )
        }
    }
}
