package com.debend.dependency_injection_example

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NotificationModule {
    @Binds
    abstract fun provideNotificationService(
        smsService: SMSNotificationService
    ): NotificationService
}