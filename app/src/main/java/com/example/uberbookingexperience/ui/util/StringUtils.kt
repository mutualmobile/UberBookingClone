package com.example.uberbookingexperience.ui.util

import java.text.DecimalFormat

fun Float.toINRString():String{
    return try {
        val df = DecimalFormat("0.00")
        df.maximumFractionDigits = 2
        "₹" + df.format(this)
    }catch(ex:Exception){
        ""
    }
}