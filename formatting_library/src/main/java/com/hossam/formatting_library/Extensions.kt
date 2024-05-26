package com.hossam.formatting_library

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

fun Double.tempToCelsius(): String{
    return this.minus(273.15).roundToInt().toString() + " \u2103"
}

fun Long.formatUnixToHours(): String{
    return SimpleDateFormat("h a", Locale.ROOT).format(Date(this * 1000))
}

fun Long.formatUnixToDay(): String{
    return SimpleDateFormat("EEE", Locale.ROOT).format(Date(this * 1000))
}