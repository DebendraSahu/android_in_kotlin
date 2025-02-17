package com.debend.coroutines_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.room.Room

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the Room database
        val db = Room.databaseBuilder(
            applicationContext, UserDatabase::class.java, "user_database"
        ).build()

        // Create an instance of the UserDao
        val userDao = db.userDao()

        // Create a UserViewModel using the UserDao
        val userViewModel = UserViewModel(userDao)

        setContent {
            MaterialTheme {
                // Pass the ViewModel to the composable
                UserApp(viewModel = userViewModel)
            }
        }
    }
}


@Composable
fun UserApp(viewModel: UserViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = { viewModel.fetchUsers() }, modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Fetch Users")
        }
        Button(
            onClick = { viewModel.addUser(User(3, "Dev", 25)) },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Add Users")
        }

        LazyColumn {
            items(viewModel.userList) { user ->
                Text(
                    text = "Name: ${user.name}, Age: ${user.age}", modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
