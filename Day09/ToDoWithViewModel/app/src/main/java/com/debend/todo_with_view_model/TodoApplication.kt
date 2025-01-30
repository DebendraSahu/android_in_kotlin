package com.debend.todo_with_view_model

import android.app.Application
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module


class TodoApplication : Application() {
    val appModule = module {
        // Inject Repository
        single { TaskRepository() }

        // Inject Logger (custom library)
//        single { Logger() }

        // Inject ViewModel
        viewModel { TodoViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin() {
            modules(appModule)
        }
    }


}