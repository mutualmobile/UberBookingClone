package com.example.uberbookingexperience.ui.screens.cabswithmap

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.model.UberCabInfo
import com.example.uberbookingexperience.ui.theme.colorGrayExtraLight
import com.example.uberbookingexperience.ui.theme.colorGrayLight
import com.example.uberbookingexperience.ui.theme.colorWhite
import com.example.uberbookingexperience.ui.theme.spacing
import com.example.uberbookingexperience.ui.util.UberIconSize.MediumImage
import com.example.uberbookingexperience.ui.util.UberIconSize.SmallImage
import com.example.uberbookingexperience.ui.util.clickableWithRipple
import com.example.uberbookingexperience.ui.util.toINRString

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UberCabsListing(
    uberMapScreenVM: UberMapScreenVM,
    isVisibleDivider: Boolean = true,
    currentFraction: Float = 1f,
    onItemChecked: (UberCabInfo) -> Unit,
    onItemSelected: (UberCabInfo) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(colorWhite)
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.graphicsLayer(alpha = if (isVisibleDivider) currentFraction else 1f - currentFraction)
        ) {
            Spacer(modifier = Modifier.padding(MaterialTheme.spacing.extraSmall))
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
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
            )
            Spacer(modifier = Modifier.padding(MaterialTheme.spacing.extraSmall))
        }

        //}
        /*else {
            Spacer(modifier = Modifier.padding(MaterialTheme.spacing.large))
        }*/

        /*Box(modifier = Modifier.
        background(colorBlack.copy(alpha = currentFraction)).
        graphicsLayer(alpha = currentFraction)){
            Box(contentAlignment = Alignment.CenterStart,
                modifier = Modifier.fillMaxWidth().statusBarsPadding()) {

                Icon(painter = painterResource(id = R.drawable.baseline_arrow_downward_24), contentDescription = ""
                    ,Modifier.graphicsLayer(rotationZ = currentFraction*90f), tint = colorWhite.copy(alpha = 0.4f)
                )
                Text(
                    "Choose a ride",
                    style = MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.Center),
                    color = colorWhite.copy(alpha = 0.8f),
                    modifier = Modifier.padding(MaterialTheme.spacing.medium).fillMaxWidth(1f)
                )
            }
        }*/
        LazyColumn {
            stickyHeader {
                AnimatedVisibility(
                    visible = !isVisibleDivider,
                    enter = slideInVertically(), exit = slideOutVertically()
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.statusBarsPadding())
                        Text(
                            "Popular",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(MaterialTheme.spacing.medium)
                                .graphicsLayer(alpha = currentFraction)
                        )
                    }
                }
            }
            itemsIndexed(uberMapScreenVM.cabListing) { index, cabs ->
                UberCabsListItem(uberCabInfo = cabs) {
                    if (!isVisibleDivider) {
                        uberMapScreenVM.selectItem(index)
                        onItemSelected(it)
                        onItemChecked(it)
                    } else {
                        if (it.isChecked) {
                            onItemSelected(it)
                        } else {
                            onItemChecked(it)
                            uberMapScreenVM.selectItem(index)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun UberCabsListItem(uberCabInfo: UberCabInfo, onItemSelected: (UberCabInfo) -> Unit) {
    val scaleX by animateFloatAsState(targetValue = if (uberCabInfo.isChecked) 1.1f else 1f)
    val scaleY by animateFloatAsState(targetValue = if (uberCabInfo.isChecked) 1.1f else 1f)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(if (uberCabInfo.isChecked) colorGrayLight else colorWhite)
            .clickableWithRipple {
                onItemSelected(uberCabInfo)
            }

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = MaterialTheme.spacing.extraSmall)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = uberCabInfo.cabIcon),
                contentDescription = uberCabInfo.cabInfo,
                modifier = Modifier
                    .requiredSize(SmallImage)
                    .graphicsLayer(
                        scaleX = scaleX,
                        scaleY = scaleY
                    )

            )

            Column(modifier = Modifier.padding(start = MaterialTheme.spacing.small)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        uberCabInfo.cabInfo, style = MaterialTheme.typography.titleLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.extraSmall)
                            .weight(1f)
                    )
                    Text(
                        uberCabInfo.cabPrice.toINRString(),
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
                    )

                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    UberCabsListItemText(
                        uberCabInfo.carTime,
                        textStyle = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.extraSmall)
                            .weight(1f)
                    )
                    uberCabInfo.cabPriceAlter?.let {
                        UberCabsListItemText(
                            it.toINRString(),
                            textStyle = MaterialTheme.typography.bodyMedium.copy(
                                textAlign = TextAlign.End,
                                textDecoration = TextDecoration.LineThrough
                            ),
                            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UberCabsListItemText(
    text: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    maxLine: Int = 2,
    overflow: TextOverflow = TextOverflow.Ellipsis,
) {
    Text(
        text,
        style = textStyle,
        maxLines = maxLine,
        overflow = overflow,
        modifier = modifier
    )
}

@Composable
fun UberCabsListItemDetails(uberCabInfo: UberCabInfo) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = MaterialTheme.spacing.extraSmall)
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
                    uberCabInfo.carTime, style = MaterialTheme.typography.titleSmall,
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
}