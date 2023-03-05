package cz.muni.consumption.ui.data

import java.util.*

// TODO 1. Trend data
data class TrendData(
    val consumption: Double,
    val date: Date,
    val type: ConsumptionType,
    val price: Double,
    val temperature: Double?
)