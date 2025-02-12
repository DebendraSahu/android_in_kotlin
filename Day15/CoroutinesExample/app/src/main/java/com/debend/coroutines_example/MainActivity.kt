package com.debend.coroutines_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    private var isActive = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CoroutineSimulationApp()
            }
        }
    }

    @Composable
    fun CoroutineSimulationApp() {
        var userData by remember { mutableStateOf("Fetching user data...") }
        var isFetching by remember { mutableStateOf(false) }
        var coroutineScope: CoroutineScope? = remember { null }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            Text(text = userData, style = MaterialTheme.typography.bodyLarge)

            Button(
                onClick = {
                    userData = "Fetching user data..."
                    // Cancel any ongoing coroutine before starting a new one
                    coroutineScope?.cancel()

                    // Create a new CoroutineScope for structured concurrency
                    coroutineScope = CoroutineScope(Dispatchers.Main)
                    isActive = true
                    isFetching = true
                    coroutineScope?.launch {
                        try {
                            val nameDeferred = async { fetchUserName() }
                            val ageDeferred = async { fetchUserAge() }

                            // Wait for both API calls to complete
                            val name = nameDeferred.await()
                            val age = ageDeferred.await()

                            userData = "User: $name, Age: $age"
                        } catch (e: CancellationException) {
                            userData = "Fetching canceled!"
                        } finally {
                            isFetching = false
                        }
                    }
                }, modifier = Modifier.padding(top = 16.dp), enabled = !isFetching
            ) {
                Text("Fetch User Data")
            }

            Button(
                onClick = {
                    isActive = false
                    coroutineScope?.cancel()
                }, modifier = Modifier.padding(top = 16.dp), enabled = isFetching
            ) {
                Text("Cancel Fetch")
            }
        }
    }

    // Simulated API to fetch user name (2-second delay)
    private suspend fun fetchUserName(): String {
        for (i in 0..19) {  // 20 small delays of 100ms
            if (!isActive) throw CancellationException("Fetch UserName canceled!")
            delay(100)  // Small delay to ensure cancellation checks
        }
        return listOf("John", "Jane", "Alice", "Bob").random()
    }

    // Simulated API to fetch user age (3-second delay)
    private suspend fun fetchUserAge(): Int {
        for (i in 0..29) {  // 30 small delays of 100ms
            if (!isActive) throw CancellationException("Fetch UserAge canceled!")
            delay(100)
        }
        return Random.nextInt(18, 60)
    }
}
