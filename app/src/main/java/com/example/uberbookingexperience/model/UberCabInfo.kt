package com.example.uberbookingexperience.model

import androidx.compose.ui.graphics.painter.Painter


data class UberCabInfo(
    val cabInfo: String,
    val cabPrice: Float,
    val carTime: String,
    val cabIcon: Painter,
    val cabPriceAlter: Float?=null,
    var isSelected:Boolean=false
)
