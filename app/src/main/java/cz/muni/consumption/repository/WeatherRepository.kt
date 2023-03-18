package cz.muni.consumption.repository

import android.util.Log
import cz.muni.consumption.api.RetrofitUtil
import cz.muni.consumption.api.WeatherWebService
import cz.muni.consumption.api.response.WeatherDailyResponse
import cz.muni.consumption.util.DateUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

// TODO 4.4 WeatherRepository
class WeatherRepository(
    // TODO 4.5 Vytvoření webservice
    private val weatherWebService: WeatherWebService = RetrofitUtil.createWeatherWebService()
) {

    fun getWeather(success: (Map<Int, Double>) -> Unit, fail: () -> Unit) {
        val today = Calendar.getInstance().time
        val startOfTheYear = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_YEAR, 1)
        }.time

        // TODO 4.6 Api Call
        weatherWebService.getDailyWeather(
            startDate = DateUtil.apiFormat.format(startOfTheYear),
            endDate = DateUtil.apiFormat.format(today)
        ).enqueue(object : Callback<WeatherDailyResponse> {

            // TODO 4.7 onResponse
            override fun onResponse(call: Call<WeatherDailyResponse>, response: Response<WeatherDailyResponse>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    val weatherData = mapResponse(responseBody)
                    success(weatherData)
                } else {
                    Log.e(this::class.simpleName, "body was null")
                    fail()
                }
            }

            // TODO 4.8 onFailure
            override fun onFailure(call: Call<WeatherDailyResponse>, t: Throwable) {
                Log.e(this::class.simpleName, t.message, t)
                fail()
            }
        })
    }

    // TODO 4.9 Mapper -> Mapování na průměrnou teplotu každý měsíc
    private fun mapResponse(response: WeatherDailyResponse): Map<Int, Double> =
        response
            .daily
            .time
            .mapIndexedNotNull { index, time ->
                val min = response.daily.temperature_2m_min[index]
                val max = response.daily.temperature_2m_max[index]

                if (min != null && max != null) {
                    val date = Calendar.getInstance().apply {
                        setTime(DateUtil.apiFormat.parse(time))
                    }

                    val temp = (min + max) / 2
                    date.get(Calendar.MONTH) to temp
                } else {
                    null
                }
            }.groupBy { (month, _) ->
                month
            }.mapValues { entry ->
                val sum = entry.value.sumOf { (_, temps) ->
                    temps
                }
                sum / entry.value.size
            }
}