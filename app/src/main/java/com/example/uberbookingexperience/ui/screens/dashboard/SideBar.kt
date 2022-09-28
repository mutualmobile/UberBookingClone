package com.example.uberbookingexperience.ui.screens.dashboard

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun SideBar() {
    var selectedItem by remember { mutableStateOf(0) }
    val tabsList = listOf(
        "Home" to Icons.Default.Home,
        "Account" to Icons.Default.SupervisedUserCircle,
        "Settings" to Icons.Default.Settings,
    )
    Row {
        NavigationRail {
            tabsList.forEachIndexed { index, item ->
                NavigationRailItem(
                    label = { Text(item.first) },
                    icon = {
                        Icon(
                            tabsList[index].second,
                            contentDescription = tabsList[index].first,
                        )
                    },
                    selected = selectedItem == index,
                    onClick = { selectedItem = index }
                )
            }
        }
    }
}