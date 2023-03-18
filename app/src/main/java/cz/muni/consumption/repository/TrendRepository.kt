package cz.muni.consumption.repository

import android.content.Context
import cz.muni.consumption.data.ConsumptionType
import cz.muni.consumption.data.MeasuredConsumption
import cz.muni.consumption.data.TrendData
import cz.muni.consumption.database.ConsumptionDatabase
import cz.muni.consumption.database.MeasuredConsumptionDao
import cz.muni.consumption.repository.mapper.toAppData
import java.util.*

class TrendRepository(
    context: Context,
    private val dao: MeasuredConsumptionDao = ConsumptionDatabase.create(context).measuredConsumptionDao(),
    private val weatherRepository: WeatherRepository = WeatherRepository()
) {

    companion object {
        private const val GAS_PRICE_CZK = 3
        private const val ELECTRICITY_PRICE_CZK = 6
    }

    fun getThisYearTrends(success: (List<TrendData>, List<TrendData>) -> Unit, fail: () -> Unit) {
        val consumptionGroupedByTypes = dao
            .selectAllOrderByDate()
            .map { it.toAppData() }
            .filter {
                Calendar.getInstance().apply {
                    time = it.measurementDate
                }.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)
            }
            .groupBy { it.type }

        weatherRepository.getWeather(
            success = { weatherData ->
                val electricity = (consumptionGroupedByTypes[ConsumptionType.ELECTRICITY] ?: emptyList())
                    .groupByMonths()
                    .createTrendData(ConsumptionType.ELECTRICITY, weatherData)

                val gas = (consumptionGroupedByTypes[ConsumptionType.GAS] ?: emptyList())
                    .groupByMonths()
                    .createTrendData(ConsumptionType.GAS, weatherData)

                success(electricity, gas)
            },
            fail = fail
        )
    }

    private fun List<MeasuredConsumption>.groupByMonths(): Map<Int, List<MeasuredConsumption>> =
        this.groupBy {
            val date = Calendar.getInstance().apply {
                time = it.measurementDate
            }
            date.get(Calendar.MONTH)
        }

    private fun Map<Int, List<MeasuredConsumption>>.createTrendData(
        type: ConsumptionType,
        weatherData: Map<Int, Double>
    ): MutableList<TrendData> {
        val trendData = mutableListOf<TrendData>()

        for (month in 0..11) {
            val monthConsumptions = this.getOrDefault(month, emptyList())
            val monthConsumption = if (monthConsumptions.isEmpty()) {
                0.0
            } else {
                calculateMonthConsumption(monthConsumptions, month)
            }

            val date = Calendar.getInstance().apply {
                set(Calendar.MONTH, month)
            }.time

            val price = when (type) {
                ConsumptionType.ELECTRICITY -> monthConsumption * ELECTRICITY_PRICE_CZK
                ConsumptionType.GAS -> monthConsumption * GAS_PRICE_CZK
            }

            trendData.add(
                TrendData(
                    consumption = monthConsumption,
                    date = date,
                    type = type,
                    price = price,
                    temperature = weatherData.getOrDefault(month, null)
                )
            )
        }

        return trendData
    }

    private fun Map<Int, List<MeasuredConsumption>>.calculateMonthConsumption(
        monthConsumptions: List<MeasuredConsumption>,
        month: Int
    ): Double {
        val previousMonthConsumption = if (month == 0) {
            this[month]?.first()?.consumption ?: 0.0
        } else {
            this[month - 1]?.last()?.consumption ?: 0.0
        }

        val monthConsumption = monthConsumptions.last().consumption
        return monthConsumption - previousMonthConsumption
    }
}