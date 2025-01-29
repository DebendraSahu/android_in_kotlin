package com.debend.mvvm_example

class CounterRepository {
    private var counterValue = 0
    fun getCounter() = counterValue

    fun increment() {
        counterValue++
    }

    fun decrement() {
        counterValue--
    }
}
