package com.example.uberbookingexperience.ui.screens.dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.ui.theme.spacing
import com.example.uberbookingexperience.ui.util.clickableWithRipple
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerWithIndicator(isMobile: Boolean, screenWidth: Dp) {
    val pagerState = rememberPagerState()
    val pagerHeightForLargerSize = screenWidth / 3
    val offers by rememberSaveable { mutableStateOf(getOffers()) }
    val offersForBiggerScreen by rememberSaveable { mutableStateOf(getOffersForBiggerScreen()) }

    Box {
        HorizontalPager(
            count = if (isMobile) offers.size else offersForBiggerScreen.size,
            state = pagerState,
            contentPadding = PaddingValues(
                top = MaterialTheme.spacing.medium,
                start = MaterialTheme.spacing.medium
            )
        ) { page ->
            // Our page content
            if (isMobile) {
                Page(150.dp, offers[page], modifier = Modifier.fillMaxWidth(),screenWidth, true)
            } else {
                Row {
                    Page(
                        pageHeight = pagerHeightForLargerSize,
                        offer = offersForBiggerScreen[page].offerFirst,
                        modifier = Modifier.weight(1f),
                        screenWidth
                    )
                    Page(
                        pageHeight = pagerHeightForLargerSize,
                        offer = offersForBiggerScreen[page].offerSecond,
                        modifier = Modifier.weight(1f),
                        screenWidth
                    )
                }
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(MaterialTheme.spacing.medium),
        )
    }
}

@Composable
fun Page(pageHeight: Dp, offer: Offer, modifier: Modifier,screenWidth: Dp, isMobile: Boolean = false) {
    val textWidthForMobile = screenWidth / 2
    val textWidthForLargerSize = screenWidth / 6
    Box(
        modifier = modifier
            .height(pageHeight)
            .padding(end = MaterialTheme.spacing.medium)
            .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
            .background(color = offer.bgColor)
            .clickableWithRipple {}
    ) {
        Image(
            painter = painterResource(id = offer.image),
            contentDescription = "offer image",
            modifier = Modifier
                .requiredSize(pageHeight)
                .align(Alignment.CenterEnd)
        )
        Text(
            text = offer.title,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = if (isMobile) 2 else 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .align(Alignment.CenterStart)
                .widthIn(max = if (isMobile) textWidthForMobile else textWidthForLargerSize),
        )
    }
}
