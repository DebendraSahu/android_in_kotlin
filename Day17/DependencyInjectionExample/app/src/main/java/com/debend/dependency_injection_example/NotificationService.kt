package com.debend.dependency_injection_example

import android.util.Log
import javax.inject.Inject

// NotificationService.kt
interface NotificationService {
    fun sendNotification(message: String)
}

// EmailNotificationService.kt
class EmailNotificationService @Inject constructor() : NotificationService {
    override fun sendNotification(message: String) {
        Log.d("DI-Example", "Email sent: $message")
    }
}

// SMSNotificationService.kt
class SMSNotificationService @Inject constructor() : NotificationService {
    override fun sendNotification(message: String) {
        Log.d("DI-Example", "SMS sent: $message")
    }
}