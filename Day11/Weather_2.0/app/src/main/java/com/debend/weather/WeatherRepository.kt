package com.debend.weather

import com.debend.weather.database.WeatherDao
import com.debend.weather.database.WeatherEntity

class WeatherRepository(
    private val apiService: WeatherApiService, private val weatherDao: WeatherDao
) {
    suspend fun getWeather(cityName: String): WeatherResponse {
        // Check if cached data is available and not stale
        val cachedWeather = weatherDao.getWeatherByCity(cityName)
        if (cachedWeather != null && !isDataStale(cachedWeather.timestamp)) {
            return cachedWeather.toResponse()
        }

        // Fetch fresh data from API
        val freshWeather = apiService.getWeather(cityName)
        weatherDao.insert(freshWeather.toEntity())
        return freshWeather
    }

    private fun isDataStale(timestamp: Long): Boolean {
        val currentTime = System.currentTimeMillis()
        val staleTime = 30 * 60 * 1000 // 30 minutes
        return currentTime - timestamp > staleTime
    }
}


fun WeatherResponse.toEntity(): WeatherEntity {
    return WeatherEntity(
        id = name.hashCode(), // Generating a unique ID based on city name
        cityName = name,
        temperature = main.temp,
        humidity = main.humidity,
        icon = weather.firstOrNull()?.icon ?: "",
        description = weather.firstOrNull()?.description ?: "",
        lon = coord.lon,
        lat = coord.lat,
        sunrise = sys.sunrise,
        sunset = sys.sunset,
        main = weather.firstOrNull()?.main ?: "",
        windSpeed = wind.speed,
        windDeg = wind.deg,
        timestamp = System.currentTimeMillis() // Cache time
    )
}

fun WeatherEntity.toResponse(): WeatherResponse {
    return WeatherResponse(
        name = cityName,
        coord = Coord(lon, lat),
        main = Main(temp = temperature, humidity = humidity, feels_like = temperature),
        wind = Wind(speed = windSpeed, deg = windDeg),
        sys = Sys(sunrise = sunrise, sunset = sunset),
        weather = listOf(Weather(description = description, icon = icon, main = main))
    )
}
