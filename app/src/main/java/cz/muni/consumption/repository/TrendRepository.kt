package cz.muni.consumption.repository

import android.content.Context
import cz.muni.consumption.data.TrendData
import cz.muni.consumption.database.ConsumptionDatabase
import cz.muni.consumption.database.MeasuredConsumptionDao

// TODO 5.1 Trend Repository
class TrendRepository(
    context: Context,
    private val dao: MeasuredConsumptionDao = ConsumptionDatabase.create(context).measuredConsumptionDao(),
    private val weatherRepository: WeatherRepository = WeatherRepository()
) {

    companion object {
        private const val GAS_PRICE_CZK = 3
        private const val ELECTRICITY_PRICE_CZK = 6
    }

    /**
     * Method uses success callback for returning TrendData for electricity and gas
     * Where trend data are returned only for current year
     * Where TrendData.temperature source is WeatherRepository data
     * Where TrendData.consumption is average consumption for month
     * Where TrendData.price is measuredConsumption * GAS_PRICE_CZK OR ELECTRICITY_PRICE_CZK (depend on type)
     * @see TrendData
     * @param success serves as callback when data are successful returned
     * @param fail serves as fail callback when something wrong happened
     */
    fun getThisYearTrends(success: (electricity: List<TrendData>, gas: List<TrendData>) -> Unit, fail: () -> Unit) {
        // TODO (S) Napsat metodu dle dokumentace ⬆ viz. výše
        // Hint: Vytažení Constumption z DB seskupené dle typu
        // Hint: Pro testování použíte generátor(ConstumptionFragment -> Add -> Generate data), který vygeneruje data pro celý rok

        weatherRepository.getWeather(
            success = { weatherData ->
                // Hint: weatherData je mapa Map<Měsíc, Průměrná teplota>
                // TODO (S) success(electricity, gas)
            },
            fail = fail
        )
    }
}