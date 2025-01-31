package com.debend.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.debend.weather.ui.theme.WeatherTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


const val WEATHER_API_KEY: String = "dbd3b02d8958d62185d02e944cd5f522"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherTheme {
                WeatherApp()
            }
        }
    }
}

@Composable
fun WeatherApp() {
    val viewModel: WeatherViewModel = viewModel()
    val apiKey = WEATHER_API_KEY // Replace with your OpenWeatherMap API key
    var city by remember { mutableStateOf("") }
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            TextField(modifier = Modifier.fillMaxWidth(0.5F),
                value = city,
                onValueChange = { city = it },
                label = { Text("Enter city") })
            Button(onClick = { viewModel.fetchWeather(city, apiKey) }) {
                Text("SEARCH", modifier = Modifier.fillMaxWidth(0.6F))
            }
        }
        WeatherScreen(viewModel)
    }

    // Fetch weather for default city on launch
    LaunchedEffect(Unit) {
        viewModel.fetchWeather("Hyderabad", WEATHER_API_KEY)
    }
}

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    val weatherState by viewModel.weatherState.collectAsState()

    when (val state = weatherState) {
        is WeatherState.Loading -> {
            FullScreenLoading()
        }

        is WeatherState.Success -> {
            val weather = state.weather
            val isDayTime = isDayTime(weather.sys.sunrise, weather.sys.sunset)
            val gradientColors = getBackgroundGradient(weather.weather[0].main, isDayTime)

            FullScreenWeather(
                weather = weather, gradientColors = gradientColors, isDayTime = isDayTime
            )
        }

        is WeatherState.Error -> {
            FullScreenError(message = state.message)
        }
    }
}

@Composable
fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF1A2980), Color(0xFF26D0CE))
                )
            ), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Color.White)
    }
}

@Composable
fun FullScreenError(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFD32F2F), Color(0xFFB71C1C))
                )
            ), contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Error: $message",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White
        )
    }
}

@Composable
fun FullScreenWeather(weather: WeatherResponse, gradientColors: List<Color>, isDayTime: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = gradientColors)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Weather Icon
            Image(
                painter = rememberAsyncImagePainter(
                    model = "https://openweathermap.org/img/wn/${weather.weather[0].icon}@4x.png"
                ),
                contentDescription = weather.weather[0].description,
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Temperature
            Text(
                text = "${weather.main.temp.toInt()}°C",
                style = MaterialTheme.typography.headlineMedium,
                color = if (isDayTime) Color.White else Color.LightGray
            )

            // Weather Description
            Text(
                text = weather.weather[0].description.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.headlineSmall,
                color = if (isDayTime) Color.White else Color.LightGray
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Additional Weather Details
            WeatherDetailRow(
                icon = Icons.Default.Thermostat,
                text = "Feels like ${weather.main.feels_like.toInt()}°C"
            )
            WeatherDetailRow(
                icon = Icons.Default.WaterDrop, text = "Humidity ${weather.main.humidity}%"
            )
            WeatherDetailRow(icon = Icons.Default.Air, text = "Wind ${weather.wind.speed} m/s")
            WeatherDetailRow(
                icon = Icons.Default.WbSunny, text = "Sunrise ${formatTime(weather.sys.sunrise)}"
            )
            WeatherDetailRow(
                icon = Icons.Default.NightsStay, text = "Sunset ${formatTime(weather.sys.sunset)}"
            )
        }
    }
}

@Composable
fun WeatherDetailRow(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text, style = MaterialTheme.typography.bodyLarge, color = Color.White
        )
    }
}

fun isDayTime(sunrise: Long, sunset: Long): Boolean {
    val currentTime = System.currentTimeMillis() / 1000
    return currentTime in sunrise..sunset
}

fun getBackgroundGradient(weatherCondition: String, isDayTime: Boolean): List<Color> {
    return when (weatherCondition.lowercase()) {
        "clear" -> if (isDayTime) listOf(Color(0xFF56CCF2), Color(0xFF2F80ED))
        else listOf(Color(0xFF0F2027), Color(0xFF203A43), Color(0xFF2C5364))

        "clouds" -> if (isDayTime) listOf(Color(0xFFBBD2C5), Color(0xFF536976))
        else listOf(Color(0xFF1F1C2C), Color(0xFF928DAB))

        "rain" -> listOf(Color(0xFF4B79A1), Color(0xFF283E51))
        "snow" -> listOf(Color(0xFFE6DADA), Color(0xFF274046))
        else -> listOf(Color(0xFF1A2980), Color(0xFF26D0CE))
    }
}

fun formatTime(timestamp: Long): String {
    val date = Date(timestamp * 1000)
    val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return format.format(date)
}
