package cz.muni.consumption.ui.util

// TODO 4.1 Extension for Double type
fun Double.oneDecimal(): String =
    String.format("%.1f", this)