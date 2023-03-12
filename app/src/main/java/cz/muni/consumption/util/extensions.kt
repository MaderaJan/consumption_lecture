package cz.muni.consumption.util

fun Double.oneDecimal(): String =
    String.format("%.1f", this)