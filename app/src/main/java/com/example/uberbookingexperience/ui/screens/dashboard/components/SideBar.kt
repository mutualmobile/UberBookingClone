package com.example.uberbookingexperience.ui.screens.dashboard.components

import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex

@Composable
fun SideBar() {
    var selectedItem by remember { mutableStateOf(0) }
    val tabsList = listOf(
        "Home" to Icons.Default.Home,
        "Account" to Icons.Default.AccountBox,
        "Settings" to Icons.Default.Settings,
    )
    NavigationRail(
        backgroundColor = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier
            .shadow(AppBarDefaults.TopAppBarElevation)
            .zIndex(1f)
    ) {
        tabsList.forEachIndexed { index, item ->
            val isSelected = selectedItem == index
            val itemColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray
            NavigationRailItem(
                label = {
                    Text(
                        item.first,
                        color = itemColor,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                icon = {
                    Icon(
                        tabsList[index].second,
                        contentDescription = tabsList[index].first,
                        tint = itemColor
                    )
                },
                selected = isSelected,
                onClick = { selectedItem = index }
            )
        }
    }
}
