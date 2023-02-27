package cz.muni.consumption.ui.repository

import cz.muni.consumption.ui.data.ConsumptionType
import cz.muni.consumption.ui.data.MeasuredConsumption
import java.util.*

class ConsumptionRepository {

    fun getAllMeasuredConsumption(): List<MeasuredConsumption> {
        val result = mutableListOf<MeasuredConsumption>()

        repeat(100) {
            result.add(
                MeasuredConsumption(
                    id = it.toLong(),
                    consumption = it.toDouble() + Math.random(),
                    measurementDate = Date(System.currentTimeMillis()),
                    type = if (it % 2 == 0) ConsumptionType.GAS else ConsumptionType.ELECTRICITY,
                )
            )
        }

        return result
    }
}