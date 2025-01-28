package com.debend.apihandling

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.debend.apihandling.ui.theme.ApiHandlingTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApiHandlingTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    MainContent(Modifier.padding(innerPadding))
                }
            }
        }
    }

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun MainContent(modifier: Modifier = Modifier) {
        var isLoading by remember { mutableStateOf(true) }
        var snackBarMessage by remember { mutableStateOf<String?>(null) }

        Box(modifier = modifier.fillMaxSize()) {
            Greeting(name = "Android", modifier = Modifier.align(Alignment.Center))

            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            snackBarMessage?.let {
                Snackbar(modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp),
                    action = {
                        TextButton(onClick = {
                            snackBarMessage = null
                            isLoading = true
                        }) {
                            Text("Dismiss")
                        }
                    }) {
                    Text(it)
                }
            }
        }

        lifecycleScope.launch {
//            fetchApiDataWithCoroutine { isSuccess: Boolean, info: String ->
//                isLoading = isSuccess
//                snackBarMessage = info
//            }
            delay(1000)
            fetchApiDataWithCallback { isSuccess: Boolean, info: String ->
                isLoading = isSuccess
                snackBarMessage = info
            }
        }

    }

    private fun fetchApiDataWithCallback(onResult: (isSuccess: Boolean, info: String) -> Unit) {
        val call = ApiClient.apiService.getNonExistentDataWithCallback() // Call<String>
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    onResult(true, "Data: ${response.body()}")
                } else {
                    val errorMessage = when (response.code()) {
                        404 -> "Resource not found"
                        500 -> "Internal server error"
                        else -> "Unexpected error: ${response.message()}"
                    }
                    onResult(false, errorMessage)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                onResult(false, "Network error: ${t.localizedMessage}")
            }
        })
    }

    private suspend fun fetchApiDataWithCoroutine(onResult: (isSuccess: Boolean, info: String) -> Unit) {
        try {
            val response =
                ApiClient.apiService.getNonExistentDataWithCoroutine() // suspend Response<String>
            if (response.isSuccessful) {
                onResult(true, "Data: ${response.body()}")
            } else {
                val errorMessage = when (response.code()) {
                    404 -> "Resource not found"
                    500 -> "Internal server error"
                    else -> "Unexpected error: ${response.message()}"
                }
                onResult(false, errorMessage)
            }
        } catch (e: Exception) {
            onResult(false, "Network error: ${e.localizedMessage}")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ApiHandlingTheme {
        Greeting("Android")
    }
}
