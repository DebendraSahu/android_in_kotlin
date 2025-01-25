package com.debend.save_instance_state_example

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.debend.save_instance_state_example.ui.theme.SaveInstanceStateExampleTheme

class MainActivity : ComponentActivity() {
    private var _counter: Int = 0

    private val tag = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate event occurred")
        enableEdgeToEdge()
        if (savedInstanceState != null) {
            _counter = savedInstanceState.getInt("counter")
        }
        setContent {
            SaveInstanceStateExampleTheme {
                MainScreen()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter", _counter)
    }

    //can be restore from here as well
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        _counter = savedInstanceState.getInt("counter")
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
    fun MainScreen() {
        var counter by remember {
            mutableIntStateOf(_counter)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hello,\ncounter is $counter", modifier = Modifier.padding(20.dp)
            )
            Button(onClick = {
                _counter++
                counter = _counter
            }) {
                Text(text = "Increment Counter")
            }
        }
    }

}

