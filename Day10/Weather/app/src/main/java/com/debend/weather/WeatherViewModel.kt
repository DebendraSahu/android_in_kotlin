package com.debend.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel() : ViewModel() {
    private val weatherRepository: WeatherRepository = WeatherRepository()
    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherState: StateFlow<WeatherState> = _weatherState

    fun fetchWeather(city: String, apiKey: String) {
        viewModelScope.launch {
            _weatherState.value = weatherRepository.getWeather(city, apiKey)
        }
    }
}
