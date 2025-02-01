package com.debend.weather.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
data class WeatherEntity(
    @PrimaryKey val id: Int, // Primary Key of table
    val cityName: String, // name of the city
    val temperature: Double, // temperature
    val humidity: Int, // Humidity
    val icon: String,  // weather icon name
    val description: String, // weather description
    val lon: Double, // longitude
    val lat: Double,  // latitude
    val sunrise: Long, // time of sunrise
    val sunset: Long, // time of sunset
    val main: String, // main info
    val windSpeed: Double, // wind Speed
    val windDeg: Int, // wind degree
    val timestamp: Long // To track when data was cached
)
