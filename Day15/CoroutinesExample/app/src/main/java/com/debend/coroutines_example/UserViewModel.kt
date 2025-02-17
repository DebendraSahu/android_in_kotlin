package com.debend.coroutines_example

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(private val userDao: UserDao) : ViewModel() {
    var userList by mutableStateOf<List<User>>(emptyList())

    init {
        viewModelScope.launch {
            // Simulate fetching from a remote API
            withContext(Dispatchers.IO) {
                // Dummy data to simulate an API response
                addUser(User(1, "John Doe", 28))
                addUser(User(2, "Jane Smith", 32))
            }
        }
    }

    // Fetch data from both Room and API
    fun fetchUsers() {
        viewModelScope.launch {
            // Load cached data from Room
            userList = userDao.getAllUsers()
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            userDao.insertUsers(listOf(user))
            fetchUsers()
        }
    }
}
