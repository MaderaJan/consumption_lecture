package cz.muni.consumption.data

import cz.muni.consumption.R

enum class ConsumptionType {
    ELECTRICITY, GAS;

    fun getUnit(): String = when (this) {
        ELECTRICITY -> "kw/h"
        GAS -> "m3"
    }

    fun geColor(): Int = when (this) {
        ELECTRICITY -> R.color.electricity
        GAS -> R.color.gas
    }
}