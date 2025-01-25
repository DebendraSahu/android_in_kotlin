package com.debend.androidlifecycle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.debend.androidlifecycle.ui.theme.AndroidLifecycleTheme

class SecondActivity : ComponentActivity() {
    private val tag = "SecondActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate event occurred")
        enableEdgeToEdge()
        setContent {
            AndroidLifecycleTheme {
                SecondScreen()
            }

        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart event occurred")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop event occurred")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause event occurred")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume event occurred")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy event occurred")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart event occurred")
    }

    override fun onPostResume() {
        super.onPostResume()
        Log.d(tag, "onPostResume event occurred")
    }

    @Composable
    fun SecondScreen() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = "Hello! This is Second Screen", modifier = Modifier.padding(20.dp)
            )//, fontSize = 30.dp)
            Button(onClick = { navigateToMainScreen() }) {
                Text(text = "Navigate to Previous Screen")
            }
        }

    }

    private fun navigateToMainScreen() {
        intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}


