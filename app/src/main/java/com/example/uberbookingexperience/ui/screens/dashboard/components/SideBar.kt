package com.example.uberbookingexperience.ui.screens.dashboard.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.zIndex
import com.example.uberbookingexperience.ui.theme.colorGrayLight

@Composable
fun SideBar() {
    var selectedItem by rememberSaveable { mutableStateOf(0) }
    val tabsList by rememberSaveable {
        mutableStateOf(
            listOf(
                "Home" to Icons.Default.Home,
                "Account" to Icons.Default.AccountBox,
                "Settings" to Icons.Default.Settings,
            )
        )
    }

    NavigationRail(
        backgroundColor = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier
            .shadow(AppBarDefaults.TopAppBarElevation)
            .zIndex(1f)
    ) {
        Spacer(modifier = Modifier.statusBarsPadding())
        tabsList.forEachIndexed { index, item ->
            val isSelected = selectedItem == index
            val itemColor = if (isSelected) MaterialTheme.colorScheme.primary else colorGrayLight
            NavigationRailItem(
                label = {
                    Text(
                        item.first,
                        color = itemColor,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium
                        )
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
