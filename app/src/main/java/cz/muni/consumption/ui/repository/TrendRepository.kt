package cz.muni.consumption.ui.repository

import cz.muni.consumption.ui.data.ConsumptionType
import cz.muni.consumption.ui.data.TrendData
import java.util.*
import kotlin.random.Random

class TrendRepository {

    /**
     * Generate mocked Trends for given type in 1 year interval
     * @param type Type of mocked data to be generated
     * @return mocked trends from january to december sorted by date
     */
    fun getMockedTrends(type: ConsumptionType): List<TrendData> {
        val resultData = mutableListOf<TrendData>()

        repeat(12) { month ->
            val calendar = Calendar.getInstance().apply {
                set(Calendar.MONTH, month)
            }

            val consumption = Random.nextDouble(30.0, 100.0)
            val price = when (type) {
                ConsumptionType.ELECTRICITY -> 6.5 * consumption
                ConsumptionType.GAS -> 3.5 * consumption
            }

            val trend = TrendData(
                consumption = consumption,
                date = calendar.time,
                type = type,
                price = price,
                temperature = Random.nextDouble(1.0, 30.0),
            )

            resultData.add(trend)
        }

        return resultData
    }
}