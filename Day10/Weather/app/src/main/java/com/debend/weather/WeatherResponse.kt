package com.debend.weather


data class WeatherResponse(
    val name: String, // City name
    val coord: Coord, // Coordinates
    val main: Main, // main info
    val wind: Wind, // wind info
    val sys: Sys, val weather: List<Weather>
) {
    override fun toString(): String {
        return "WeatherResponse(name='$name', coord=$coord, main=$main, weather=$weather)"
    }
}

data class Coord(
    val lon: Double, //longitude
    val lat: Double  //latitude
) {
    override fun toString(): String {
        return "Coord(lon=$lon, lat=$lat)"
    }
}

data class Main(
    val temp: Double, // Temperature
    val humidity: Int, // Humidity
    val feels_like: Double
)

data class Wind(
    val speed: Double, val deg: Int
)

data class Sys(
    val sunrise: Long, val sunset: Long
)

data class Weather(
    val description: String, // Weather description
    val icon: String,
    val main: String
) {
    override fun toString(): String {
        return "Weather(description='$description')"
    }
}

