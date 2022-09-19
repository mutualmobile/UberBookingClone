package com.example.uberbookingexperience.model

import androidx.annotation.DrawableRes


data class UberCabInfo(
    val cabInfo: String,
    val cabPrice: Float,
    val carTime: String,
    @DrawableRes val cabIcon: Int,
    val cabPriceAlter: Float? = null,
    var isChecked: Boolean = false
)
