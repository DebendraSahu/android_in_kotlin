package com.debend.weather


class WeatherRepository {
    suspend fun getWeather(city: String, apiKey: String): WeatherState {
        try {
            val response = RetrofitClient.instance.getWeather(city, apiKey = apiKey)
            println(response)
            return WeatherState.Success(response)
        } catch (e: Exception) {
            return WeatherState.Error(e.message ?: "Unknown error")
        }
    }
}