package com.example.uberbookingexperience.ui.screens.dashboard

import androidx.compose.ui.graphics.Color
import com.example.uberbookingexperience.R

data class Offer(
    var title: String,
    var image: Int,
    var bgColor: Color
)

data class OfferForBiggerScreen(
    val offerFirst: Offer,
    val offerSecond: Offer
)

fun getOffers() : List<Offer> {
    val offers = arrayListOf<Offer>()
    offers.add(Offer("Ways people move around the world", R.drawable.womanwithphone, Color(0XFF0D4930)))
    offers.add(Offer("Opportunity for all", R.drawable.wheelchair,Color(0XFF81959F)))
    offers.add(Offer("Our users are diverse, and so are we", R.drawable.diversity,Color(0XFFFFD7E4)))
    offers.add(Offer("Ready? Then let's roll.", R.drawable.cityscape,Color(0XFF34D19B)))
    return offers
}

fun getOffersForBiggerScreen() : List<OfferForBiggerScreen> {
    val offerForBiggerScreen = arrayListOf<OfferForBiggerScreen>()
    offerForBiggerScreen.add(
        OfferForBiggerScreen(
        Offer("Ways people move around the world", R.drawable.womanwithphone, Color(0XFF0D4930)),
        Offer("Opportunity for all", R.drawable.wheelchair,Color(0XFF81959F))
        )
    )
    offerForBiggerScreen.add(
        OfferForBiggerScreen(
            Offer("Our users are diverse, and so are we", R.drawable.diversity,Color(0XFFFFD7E4)),
            Offer("Ready? Then let's roll.", R.drawable.cityscape,Color(0XFF34D19B))
        )
    )
    return offerForBiggerScreen
}


