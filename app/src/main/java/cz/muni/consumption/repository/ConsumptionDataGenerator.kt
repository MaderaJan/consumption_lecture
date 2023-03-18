package cz.muni.consumption.repository

import cz.muni.consumption.data.ConsumptionType
import cz.muni.consumption.data.MeasuredConsumption
import java.util.*
import kotlin.random.Random

class ConsumptionDataGenerator {

    fun generateData(): List<MeasuredConsumption> {
        val numberOfRecords = Random.nextInt(100, 1000)

        var currentElectricity = Random.nextDouble(1000.0, 10000.0)
        var currentGasConsumption = Random.nextDouble(1000.0, 10000.0)


        val dayOfYears = mutableListOf<Int>()
        repeat(numberOfRecords) {
            val day = Random.nextInt(1, 360)
            dayOfYears.add(day)
        }

        val generatedConsumption = mutableListOf<MeasuredConsumption>()
        dayOfYears
            .sorted()
            .forEach { dayOfYear ->
                val measurementDate = Calendar.getInstance().apply {
                    set(Calendar.DAY_OF_YEAR, dayOfYear)
                }.time

                currentElectricity += Random.nextDouble(5.0, 10.0)
                currentGasConsumption += Random.nextDouble(5.0, 10.0)

                generatedConsumption.add(
                    MeasuredConsumption(
                        id = 0,
                        consumption = currentGasConsumption,
                        measurementDate = measurementDate,
                        type = ConsumptionType.GAS
                    )
                )

                generatedConsumption.add(
                    MeasuredConsumption(
                        id = 0,
                        consumption = currentElectricity,
                        measurementDate = measurementDate,
                        type = ConsumptionType.ELECTRICITY
                    )
                )
            }

        return generatedConsumption
    }
}