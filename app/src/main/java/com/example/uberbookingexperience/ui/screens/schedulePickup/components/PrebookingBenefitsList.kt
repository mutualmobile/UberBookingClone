package com.example.uberbookingexperience.ui.screens.schedulePickup.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uberbookingexperience.ui.common.UberDivider
import com.example.uberbookingexperience.ui.theme.spacing
import com.example.uberbookingexperience.ui.util.UberIconSize
import com.example.uberbookingexperience.ui.util.clickableWithRipple

@Immutable
data class PrebookingBenefitItem(
    @DrawableRes val icon: Int,
    val text: String
)

@Composable
fun PrebookingBenefitsList(
    modifier: Modifier = Modifier,
    prebookingBenefits: SnapshotStateList<PrebookingBenefitItem>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium)
    ) {
        Text(
            modifier = Modifier.padding(bottom = MaterialTheme.spacing.medium),
            text = "Added flexibility if you book 2 hours ahead",
            style = MaterialTheme.typography.headlineSmall
        )
        prebookingBenefits.forEachIndexed { index, prebookingBenefitItem ->
            PrebookingBenefitsListItem(
                prebookingBenefitItem = prebookingBenefitItem,
                showDivider = index != prebookingBenefits.lastIndex
            )
        }
        Text(
            modifier = Modifier
                .padding(MaterialTheme.spacing.large)
                .clickableWithRipple {},
            text = "See terms",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
private fun PrebookingBenefitsListItem(
    prebookingBenefitItem: PrebookingBenefitItem,
    showDivider: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(12.dp).size(UberIconSize.NormalIcon),
            painter = painterResource(id = prebookingBenefitItem.icon),
            contentDescription = null
        )
        Text(
            text = prebookingBenefitItem.text,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Light,
            letterSpacing = 0.5.sp
        )
    }
    if (showDivider) {
        UberDivider(Modifier.padding(start = 48.dp))
    }
}
