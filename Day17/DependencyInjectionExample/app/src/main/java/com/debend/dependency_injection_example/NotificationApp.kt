package com.debend.dependency_injection_example

import android.app.Application
import dagger.hilt.EntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class NotifyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Verify DI setup
        initDaggerHilt()
    }

    @Inject
    lateinit var entryPoint: EntryPoint
    private fun initDaggerHilt() {
        // Will throw error if DI setup is wrong
    }
}