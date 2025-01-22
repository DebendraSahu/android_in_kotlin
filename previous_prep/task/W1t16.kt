package task

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import task.W1t14.MILLI_SEC_TO_SECONDS
import kotlin.coroutines.cancellation.CancellationException

/**
 * **Task 16:** Implement a coroutine in Kotlin that launches a new coroutine, performs a simple task, and then prints the result.
 */
fun main() {
    fun runner() = runBlocking {
        val job: Job = launch(Dispatchers.Default) {
            printer()
        }
        delay(5 * MILLI_SEC_TO_SECONDS.toLong())
        job.cancelAndJoin()
    }
    runner()
}

suspend fun printer() {
    var i = 0
    while (i < 100) {
        println("Iterating for $i th time")
        try {
            delay(100)
        } catch (e: CancellationException) {
            break
        }
        i++
    }
}
