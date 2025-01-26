package com.debend.multi_screen_navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    private val userList = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = userList

    init {
        userList.value = emptyList()
    }

    fun addUser(user: User) {
        val currentList = userList.value ?: emptyList()
        userList.value = currentList + user
    }

    fun removeUser(user: User) {
        val currentList = userList.value ?: emptyList()
        userList.value = currentList - user
    }

    fun updateUser(oldUser: User, newUser: User) {
        val currentList = userList.value?.toMutableList() ?: mutableListOf()
        val index = currentList.indexOf(oldUser)
        if (index != -1) {
            currentList[index] = newUser
            userList.value = currentList
        }
    }
}
