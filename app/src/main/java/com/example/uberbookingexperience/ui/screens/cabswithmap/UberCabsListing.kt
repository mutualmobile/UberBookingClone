package com.example.uberbookingexperience.ui.screens.cabswithmap

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.model.UberCabInfo
import com.example.uberbookingexperience.ui.theme.colorGrayExtraLight
import com.example.uberbookingexperience.ui.theme.colorGrayLight
import com.example.uberbookingexperience.ui.theme.colorWhite
import com.example.uberbookingexperience.ui.util.UberIconSize.MediumImage
import com.example.uberbookingexperience.ui.util.toINRString

@Composable
fun UberCabsListing(
    uberMapScreenVM: UberMapScreenVM,
    isVisibleDivider: Boolean = true,
    onItemSelected: (UberCabInfo) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(colorWhite)
            .fillMaxSize()
    ) {
        if (isVisibleDivider) {
            Spacer(modifier = Modifier.padding(4.dp))
            val dividerColor = colorGrayExtraLight
            Divider(
                thickness = 4.dp, color = dividerColor, modifier = Modifier
                    .background(
                        dividerColor,
                        RoundedCornerShape(12.dp)
                    )
                    .fillMaxWidth(0.2f)
                    .padding(1.dp)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                "Choose a ride, or swipe up for more",
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(4.dp)
            )
            Spacer(modifier = Modifier.padding(4.dp))
        } else {
            Spacer(modifier = Modifier.padding(24.dp))
        }

        LazyColumn {
            itemsIndexed(uberMapScreenVM.cabListing) { index, cabs ->
                UberCabsListItem(uberCabInfo = cabs) {
                    if (it.isChecked) {
                        onItemSelected(it)
                    } else {
                        uberMapScreenVM.selectItem(index)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun UberCabsListItem(uberCabInfo: UberCabInfo, onItemSelected: (UberCabInfo) -> Unit) {
    val requiredSize = if (uberCabInfo.isChecked) 90.dp else 80.dp
    val animateasDp: Dp by animateDpAsState(targetValue = requiredSize)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(if (uberCabInfo.isChecked) colorGrayLight else colorWhite)
            .clickable {
                onItemSelected(uberCabInfo)
            }

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 4.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = uberCabInfo.cabIcon),
                contentDescription = uberCabInfo.cabInfo,
                modifier = Modifier
                    .padding(8.dp)
                    .requiredSize(animateasDp)

            )

            Column() {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        uberCabInfo.cabInfo, style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(4.dp)
                            .weight(1f)
                    )
                    Text(
                        uberCabInfo.cabPrice.toINRString(),
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(4.dp)
                    )

                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        uberCabInfo.carTime, style = MaterialTheme.typography.labelSmall,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(4.dp)
                            .weight(1f)
                    )
                    uberCabInfo.cabPriceAlter?.let {
                        Text(
                            it.toINRString(),
                            style = MaterialTheme.typography.labelSmall.copy(
                                textAlign = TextAlign.End,
                                textDecoration = TextDecoration.LineThrough
                            ),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun UberCabsListItemDetails(uberCabInfo: UberCabInfo) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .fillMaxWidth()

    ) {
        Image(
            painter = painterResource(id = uberCabInfo.cabIcon),
            contentDescription = uberCabInfo.cabInfo,
            modifier = Modifier
                .requiredSize(MediumImage)
        )
        Column() {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    uberCabInfo.cabInfo, style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(1f)
                )
                Text(
                    uberCabInfo.cabPrice.toINRString(),
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(4.dp)
                )

            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    uberCabInfo.carTime, style = MaterialTheme.typography.labelMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(1f)
                )
                uberCabInfo.cabPriceAlter?.let {
                    Text(
                        it.toINRString(),
                        style = MaterialTheme.typography.labelMedium.copy(
                            textAlign = TextAlign.End,
                            textDecoration = TextDecoration.LineThrough
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun UberCabsListingPreview() {
    /*UberCabsListing(exampleCabList){

    }*/
}