package com.debend.mvvm_example

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private var repository: CounterRepository = CounterRepository()

    init {
        println("initializing the view model.")
    }

    private val _counter = MutableLiveData(repository.getCounter())
    val counter: LiveData<Int> = _counter

    fun incrementCounter() {
        repository.increment()
        _counter.value = repository.getCounter()  // Update LiveData
    }

    fun decrementCounter() {
        repository.decrement()
        _counter.value = repository.getCounter()  // Update LiveData
    }
}
