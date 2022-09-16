package com.example.uberbookingexperience.ui.screens.paymentOptions.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PayeeType() {
    var selectedItemTitle by remember { mutableStateOf("Personal") }

    LazyRow(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)
    ) {
        item {
            UberSelectableChip(
                modifier = Modifier.padding(start = 8.dp),
                title = "Personal",
                isSelected = selectedItemTitle == "Personal",
                icon = Icons.Filled.Person,
                onClick = {
                    selectedItemTitle = "Personal"
                }
            )
        }
        item {
            UberSelectableChip(
                modifier = Modifier.padding(start = 8.dp),
                title = "Business",
                isSelected = selectedItemTitle == "Business",
                icon = Icons.Filled.Work,
                onClick = {
                    selectedItemTitle = "Business"
                },
                selectedBackgroundColor = MaterialTheme.colorScheme.secondary
            )
        }
    }
}
