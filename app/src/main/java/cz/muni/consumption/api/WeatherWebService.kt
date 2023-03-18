package cz.muni.consumption.api

import cz.muni.consumption.api.response.WeatherDailyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// TODO 4.1 Web Service API
interface WeatherWebService {

    @GET("era5")
    fun getDailyWeather(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("daily") dailyMax: String = "temperature_2m_max",
        @Query("daily") dailyMin: String = "temperature_2m_min",
        @Query("timezone") timezone: String = "auto",
        @Query("latitude") lat: String = "49.20",
        @Query("longitude") long: String = "16.61",
    ): Call<WeatherDailyResponse>
}