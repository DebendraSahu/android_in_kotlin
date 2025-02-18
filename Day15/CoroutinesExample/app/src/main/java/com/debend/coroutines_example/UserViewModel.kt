package com.debend.coroutines_example

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(private val userDao: UserDao) : ViewModel() {
    private val _userList = MutableStateFlow<List<User>>(emptyList())
    val userList = _userList.asStateFlow()

    // Fetch data from both Room and API
    fun fetchUsers() {
        viewModelScope.launch {
            _userList.value = userDao.getAllUsers()
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            userDao.insertUsers(listOf(user))
            fetchUsers()
        }
    }

    fun removeAllUsers() {
        viewModelScope.launch {
            userDao.deleteUsers(_userList.value)
            fetchUsers()
        }
    }
}
