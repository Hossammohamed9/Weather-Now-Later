package com.core.utils

import java.text.SimpleDateFormat
import java.util.Date
import kotlin.math.roundToInt

fun Double.toCelsius(): String{
    return this.minus(273.15).roundToInt().toString() + " C"
}

fun Long.formatToHours(): String{
    return SimpleDateFormat("h a").format(Date(this * 1000))
}

fun Long.formatToDay(): String{
    return SimpleDateFormat("EEE").format(Date(this * 1000))
}