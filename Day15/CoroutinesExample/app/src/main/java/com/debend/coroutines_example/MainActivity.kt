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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.room.Room
import kotlin.random.Random

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
    val users: List<User> by viewModel.userList.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "User List",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(users) { user ->
                Text(
                    text = "Name: ${user.name}, Age: ${user.age}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { viewModel.fetchUsers() }, modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            ) {
                Text("FETCH")
            }
            Button(
                onClick = {
                    val id = users.size + 1
                    viewModel.addUser(User(id, "User $id", (0..100).random()))
                }, modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            ) {
                Text("ADD")
            }
            Button(
                onClick = { viewModel.removeAllUsers() },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            ) {
                Text("CLEAR")
            }
        }
    }
}
