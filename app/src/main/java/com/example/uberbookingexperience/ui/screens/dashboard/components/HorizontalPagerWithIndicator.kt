package com.example.uberbookingexperience.ui.screens.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.ui.theme.spacing
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerWithIndicator(isMobile: Boolean) {
    val pagerState = rememberPagerState()
    val pagerHeightForLargerSize = LocalConfiguration.current.screenHeightDp.dp / 2

    Box {
        HorizontalPager(
            count = 5,
            state = pagerState,
            contentPadding = PaddingValues(
                top = MaterialTheme.spacing.medium,
                start = MaterialTheme.spacing.medium
            )
        ) { page ->
            // Our page content
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (isMobile) 150.dp else pagerHeightForLargerSize)
                    .padding(end = MaterialTheme.spacing.medium)
                    .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
                    .background(color = Color.Green)
            )
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(MaterialTheme.spacing.medium),
        )
    }
}