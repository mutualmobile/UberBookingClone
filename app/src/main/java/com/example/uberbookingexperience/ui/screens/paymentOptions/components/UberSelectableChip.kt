package com.example.uberbookingexperience.ui.screens.paymentOptions.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UberSelectableChip(
    modifier: Modifier = Modifier,
    title: String,
    isSelected: Boolean,
    icon: ImageVector,
    chipPadding: PaddingValues = PaddingValues(start = 8.dp),
    contentPadding: PaddingValues = PaddingValues(10.dp),
    selectedBackgroundColor: Color = MaterialTheme.colorScheme.onSurface,
    elevation: Dp = 2.dp,
    onClick: () -> Unit
) {
    val layoutDirection = LocalLayoutDirection.current

    FilterChip(
        modifier = modifier.padding(chipPadding).graphicsLayer {
            clip = false
        },
        selected = isSelected, onClick = onClick, label = {
            Text(
                modifier = Modifier.padding(
                    top = contentPadding.calculateTopPadding(),
                    end = contentPadding.calculateEndPadding(layoutDirection),
                    bottom = contentPadding.calculateBottomPadding()
                ),
                text = title
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = MaterialTheme.colorScheme.background,
            selectedContainerColor = selectedBackgroundColor,
            labelColor = MaterialTheme.colorScheme.onSurface,
            selectedLabelColor = MaterialTheme.colorScheme.surface,
            iconColor = MaterialTheme.colorScheme.onSurface,
            selectedLeadingIconColor = MaterialTheme.colorScheme.surface
        ),
        leadingIcon = {
            Icon(
                modifier = Modifier.padding(
                    start = contentPadding.calculateStartPadding(layoutDirection),
                    top = contentPadding.calculateTopPadding(),
                    bottom = contentPadding.calculateBottomPadding()
                ),
                imageVector = icon,
                contentDescription = "$title payee type chip"
            )
        }, shape = CircleShape,
        border = FilterChipDefaults.filterChipBorder(
            borderColor = Color.Transparent
        ),
        elevation = FilterChipDefaults.filterChipElevation(
            defaultElevation = elevation
        )
    )
}
