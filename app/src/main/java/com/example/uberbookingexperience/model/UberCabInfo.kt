package com.example.uberbookingexperience.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.saveable.mapSaver


@Immutable
data class UberCabInfo(
    val cabInfo: String,
    val cabPrice: Float,
    val carTime: String,
    @DrawableRes val cabIcon: Int,
    val cabPriceAlter: Float? = null,
    var isChecked: Boolean = false
)

val UberCabInfoSaver = run {
    val cabInfo = "Name"
    val cabPrice = "cabPrice"
    val carTime = "carTime"
    val cabIcon = "cabIcon"
    val cabPriceAlter = "cabPriceAlter"
    val isChecked = "isChecked"
    mapSaver(
        save = {
            mapOf(
                cabInfo to it.cabInfo, cabPrice to it.cabPrice,
                carTime to it.carTime,
                cabIcon to it.cabIcon, cabPriceAlter to it.cabPriceAlter, isChecked to it.isChecked
            )
        },
        restore = {
            UberCabInfo(
                it[cabInfo] as String,
                it[cabPrice] as Float,
                it[carTime] as String,
                it[cabIcon] as Int,
                it[cabPriceAlter] as Float?,
                it[isChecked] as Boolean
            )
        }
    )
}