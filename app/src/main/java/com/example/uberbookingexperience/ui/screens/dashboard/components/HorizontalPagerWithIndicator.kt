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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.ui.screens.dashboard.Offer
import com.example.uberbookingexperience.ui.screens.dashboard.OfferForBiggerScreen
import com.example.uberbookingexperience.ui.screens.dashboard.getOffers
import com.example.uberbookingexperience.ui.screens.dashboard.getOffersForBiggerScreen
import com.example.uberbookingexperience.ui.theme.spacing
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerWithIndicator(isMobile: Boolean) {
    val pagerState = rememberPagerState()
    val pagerHeightForLargerSize = LocalConfiguration.current.screenHeightDp.dp / 3
    val offers = getOffers()
    val offersForBiggerScreen = getOffersForBiggerScreen()

    Box {
        HorizontalPager(
            count = if(isMobile) offers.size else offersForBiggerScreen.size,
            state = pagerState,
            contentPadding = PaddingValues(
                top = MaterialTheme.spacing.medium,
                start = MaterialTheme.spacing.medium
            )
        ) { page ->
            // Our page content
            val pageHeight = if (isMobile) 150.dp else pagerHeightForLargerSize
            if (isMobile) {
                Page(pageHeight, offers[page], modifier = Modifier.fillMaxWidth(), isMobile)
            } else {
                Row {
                   Page(pageHeight = pageHeight, offer = offersForBiggerScreen[page].offerFirst, modifier = Modifier.weight(1f), isMobile)
                   Page(pageHeight = pageHeight, offer = offersForBiggerScreen[page].offerSecond, modifier = Modifier.weight(1f), isMobile)
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
fun Page(pageHeight: Dp, offer: Offer, modifier: Modifier, isMobile: Boolean) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val textWidthForMobile = screenWidth / 2
    val textWidthForLargerSize = screenWidth / 6
    Box(
        modifier = modifier
            .height(pageHeight)
            .padding(end = MaterialTheme.spacing.medium)
            .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
            .background(color = offer.bgColor)
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
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterStart)
                .widthIn(max = if (isMobile) textWidthForMobile else textWidthForLargerSize),
        )
    }
}
