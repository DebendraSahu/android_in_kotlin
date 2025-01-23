package com.debend.compose_state

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.debend.compose_state.ui.theme.ComposeStateTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeStateTheme {
//                HomeScreen()
                HomeScreenContentWithViewModel(HelloViewModel())
            }
        }
    }
}

@Composable
fun HomeScreen() {
    var value by remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Current value is $value")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Button(onClick = { value++ }) {
                Text(text = "Increment Value")
            }
            Spacer(Modifier.padding(10.dp))
            Button(onClick = { value-- }) {
                Text(text = "Decrement Value")
            }
        }
    }
}

class HelloViewModel : ViewModel() {
    private val _name: MutableLiveData<String> = MutableLiveData("")
    val name: LiveData<String> = _name
    fun onNameChange(name: String) {
        _name.value = name
    }

}

@Composable
fun HomeScreenContentWithViewModel(helloViewModel: HelloViewModel) {
    val name: String by helloViewModel.name.observeAsState("")
    HelloContent(name = name, onNameChange = { helloViewModel.onNameChange(it) })
}

@Composable
fun HomeScreenContentWithRememberStateFlow() {
    var name: String by rememberSaveable {
        mutableStateOf("")
    }
    HelloContent(name = name, onNameChange = { name = it })
}

@Composable
fun HomeScreenContentWithStateFlow() {
    var name: String by remember {
        mutableStateOf("")
    }
    HelloContent(name = name, onNameChange = { name = it })
}

@Preview
@Composable
fun HelloContent(name: String = "Dev", onNameChange: (String) -> Unit = {}) {
    Column(
        modifier = Modifier.padding(top = 50.dp, start = 10.dp)
    ) {
        Text(
            text = "Hello, $name!",
            modifier = Modifier.padding(10.dp),
            style = MaterialTheme.typography.headlineMedium
        )
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text(text = "Name") })
    }
}
