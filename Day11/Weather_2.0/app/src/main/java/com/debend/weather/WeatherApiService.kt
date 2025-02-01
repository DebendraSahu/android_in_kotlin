package com.debend.weather

import retrofit2.http.GET
import retrofit2.http.Query
const val WEATHER_API_KEY: String = "dbd3b02d8958d62185d02e944cd5f522"

interface WeatherApiService {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String= WEATHER_API_KEY
    ): WeatherResponse
}