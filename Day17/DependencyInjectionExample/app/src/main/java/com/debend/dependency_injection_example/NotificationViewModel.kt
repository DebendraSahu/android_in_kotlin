package com.debend.dependency_injection_example

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationService: NotificationService
) : ViewModel() {
    fun sendMessage(message: String) {
        notificationService.sendNotification(message)
    }
}