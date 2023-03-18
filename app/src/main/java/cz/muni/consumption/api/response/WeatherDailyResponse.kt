package cz.muni.consumption.api.response

// TODO 4.2 Api Response
data class WeatherDailyResponse(
    val daily: Daily
) {

    data class Daily(
        val time: List<String>,
        val temperature_2m_min: List<Double?>,
        val temperature_2m_max: List<Double?>
    )
}